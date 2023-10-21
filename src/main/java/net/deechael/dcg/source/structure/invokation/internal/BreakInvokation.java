package net.deechael.dcg.source.structure.invokation.internal;

import net.deechael.dcg.source.structure.DyLabel;
import net.deechael.dcg.source.structure.invokation.Invokation;

public class BreakInvokation implements Invokation {

    private final DyLabel label;

    public BreakInvokation(DyLabel label) {
        this.label = label;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        builder.append("break");
        if (this.label != null)
            builder.append(" ")
                    .append(this.label.getName());
        builder.append(";");
        return builder.toString();
    }

}
