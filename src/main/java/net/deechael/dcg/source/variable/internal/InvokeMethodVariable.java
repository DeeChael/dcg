package net.deechael.dcg.source.variable.internal;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.invokation.Invoker;
import net.deechael.dcg.source.variable.DyType;
import net.deechael.dcg.source.variable.Variable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class InvokeMethodVariable implements Variable {

    private final DyStructure domain;

    private final Invoker invoker;
    private final String methodName;
    private final Variable[] parameters;

    public InvokeMethodVariable(DyStructure domain, Invoker invoker, String methodName, Variable... parameters) {
        this.domain = domain;

        this.invoker = invoker;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    @Override
    public @NotNull DyType getType() {
        throw new RuntimeException("InvokeMethodVariable cannot get type!");
    }

    @Override
    public String getName() {
        throw new RuntimeException("InvokeMethodVariable cannot get name!");
    }

    @Override
    public @NotNull DyStructure getDomain() {
        return this.domain;
    }

    @Override
    public String toVariableString() {
        StringBuilder builder = new StringBuilder();
        if (this.invoker != null)
            builder.append(this.invoker.toInvokerString())
                    .append(".");
        builder.append(this.methodName)
                .append("(")
                .append(String.join(", ", Arrays.stream(this.parameters).map(Variable::toVariableString).toList().toArray(new String[0])))
                .append(")");
        return builder.toString();
    }

}
