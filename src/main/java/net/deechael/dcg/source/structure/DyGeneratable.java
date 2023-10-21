package net.deechael.dcg.source.structure;

import net.deechael.dcg.DyTranstringable;
import org.jetbrains.annotations.NotNull;

public interface DyGeneratable extends DyTranstringable {

    /**
     * Get the simple name of the generatable object without package name etc.
     *
     * @return simple name
     */
    @NotNull
    String getSimpleName();

}
