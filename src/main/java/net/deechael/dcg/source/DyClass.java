package net.deechael.dcg.source;

import com.google.gson.JsonObject;
import net.deechael.dcg.source.structure.DyAnnotatable;
import net.deechael.dcg.source.structure.DySerializable;
import net.deechael.dcg.source.structure.DySource;
import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.container.DyConstructorContainer;
import net.deechael.dcg.source.structure.container.DyFieldContainer;
import net.deechael.dcg.source.structure.container.DyMethodContainer;
import net.deechael.dcg.source.structure.importation.DyExportable;
import net.deechael.dcg.source.structure.importation.DyImportable;
import net.deechael.dcg.variable.DyType;
import net.deechael.dcg.variable.JvmVariable;
import net.deechael.dcg.variable.Visibility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public final class DyClass implements DyStructure, DySource, DyAnnotatable, DyType, DyFieldContainer, DyMethodContainer, DyConstructorContainer, DyImportable, DySerializable {

    private Visibility visibility;

    private final DyPackage dyPackage;
    private final String className;

    DyClass(Visibility visibility, DyPackage dyPackage, String className) {
        this.visibility = visibility;
        this.dyPackage = dyPackage;
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
    public @NotNull String getPackageName() {
        return this.dyPackage != null ? this.dyPackage.getName() : "";
    }

    @Override
    public @NotNull String getName() {
        return String.join(".", this.dyPackage.getName(), this.className);
    }

    @Override
    public @NotNull String getSimpleName() {
        return this.className;
    }

    @Override
    public @Nullable DyPackage getPackage() {
        return this.dyPackage;
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
    public boolean isArray() {
        return false;
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
        return this.visibility;
    }

}
