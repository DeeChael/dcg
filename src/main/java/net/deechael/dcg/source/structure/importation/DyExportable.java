package net.deechael.dcg.source.structure.importation;

import org.jetbrains.annotations.NotNull;

public interface DyExportable {

    /**
     * Get compilable string
     *
     * @return compilable string
     */
    @NotNull
    String toExportableString();

    boolean isStaticExportable();

    default boolean isExportable() {
        return true;
    }

}
