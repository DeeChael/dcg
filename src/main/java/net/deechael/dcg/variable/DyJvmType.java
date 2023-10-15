package net.deechael.dcg.variable;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

final class DyJvmType implements DyType {

    private final Class<?> clazz;

    private final DyType baseType;

    private boolean isArray = false;

    DyJvmType(Class<?> clazz) {
        this.clazz = clazz;


        Class<?> tempClass = clazz;
        while (tempClass.isArray()) {
            tempClass = tempClass.getComponentType();
            this.isArray = true;
        }
        this.baseType = DyType.classType(tempClass);
    }

    @Override
    public boolean isPrimitive() {
        return this.clazz.isPrimitive();
    }

    @NotNull
    @Override
    public DyType getBaseType() {
        return this.baseType;
    }

    @Override
    public @NotNull String toTypeString() {
        if (baseType == this)
            return this.clazz.getName().replace("$", ".");
        return this.baseType.toTypeString();
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
        DyJvmType dyJvmType = (DyJvmType) o;
        return Objects.equals(this.clazz, dyJvmType.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.clazz);
    }

}
