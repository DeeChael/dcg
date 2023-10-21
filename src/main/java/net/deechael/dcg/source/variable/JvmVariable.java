package net.deechael.dcg.source.variable;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.DyUndefinedStructure;
import org.jetbrains.annotations.NotNull;

public interface JvmVariable extends Variable {

    @Override
    default String getName() {
        throw new RuntimeException("Jvm variable has no name");
    }

    @Override
    @NotNull
    default DyStructure getDomain() {
        return DyUndefinedStructure.INSTANCE;
    }

}
