package net.deechael.dcg.source.variable.internal;

import net.deechael.dcg.source.variable.Variable;
import org.jetbrains.annotations.NotNull;

public interface NonNameVariable extends Variable {

    @Override
    @NotNull
    default String getName() {
        throw new RuntimeException("no name");
    }

}
