package net.deechael.dcg.variable;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.DyUndefinedStructure;
import net.deechael.dcg.source.structure.invokation.Invoker;
import net.deechael.dcg.variable.internal.InvokeMethodVariable;
import net.deechael.dcg.variable.internal.SuperVariable;
import net.deechael.dcg.variable.internal.ThisVariable;
import net.deechael.dcg.variable.internal.jvm.NullVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Variable extends Invoker {

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

    @Override
    @NotNull
    default String toInvokerString() {
        return this.toVariableString();
    }

    static Variable nullVariable() {
        return NullVariable.INSTANCE;
    }

    static Variable invokeMethodVariable(@Nullable Invoker invoker, @NotNull String methodName, Variable... parameters) {
        return new InvokeMethodVariable(DyUndefinedStructure.INSTANCE, invoker, methodName, parameters);
    }

    static Variable superVariable(DyType type) {
        return new SuperVariable(DyUndefinedStructure.INSTANCE, type);
    }

    static Variable thisVariable(DyType type) {
        return new ThisVariable(DyUndefinedStructure.INSTANCE, type);
    }

}
