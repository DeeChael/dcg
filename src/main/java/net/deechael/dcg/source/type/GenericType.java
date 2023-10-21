package net.deechael.dcg.source.type;

import net.deechael.dcg.source.structure.DyStructure;
import org.jetbrains.annotations.NotNull;

public class GenericType implements DyType, DyStructure {

    private final DyStructure[] parentDomains;
    private final String name;

    private final DyType extending;

    public GenericType(DyStructure[] parentDomains, String name, DyType extending) {
        this.parentDomains = parentDomains;
        this.name = name;
        this.extending = extending;
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

    public String toGenericTypeString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name);
        if (this.extending != null)
            builder.append(" ")
                    .append("extends")
                    .append(" ")
                    .append(this.extending.toTypeString());
        return builder.toString();
    }

}
