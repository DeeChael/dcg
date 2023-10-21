package net.deechael.dcg.source.structure.invokation.internal;

import net.deechael.dcg.source.structure.invokation.Invokation;

public class CreateLabelInvokation implements Invokation {

    private final String labelName;

    public CreateLabelInvokation(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.labelName)
                .append(":");
        return builder.toString();
    }

}
