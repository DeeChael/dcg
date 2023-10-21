package net.deechael.dcg.source.structure.invokation.internal;

import net.deechael.dcg.source.structure.invokation.Invokation;
import net.deechael.dcg.source.variable.Variable;

public class ReturnInvokation implements Invokation {

    private final Variable variable;

    public ReturnInvokation(Variable variable) {
        this.variable = variable;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        builder.append("return");
        if (this.variable != null)
            builder.append(" ")
                    .append(this.variable.toVariableString());
        builder.append(";");
        return builder.toString();
    }

}
