package net.deechael.dcg.source.variable.internal.jvm;

import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.variable.JvmVariable;
import org.jetbrains.annotations.NotNull;

public class NullVariable implements JvmVariable {

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
    public String toVariableString() {
        return "null";
    }

}
