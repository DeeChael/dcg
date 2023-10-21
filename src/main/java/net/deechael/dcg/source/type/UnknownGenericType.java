package net.deechael.dcg.source.type;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.DyUndefinedStructure;
import org.jetbrains.annotations.NotNull;

public class UnknownGenericType extends GenericType {

    public UnknownGenericType(DyType extending) {
        super(new DyStructure[]{DyUndefinedStructure.INSTANCE}, "?", extending);
    }

    @Override
    public @NotNull String toTypeString() {
        return this.toGenericTypeString();
    }

}
