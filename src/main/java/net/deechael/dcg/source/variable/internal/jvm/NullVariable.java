package net.deechael.dcg.source.variable.internal.jvm;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.DyUndefinedStructure;
import net.deechael.dcg.source.variable.Variable;
import net.deechael.dcg.source.variable.DyType;
import org.jetbrains.annotations.NotNull;

public class NullVariable implements Variable {

    public final static NullVariable INSTANCE = new NullVariable();

    private NullVariable() {
    }

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
