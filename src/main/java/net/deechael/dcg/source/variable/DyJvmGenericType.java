package net.deechael.dcg.source.variable;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

final class DyJvmGenericType implements DyType {

    private final Class<?> clazz;
    private final List<DyType> types;

    private final DyType baseType;

    private boolean isArray = false;

    DyJvmGenericType(Class<?> clazz, DyType... types) {
        if (clazz.isPrimitive())
            throw new IllegalArgumentException("Primitive cannot be generic type");
        this.clazz = clazz;
        this.types = Arrays.asList(types);

        Class<?> tempClass = clazz;
        while (tempClass.isArray()) {
            tempClass = tempClass.getComponentType();
            this.isArray = true;
        }
        this.baseType = DyType.classType(tempClass);
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    @NotNull
    public DyType getBaseType() {
        return this.baseType;
    }

    @Override
    public @NotNull String toTypeString() {
        StringBuilder builder = new StringBuilder();
        int loopTimes = this.types.size();
        if (loopTimes > 0) {
            builder.append("<");
            for (int i = 0; i < loopTimes; i++) {
                builder.append(this.types.get(i).toTypeString());
                if (i != (loopTimes - 1)) {
                    builder.append(", ");
                }
            }
            builder.append(">");
        }
        builder.insert(0, this.baseType.toTypeString());
        return builder.toString();
    }

    @Override
    public boolean isArray() {
        return this.isArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DyJvmGenericType that = (DyJvmGenericType) o;
        return Objects.equals(this.clazz, that.clazz) && Objects.equals(this.types, that.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.clazz, this.types);
    }

}
