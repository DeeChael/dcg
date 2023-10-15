package net.deechael.dcg.sourcer;

import com.google.gson.JsonObject;
import net.deechael.dcg.structure.*;
import net.deechael.dcg.structure.container.DyConstructorContainer;
import net.deechael.dcg.structure.container.DyFieldContainer;
import net.deechael.dcg.structure.container.DyMethodContainer;
import net.deechael.dcg.structure.importation.DyExportable;
import net.deechael.dcg.structure.importation.DyImportable;
import net.deechael.dcg.variable.DyType;
import net.deechael.dcg.variable.JvmVariable;
import net.deechael.dcg.variable.Visibility;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class DyClass implements DyStructure, DySource, DyAnnotatable, DyType, DyFieldContainer, DyMethodContainer, DyConstructorContainer, DyImportable, DySerializable {

    private Visibility visibility;

    private final String packageName;
    private final String className;

    private DyClass(Visibility visibility, String className, String packageName) {
        this.visibility = visibility;
        this.packageName = packageName;
        this.className = className;
    }

    @Override
    public void annotate(@NotNull DyType annotationType, Map<String, JvmVariable> values) {

    }

    @Override
    public @NotNull Map<DyType, Map<String, JvmVariable>> listAnnotations() {
        return null;
    }

    @Override
    public @NotNull String getPackage() {
        return this.packageName;
    }

    @Override
    public @NotNull String getName() {
        return String.join(".", this.packageName, this.className);
    }

    @Override
    public @NotNull String getSimpleName() {
        return this.className;
    }

    @Override
    public DyStructure[] getParentDomains() {
        return new DyStructure[0];
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public @NotNull DyType getBaseType() {
        return this;
    }

    @Override
    public @NotNull String toTypeString() {
        return this.getName();
    }

    @Override
    public String toCompilableString() {
        return null;
    }

    @Override
    public @NotNull JsonObject serialize() {
        return null;
    }

    @Override
    public void imports(DyExportable... types) {

    }

    @Override
    public void imports(Iterable<DyExportable> types) {

    }

    @Override
    public Visibility getVisibility() {
        return null;
    }

    public static class Builder {

        private String className;
        private String packageName;
        private Visibility visibility = Visibility.DEFAULT;

        Builder() {
        }

        public Builder withName(String className) {
            this.className = className;
            return this;
        }

        public Builder withPackage(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder withVisibility(Visibility visibility) {
            this.visibility = visibility;
            return this;
        }

        public DyClass build() {
            return new DyClass(this.visibility, this.className, this.packageName);
        }

    }

}
