package net.deechael.dcg.util;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.DyUndefinedStructure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        if (domain0(current, another))
            return;
        try {
            throw new IllegalAccessException("This object is not accessible in this structure!");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean domain0(DyStructure current, DyStructure another) {
        if (current instanceof DyUndefinedStructure)
            return true;
        if (another == current)
            return true;
        if (another instanceof DyUndefinedStructure)
            return true;
        if (another.getParentDomains().length == 0)
            for (DyStructure parent : another.getParentDomains())
                if (domain0(current, parent))
                    return true;
        return false;
    }


    private Preconditions() {
    }

}
