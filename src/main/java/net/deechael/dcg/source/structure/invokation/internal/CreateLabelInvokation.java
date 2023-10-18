package net.deechael.dcg.source.structure.invokation.internal;

import net.deechael.dcg.source.structure.invokation.Invokation;

public class CreateLabelInvokation implements Invokation {

    private final String labelName;

    public CreateLabelInvokation(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toCompilableString() {
        return new StringBuilder().append(this.labelName).append(":").toString();
    }

}
