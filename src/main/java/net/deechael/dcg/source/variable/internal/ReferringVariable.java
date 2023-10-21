package net.deechael.dcg.source.variable.internal;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.variable.Variable;
import org.jetbrains.annotations.NotNull;

public class ReferringVariable implements Variable {

    private final DyStructure structure;

    private final DyType type;
    private final String name;

    public ReferringVariable(DyStructure structure, DyType type, String name) {
        this.structure = structure;

        this.type = type;
        this.name = name;
    }

    @Override
    public @NotNull DyType getType() {
        if (this.type == null)
            throw new RuntimeException("This referring variable may be a multi-type available variable!");
        return this.type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public @NotNull DyStructure getDomain() {
        return this.structure;
    }

    @Override
    public String toVariableString() {
        return this.getName();
    }

}
