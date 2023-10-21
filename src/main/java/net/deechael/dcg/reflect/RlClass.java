package net.deechael.dcg.reflect;

import net.deechael.dcg.source.type.DyType;
import org.jetbrains.annotations.NotNull;

public class RlClass implements DyType {
    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public @NotNull DyType getBaseType() {
        return null;
    }

    @Override
    public @NotNull String toTypeString() {
        return null;
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
