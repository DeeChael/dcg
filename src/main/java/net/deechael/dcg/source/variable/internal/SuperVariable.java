package net.deechael.dcg.source.variable.internal;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.variable.Variable;
import net.deechael.dcg.source.type.DyType;
import org.jetbrains.annotations.NotNull;

public final class SuperVariable implements Variable, NonNameVariable {

    private final DyStructure structure;
    private final DyType type;

    public SuperVariable(DyStructure structure, DyType type) {
        this.structure = structure;
        this.type = type;
    }

    @Override
    public @NotNull DyType getType() {
        return this.type;
    }

    @Override
    public @NotNull DyStructure getDomain() {
        return this.structure;
    }

    @Override
    public String toVariableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.type.toTypeString())
                .append(".")
                .append("super");
        return builder.toString();
    }

}
