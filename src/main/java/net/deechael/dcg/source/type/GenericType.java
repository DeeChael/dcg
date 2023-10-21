package net.deechael.dcg.source.type;

import net.deechael.dcg.source.structure.DyStructure;
import org.jetbrains.annotations.NotNull;

public final class GenericType implements DyType, DyStructure {

    private final DyStructure[] parentDomains;
    private final String name;

    public GenericType(DyStructure[] parentDomains, String name) {
        this.parentDomains = parentDomains;
        this.name = name;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public @NotNull DyType getBaseType() {
        return this;
    }

    @Override
    public @NotNull String toTypeString() {
        return this.name;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public @NotNull DyStructure[] getParentDomains() {
        return this.parentDomains;
    }

    public String getName() {
        return this.name;
    }

}
