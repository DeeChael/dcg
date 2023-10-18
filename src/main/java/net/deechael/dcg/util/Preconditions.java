package net.deechael.dcg.util;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.DyUndefinedStructure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.regex.Pattern;

public final class Preconditions {

    @NotNull
    public static <T> T notNull(@Nullable T obj, String message) {
        if (obj == null)
            throw new RuntimeException(message);
        return obj;
    }

    @NotNull
    public static String regex(@NotNull String value, Pattern pattern, String message) {
        if (!pattern.matcher(value).matches())
            throw new IllegalArgumentException(message);
        return value;
    }

    public static void domain(@NotNull DyStructure current, @NotNull DyStructure another) {
        if (current instanceof DyUndefinedStructure)
            return;
        if (another == current)
            return;
        if (another instanceof DyUndefinedStructure)
            return;
        IllegalAccessException exception = new IllegalAccessException("This object is not accessible in this structure!");
        if (another.getParentDomains().length == 0)
            try {
                throw exception;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        if (Arrays.stream(another.getParentDomains()).toList().contains(current))
            return;
        try {
            throw exception;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Preconditions() {
    }

}
