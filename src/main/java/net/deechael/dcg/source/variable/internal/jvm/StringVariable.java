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
        StringBuilder builder = new StringBuilder();
        builder.append("\"")
                .append(this.value)
                .append("\"");
        return builder.toString();
    }
}
