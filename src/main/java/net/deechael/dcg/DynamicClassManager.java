package net.deechael.dcg;

import net.deechael.dcg.compile.DynamicCompiler;
import net.deechael.dcg.map.DynamicMapper;
import net.deechael.dcg.reflect.DynamicReflector;
import net.deechael.dcg.source.DynamicSourcer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class DynamicClassManager {

    private final DynamicCompiler compiler;
    private final List<Path> libraries;
    private final List<ClassLoader> classLoaders;
    private final List<String> compileBeforeFlags;
    private final List<String> compileAfterFlags;

    private final DynamicSourcer sourcer;
    private final DynamicMapper mapper;
    private final DynamicReflector reflector;

    private DynamicClassManager(List<Path> libraries, List<ClassLoader> classLoaders, List<String> beforeFlags, List<String> afterFlags) {
        this.compiler = this.newCompiler();
        this.libraries = libraries;
        this.classLoaders = classLoaders;
        this.compileBeforeFlags = beforeFlags;
        this.compileAfterFlags = afterFlags;

        this.sourcer = new DynamicSourcer();
        this.mapper = new DynamicMapper();
        this.reflector = new DynamicReflector();
    }

    /**
     * Create new compiler
     *
     * @return new compiler
     */
    @NotNull
    public DynamicCompiler newCompiler() {
        return new DynamicCompiler(libraries, classLoaders, this.compileBeforeFlags, this.compileAfterFlags);
    }

    /**
     * Get the default compiler of this manager which will be used for Mapper too
     *
     * @return default compiler
     */
    @NotNull
    public DynamicCompiler defaultCompiler() {
        return compiler;
    }

    /**
     * Get sourcer to generate classes
     *
     * @return sourcer
     */
    public DynamicSourcer sourcer() {
        return sourcer;
    }

    public DynamicMapper mapper() {
        return mapper;
    }

    public DynamicReflector reflector() {
        return reflector;
    }

    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<Path> libraries = new ArrayList<>();
        private final List<ClassLoader> classLoaders = new ArrayList<>();

        private final List<String> beforeFlags = new ArrayList<>();
        private final List<String> afterFlags = new ArrayList<>();

        private Builder() {
        }

        public Builder library(@NotNull JarFile jarFile) {
            this.libraries.add(new File(jarFile.getName()).toPath());
            return this;
        }

        public Builder library(@NotNull File jarFile) {
            if (jarFile.isDirectory() || !jarFile.getName().endsWith(".jar"))
                throw new IllegalArgumentException("The library must be a jar file");
            this.libraries.add(jarFile.toPath());
            return this;
        }

        public Builder library(@NotNull Path jarFile) {
            return this.library(jarFile.toFile());
        }

        public Builder library(@NotNull String jarFile) {
            return this.library(new File(jarFile));
        }

        public Builder classLoader(@NotNull ClassLoader classLoader) {
            this.classLoaders.add(classLoader);
            return this;
        }

        public Builder compileFlag(@NotNull String key, @NotNull String value, @NotNull Priority priority) {
            if (priority == Priority.BEFORE) {
                this.beforeFlags.add(key);
                this.beforeFlags.add(value);
            } else {
                this.afterFlags.add(key);
                this.afterFlags.add(value);
            }
            return this;
        }

        public Builder compileFlag(@NotNull String value, @NotNull Priority priority) {
            if (priority == Priority.BEFORE) {
                this.beforeFlags.add(value);
            } else {
                this.afterFlags.add(value);
            }
            return this;
        }

        @NotNull
        public DynamicClassManager build() {
            return new DynamicClassManager(this.libraries, this.classLoaders, this.beforeFlags, this.afterFlags);
        }

    }

}
