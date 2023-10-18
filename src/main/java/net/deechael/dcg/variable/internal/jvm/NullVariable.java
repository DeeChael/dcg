package net.deechael.dcg.variable.internal.jvm;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.DyUndefinedStructure;
import net.deechael.dcg.variable.DyType;
import net.deechael.dcg.variable.Variable;
import org.jetbrains.annotations.NotNull;

public class NullVariable implements Variable {

    @Override
    public @NotNull DyType getType() {
        try {
            throw new NoSuchFieldException("Null value has no class");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public @NotNull DyStructure getDomain() {
        return DyUndefinedStructure.INSTANCE;
    }

    @Override
    public String toVariableString() {
        return "null";
    }

}
