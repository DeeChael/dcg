package net.deechael.dcg.source.structure;

import net.deechael.dcg.source.DyPackage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DySource extends DyVisible, DyGeneratable {

    /**
     * Get package of this class
     *
     * @return package name
     */
    @NotNull
    String getPackageName();

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

    @Nullable
    DyPackage getPackage();


}
