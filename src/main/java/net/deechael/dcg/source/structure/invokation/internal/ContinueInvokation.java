package net.deechael.dcg.source.structure.invokation.internal;

import net.deechael.dcg.source.structure.DyLabel;
import net.deechael.dcg.source.structure.invokation.Invokation;

public class ContinueInvokation implements Invokation {

    private final DyLabel label;

    public ContinueInvokation(DyLabel label) {
        this.label = label;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        builder.append("continue");
        if (this.label != null)
            builder.append(" ")
                    .append(this.label.getName());
        builder.append(";");
        return builder.toString();
    }

}
