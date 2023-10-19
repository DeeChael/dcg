package net.deechael.dcg.variable.internal;

import net.deechael.dcg.variable.DyType;
import net.deechael.dcg.variable.Variable;
import org.jetbrains.annotations.NotNull;

public interface NonTypeVariable extends Variable {

    @Override
    @NotNull
    default DyType getType() {
        throw new RuntimeException("no type");
    }

}
