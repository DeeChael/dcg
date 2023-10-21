package net.deechael.dcg.source.structure.method;

import net.deechael.dcg.source.structure.DyAnnotatable;
import net.deechael.dcg.source.structure.DyGenericable;
import net.deechael.dcg.source.structure.DyParameterable;
import net.deechael.dcg.source.structure.DyVisible;
import net.deechael.dcg.source.type.DyType;
import org.jetbrains.annotations.Nullable;

public interface DyMethod extends DyParameterable, DyVisible, DyAnnotatable, DyGenericable {

    boolean isStatic();

    @Nullable
    DyType getReturnType();

    String getName();

}
