package net.deechael.dcg.source.structure.type;

import com.google.gson.JsonObject;
import net.deechael.dcg.source.DyPackage;
import net.deechael.dcg.source.structure.*;
import net.deechael.dcg.source.structure.container.DyConstructorContainer;
import net.deechael.dcg.source.structure.container.DyFieldContainer;
import net.deechael.dcg.source.structure.container.DyMethodContainer;
import net.deechael.dcg.source.structure.importation.DyExportable;
import net.deechael.dcg.source.structure.importation.DyImportable;
import net.deechael.dcg.source.structure.method.DyClassMethod;
import net.deechael.dcg.source.structure.method.DyMethod;
import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.type.GenericType;
import net.deechael.dcg.source.variable.JvmVariable;
import net.deechael.dcg.source.variable.Visibility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DyClass implements DySource, DyAnnotatable, DyType, DyFieldContainer, DyMethodContainer, DyConstructorContainer, DyImportable, DyGenericable, DySerializable {

    private final Visibility visibility;

    private final DyPackage dyPackage;
    private final String className;

    private final List<DyExportable> imports = new ArrayList<>();
    private final List<DyMethod> methods = new ArrayList<>();

    public DyClass(Visibility visibility, DyPackage dyPackage, String className) {
        this.visibility = visibility;
        this.dyPackage = dyPackage;
        this.className = className;
    }

    public DyClassMethod newMethod(@NotNull Visibility visibility, @Nullable DyType returnType, @NotNull String name) {
        DyClassMethod method = new DyClassMethod(new DyStructure[]{this}, visibility, returnType, name);
        this.methods.add(method);
        return method;
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
        for (DyExportable exportable : types)
            if (exportable.isExportable())
                this.imports.add(exportable);
    }

    @Override
    public void imports(Iterable<DyExportable> types) {

    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public GenericType addGeneric(@NotNull String typeName, @Nullable DyType extending) {
        return null;
    }

    @Override
    public List<GenericType> listGenerics() {
        return null;
    }
}
