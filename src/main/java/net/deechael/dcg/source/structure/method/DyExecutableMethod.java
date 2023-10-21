package net.deechael.dcg.source.structure.method;

import net.deechael.dcg.source.structure.DyParameter;
import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.execution.DyExecutable;
import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.type.GenericType;
import net.deechael.dcg.source.variable.JvmVariable;
import net.deechael.dcg.source.variable.Visibility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DyExecutableMethod extends DyExecutable implements DyMethod {

    private final DyStructure[] parentDomains;
    protected final Visibility visibility;
    protected final Map<DyType, Map<String, JvmVariable>> annotations = new HashMap<>();
    protected final List<GenericType> generics = new ArrayList<>();
    protected final List<DyParameter> parameters = new ArrayList<>();

    private final DyType returnType;
    private final String name;

    private boolean isStatic = false;

    public DyExecutableMethod(DyStructure[] parentDomains, Visibility visibility, DyType returnType, String name) {
        this.parentDomains = parentDomains;
        this.visibility = visibility;
        this.returnType = returnType;
        this.name = name;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    @Override
    public boolean isStatic() {
        return this.isStatic;
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public void annotate(@NotNull DyType annotationType, @Nullable Map<String, JvmVariable> values) {
        this.annotations.put(annotationType, values != null ? values : new HashMap<>());
    }

    @Override
    public @NotNull Map<DyType, Map<String, JvmVariable>> listAnnotations() {
        return this.annotations;
    }

    @Override
    public @NotNull DyStructure[] getParentDomains() {
        return this.parentDomains;
    }

    @Override
    public GenericType addGeneric(@NotNull String typeName, @Nullable DyType extending) {
        GenericType type = new GenericType(new DyStructure[]{this}, typeName, extending);
        this.generics.add(type);
        return type;
    }

    @Override
    public List<GenericType> listGenerics() {
        return this.generics;
    }

    @Override
    public DyParameter addParameter(DyType type, String name) {
        DyParameter parameter = new DyParameter(this, type, name);
        this.parameters.add(parameter);
        return parameter;
    }

    @Override
    public List<DyParameter> listParameters() {
        return this.parameters;
    }

    @Nullable
    @Override
    public DyType getReturnType() {
        return returnType;
    }

    @Override
    public String getName() {
        return name;
    }

}
