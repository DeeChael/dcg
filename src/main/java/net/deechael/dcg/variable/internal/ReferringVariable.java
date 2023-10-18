package net.deechael.dcg.variable.internal;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.variable.DyType;
import net.deechael.dcg.variable.Variable;
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
