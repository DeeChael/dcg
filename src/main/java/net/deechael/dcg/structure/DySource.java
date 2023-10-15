package net.deechael.dcg.structure;

import org.jetbrains.annotations.NotNull;

public interface DySource extends DyVisible, DyGeneratable {

    /**
     * Get package of this class
     *
     * @return package name
     */
    @NotNull
    String getPackage();

    /**
     * Get full name of this class
     *
     * @return class full name
     */
    @NotNull
    String getName();

    /**
     * Get simple name of this class
     *
     * @return class simple name
     */
    @NotNull
    String getSimpleName();


}
