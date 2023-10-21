package net.deechael.dcg.source.variable.internal.jvm;

import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.variable.JvmVariable;
import org.jetbrains.annotations.NotNull;

public class StringVariable implements JvmVariable {

    private final String value;

    public StringVariable(String value) {
        this.value = value;
    }

    @Override
    public @NotNull DyType getType() {
        return DyType.STRING;
    }

    @Override
    public String toVariableString() {
        String builder = "\"" +
                this.value +
                "\"";
        return builder;
    }
}
