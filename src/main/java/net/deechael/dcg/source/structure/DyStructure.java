package net.deechael.dcg.source.structure;

import org.jetbrains.annotations.NotNull;

public interface DyStructure {

    /**
     * Get parents domains to which this structure object belongs
     * @return parent domains
     */
    @NotNull
    DyStructure[] getParentDomains();

    default boolean isStatic() {
        return false;
    }

}
