package net.deechael.dcg.source.structure.execution;

import net.deechael.dcg.DyTranstringable;
import net.deechael.dcg.source.structure.DyLabel;
import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.invokation.DyInvokable;
import net.deechael.dcg.source.structure.invokation.Invokation;
import net.deechael.dcg.source.structure.invokation.Invoker;
import net.deechael.dcg.source.structure.invokation.internal.CreateLabelInvokation;
import net.deechael.dcg.source.structure.invokation.internal.CreateVariableInvokation;
import net.deechael.dcg.source.structure.invokation.internal.InvokeMethodInvokation;
import net.deechael.dcg.source.structure.invokation.internal.ModifyVariableInvokation;
import net.deechael.dcg.source.structure.selection.IfElseSelection;
import net.deechael.dcg.source.structure.selection.TryCatchSelection;
import net.deechael.dcg.util.Preconditions;
import net.deechael.dcg.source.variable.DyType;
import net.deechael.dcg.source.variable.Variable;
import net.deechael.dcg.source.variable.internal.InvokeMethodVariable;
import net.deechael.dcg.source.variable.internal.ReferringVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class DyExecutable implements DyInvokable, DyStructure, DyTranstringable {

    private final List<Invokation> invokations = new ArrayList<>();

    @NotNull
    public CreateVariableInvokation createVariable(@NotNull DyType type, @NotNull String name, @NotNull Variable variable) {
        CreateVariableInvokation invokation = new CreateVariableInvokation(type, name, variable, new ReferringVariable(this, type, name));
        this.invokations.add(invokation);
        return invokation;
    }

    public void modifyVariable(Variable referring, @Nullable Variable newValue) {
        Preconditions.domain(this, referring.getDomain());
        if (newValue != null)
            Preconditions.domain(this, newValue.getDomain());

        ModifyVariableInvokation invokation = new ModifyVariableInvokation(referring, newValue != null ? newValue : Variable.nullVariable());
        this.invokations.add(invokation);
    }

    public DyLabel createLabel(String name) {
        this.invokations.add(new CreateLabelInvokation(name));
        return new DyLabel(name);
    }

    public InvokeMethodVariable invoke(@Nullable Invoker invoker, @NotNull String methodName, Variable... parameters) {
        if (invoker instanceof Variable invokerVariable)
            Preconditions.domain(this, invokerVariable.getDomain());
        for (Variable parameter : parameters)
            Preconditions.domain(this, parameter.getDomain());

        InvokeMethodVariable variable = new InvokeMethodVariable(this, invoker, methodName, parameters);

        this.invokations.add(new InvokeMethodInvokation(variable));

        return variable;
    }

    public IfElseSelection ifElse() {
        IfElseSelection selection = new IfElseSelection(this);
        this.invokations.add(selection);
        return selection;
    }

    public TryCatchSelection tryCatch() {
        TryCatchSelection selection = new TryCatchSelection(this);
        this.invokations.add(selection);
        return selection;
    }

    @Override
    public void addInvokation(Invokation invokation) {
        this.invokations.add(invokation);
    }

    @Override
    public @NotNull List<Invokation> listInvokations() {
        return this.invokations;
    }

    @Override
    public void clearInvokations() {
        this.invokations.clear();
    }

}
