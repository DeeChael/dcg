package net.deechael.dcg.util;

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

    public static String regex(String value, Pattern pattern, String message) {
        if (!pattern.matcher(value).matches())
            throw new IllegalArgumentException(message);
        return value;
    }

    private Preconditions() {
    }

}
