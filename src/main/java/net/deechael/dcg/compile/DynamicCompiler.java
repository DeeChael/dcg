package net.deechael.dcg.compile;

import net.deechael.dcg.source.structure.DyGeneratable;
import org.jetbrains.annotations.NotNull;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class DynamicCompiler {

    private final static JavaCompiler SYSTEM_COMPILER = ToolProvider.getSystemJavaCompiler();

    private final DynamicJavaFileManager javaFileManager;
    private final List<Path> libraries;
    private final List<String> compileBeforeFlags;
    private final List<String> compileAfterFlags;

    private final DynamicClassLoader classLoader;

    public DynamicCompiler(List<Path> libraries, List<ClassLoader> classLoaders, List<String> compileBeforeFlags, List<String> compileAfterFlags) {
        this.javaFileManager = new DynamicJavaFileManager(SYSTEM_COMPILER.getStandardFileManager(null, null, null));
        this.libraries = libraries;
        this.compileBeforeFlags = compileBeforeFlags;
        this.compileAfterFlags = compileAfterFlags;

        this.classLoader = new DynamicClassLoader(classLoaders);
    }

    /**
     * Create a queue which is prepared to be compiled
     *
     * @return new empty queue
     */
    public DynamicCompileQueue prepare() {
        return new DynamicCompileQueue(this);
    }

    @NotNull
    protected Map<DyGeneratable, Class<?>> generate(@NotNull Iterable<DyGeneratable> generatables) {
        List<DyGeneratable> dynamicGeneratables = new ArrayList<>();
        generatables.iterator().forEachRemaining(dynamicGeneratables::add);
        List<String> options = new ArrayList<>(this.compileBeforeFlags);
        if (!this.libraries.isEmpty()) {
            StringBuilder classPathOption = new StringBuilder();
            for (Path library : this.libraries) {
                classPathOption.append(library).append(";");
            }
            options.addAll(Arrays.asList("-classpath", classPathOption.toString()));
        }
        options.addAll(this.compileAfterFlags);
        JavaCompiler.CompilationTask task = SYSTEM_COMPILER.getTask(
                null,
                this.javaFileManager,
                null,
                options,
                null,
                dynamicGeneratables.stream()
                        .map(generatable -> {
                            try {
                                return new DynamicStringObject(generatable, new URI(generatable.getSimpleName() + ".java"), JavaFileObject.Kind.SOURCE, generatable.toCompilableString());
                            } catch (URISyntaxException e) {
                                throw new RuntimeException(e);
                            }
                        }).collect(Collectors.toList()));
        if (task.call()) {
            Map<DyGeneratable, Class<?>> classes = new HashMap<>();
            for (DynamicJavaFileObject javaFileObject : this.javaFileManager.readCache()) {
                classes.put(javaFileObject.getGeneratable(), this.classLoader.generate0(javaFileObject.getName().replace(".class", ""), javaFileObject.getBytes()));
            }
            return classes;
        } else {
            throw new RuntimeException("Failed to generate the class!");
        }
    }

    @NotNull
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

}
