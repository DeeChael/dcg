package net.deechael.dcg.source.structure.invokation.internal;

import net.deechael.dcg.source.structure.execution.DyExecutable;
import net.deechael.dcg.source.structure.invokation.Invokation;

public class ExecutableInvokation implements Invokation {

    private final DyExecutable executable;
    private final boolean isSynchronized;

    public ExecutableInvokation(DyExecutable executable, boolean isSynchronized) {
        this.executable = executable;
        this.isSynchronized = isSynchronized;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        if (this.isSynchronized)
            builder.append("synchronized")
                    .append(" ");
        builder.append("{")
                .append("\n")
                .append(this.executable.toCompilableString())
                .append("\n")
                .append("}");
        return builder.toString();
    }

}
