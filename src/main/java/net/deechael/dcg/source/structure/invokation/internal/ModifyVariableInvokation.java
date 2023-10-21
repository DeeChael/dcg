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
        String builder = this.referring.toVariableString() +
                " " +
                "=" +
                " " +
                this.newValue.toVariableString() +
                ";";
        return builder;
    }

}
