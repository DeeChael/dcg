package net.deechael.dcg.source.structure.invokation.internal;

import net.deechael.dcg.source.structure.invokation.Invokation;
import net.deechael.dcg.source.variable.internal.InvokeMethodVariable;

public class InvokeMethodInvokation implements Invokation {

    private final InvokeMethodVariable variable;

    public InvokeMethodInvokation(InvokeMethodVariable variable) {
        this.variable = variable;
    }

    @Override
    public String toCompilableString() {
        String builder = this.variable.toVariableString() +
                ";";
        return builder;
    }

}
