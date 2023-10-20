package net.deechael.dcg.source.structure.invokation.internal;

import net.deechael.dcg.source.structure.DyAnnotatable;
import net.deechael.dcg.source.structure.invokation.Invokation;
import net.deechael.dcg.util.StringCompiler;
import net.deechael.dcg.source.variable.DyType;
import net.deechael.dcg.source.variable.JvmVariable;
import net.deechael.dcg.source.variable.Variable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CreateVariableInvokation implements Invokation, DyAnnotatable {

    private final DyType type;
    private final String name;
    private final Variable variable;

    private final Variable newVariable;

    private boolean isFinal = false;

    private final Map<DyType, Map<String, JvmVariable>> annotations = new HashMap<>();

    public CreateVariableInvokation(DyType type, String name, Variable variable, Variable newVariable) {
        this.type = type;
        this.name = name;
        this.variable = variable;
        this.newVariable = newVariable;
    }

    public Variable getNewVariable() {
        return this.newVariable;
    }

    public CreateVariableInvokation setFinal(boolean isFinal) {
        this.isFinal = isFinal;
        return this;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        if (!this.annotations.isEmpty())
            builder.append(StringCompiler.compileAnnotations(this.annotations));
        if (isFinal)
            builder.append("final").append(" ");
        builder.append(this.type.toTypeString())
                .append(" ")
                .append(this.name)
                .append(" ")
                .append("=")
                .append(" ")
                .append(this.variable.toVariableString())
                .append(";");
        return builder.toString();
    }

    @Override
    public void annotate(@NotNull DyType annotationType, @Nullable Map<String, JvmVariable> values) {
        this.annotations.put(annotationType, values != null ? values : new HashMap<>());
    }

    @Override
    public @NotNull Map<DyType, Map<String, JvmVariable>> listAnnotations() {
        return this.annotations;
    }
}
