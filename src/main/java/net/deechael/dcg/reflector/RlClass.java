package net.deechael.dcg.reflector;

import net.deechael.dcg.variable.DyType;
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
