package net.deechael.dcg.source.structure;

import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.type.GenericType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DyGenericable {

    GenericType addGeneric(@NotNull String typeName, @Nullable DyType extending);

    List<GenericType> listGenerics();

}
