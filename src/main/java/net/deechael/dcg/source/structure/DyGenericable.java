package net.deechael.dcg.source.structure;

import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.type.GenericType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DyGenericable {

    GenericType addGeneric(@NotNull String typeName, @Nullable DyType extending);

    List<Map.Entry<GenericType, Optional<DyType>>> listGenerics();

}
