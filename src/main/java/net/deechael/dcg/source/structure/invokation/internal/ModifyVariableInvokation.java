package net.deechael.dcg.source.structure.invokation.internal;

import net.deechael.dcg.source.structure.invokation.Invokation;
import net.deechael.dcg.source.variable.Variable;

public class ModifyVariableInvokation implements Invokation {

    private final Variable referring;
    private final Variable newValue;


    public ModifyVariableInvokation(Variable referring, Variable newValue) {
        this.referring = referring;
        this.newValue = newValue;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.referring.toVariableString())
                .append(" ")
                .append("=")
                .append(" ")
                .append(this.newValue.toVariableString())
                .append(";");
        return builder.toString();
    }

}
