package net.deechael.dcg.source.structure;

import org.jetbrains.annotations.NotNull;

public final class DyUndefinedStructure implements DyStructure {

    public final static DyUndefinedStructure INSTANCE = new DyUndefinedStructure();

    private DyUndefinedStructure() {}

    @Override
    public @NotNull DyStructure[] getParentDomains() {
        return new DyStructure[0];
    }

}
