package net.deechael.dcg.source.structure.container;

import org.jetbrains.annotations.NotNull;

public interface DyConstructorContainer {

    /**
     * Get the full name of the container
     * @return full name
     */
    String getName();


    /**
     * Get the simple name of the container object without package name etc.
     *
     * @return simple name
     */
    @NotNull
    String getSimpleName();

}
