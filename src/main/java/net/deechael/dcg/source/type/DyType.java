package net.deechael.dcg.source.type;

import net.deechael.dcg.source.structure.importation.DyExportable;
import net.deechael.dcg.source.structure.invokation.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DyType extends DyExportable, Invoker {

    // Default provided JTypes
    DyType VOID = classType(void.class);
    DyType INT = classType(int.class);
    DyType BOOLEAN = classType(boolean.class);
    DyType DOUBLE = classType(double.class);
    DyType FLOAT = classType(float.class);
    DyType LONG = classType(long.class);
    DyType SHORT = classType(short.class);
    DyType BYTE = classType(byte.class);
    DyType CHAR = classType(char.class);
    DyType OBJECT = classType(Object.class);
    DyType STRING = classType(String.class);
    DyType CLASS = classType(Class.class);

    /**
     * Get type for normal class type
     *
     * @param clazz class type
     * @return type
     */
    @NotNull
    static DyType classType(@NotNull Class<?> clazz) {
        return new DyJvmType(clazz);
    }

    /**
     * Get type for a generic type </br>
     * Example: List<T>, Map<K, V> etc.
     *
     * @param clazz base type
     * @param types generic parameters
     * @return type
     */
    @NotNull
    static DyType genericClassType(@NotNull Class<?> clazz, @NotNull DyType... types) {
        return new DyJvmGenericType(clazz, types);
    }

    @NotNull
    static DyType unknownGenericType(@Nullable DyType extending) {
        return new UnknownGenericType(extending);
    }

    /**
     * To check if this type is primitive type
     *
     * @return if is primitive type
     */
    boolean isPrimitive();

    /**
     * Get the base type of this type </br>
     * Example: if this type is java.lang.String[][], this method will return java.lang.String
     *
     * @return base type of this type
     */
    @NotNull
    DyType getBaseType();

    /**
     * Generate compilable string
     *
     * @return compilable string
     */
    @NotNull
    String toTypeString();

    /**
     * To check if this type is array
     *
     * @return if this type self is an array
     */
    boolean isArray();

    @Override
    @NotNull
    default String toExportableString() {
        return this.toTypeString();
    }

    @Override
    @NotNull
    default String toInvokerString() {
        return this.toTypeString();
    }

    @Override
    default boolean isStaticExportable() {
        return false;
    }

}
