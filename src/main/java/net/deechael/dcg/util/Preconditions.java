package net.deechael.dcg.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Preconditions {

    @NotNull
    public static <T> T notNull(@Nullable T obj, String message) {
        if (obj == null)
            throw new RuntimeException(message);
        return obj;
    }

    private Preconditions() {
    }

}
