package net.deechael.dcg.variable;

import net.deechael.dcg.structure.DyStructure;
import org.jetbrains.annotations.NotNull;

public interface Variable {

    /**
     * Get the type of this variable
     *
     * @return variable type
     */
    @NotNull
    DyType getType();

    /**
     * Get the name of the variable, only exists if it's a defined variable
     * @return name, exception when not exists
     */
    String getName();

    /**
     * Get the domain of this variable, defining the availability of the variable
     *
     * @return domain
     */
    @NotNull
    DyStructure getDomain();

    /**
     * Generate compilable string for compiling use
     *
     * @return compilable string
     */
    String toVariableString();

}
