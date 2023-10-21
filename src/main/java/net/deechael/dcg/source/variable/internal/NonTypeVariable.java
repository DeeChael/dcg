package net.deechael.dcg.source.variable.internal;

import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.variable.Variable;
import org.jetbrains.annotations.NotNull;

public interface NonTypeVariable extends Variable {

    @Override
    @NotNull
    default DyType getType() {
        throw new RuntimeException("no type");
    }

}
