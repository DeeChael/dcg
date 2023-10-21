package net.deechael.dcg.source.structure.execution;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.invokation.Invokation;
import org.jetbrains.annotations.NotNull;

public final class DyInnerExecutable extends DyExecutable {

    private final DyStructure[] parentDomains;

    public DyInnerExecutable(DyStructure[] parentDomains) {
        this.parentDomains = parentDomains;
    }

    @Override
    public @NotNull DyStructure[] getParentDomains() {
        return this.parentDomains;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        for (Invokation invokation : this.listInvokations())
            builder.append(invokation.toCompilableString()).append("\n");
        return builder.toString();
    }

}
