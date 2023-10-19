package net.deechael.dcg.variable.internal;

import net.deechael.dcg.variable.DyType;
import net.deechael.dcg.variable.Variable;
import org.jetbrains.annotations.NotNull;

public interface NonNameVariable extends Variable {

    @Override
    @NotNull
    default String getName() {
        throw new RuntimeException("no name");
    }

}
