package net.deechael.dcg.source.structure.selection;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.execution.DyExecutable;
import net.deechael.dcg.source.structure.execution.DyInnerExecutable;
import net.deechael.dcg.source.structure.invokation.Invokation;
import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.variable.Variable;
import net.deechael.dcg.source.variable.internal.ReferringVariable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class TryCatchSelection implements Invokation {

    private final DyStructure parent;

    private final List<Map.Entry<Variable, Variable>> initialVariables = new ArrayList<>();

    private final DyExecutable tryExecutable;
    private final List<Map.Entry<Map.Entry<List<DyType>, String>, DyExecutable>> catchesExecutable = new ArrayList<>();
    private DyExecutable finallyExecutable = null;

    public TryCatchSelection(DyStructure parent) {
        this.parent = parent;

        this.tryExecutable = new DyInnerExecutable(new DyStructure[]{parent});
    }

    public TryCatchSelection doTry(Consumer<DyExecutable> invokation) {
        this.tryExecutable.clearInvokations();
        invokation.accept(this.tryExecutable);
        return this;
    }

    public TryCatchSelection doCatch(BiConsumer<DyExecutable, Variable> invokation, String throwableParamterName, DyType... throwableTypes) {
        if (throwableTypes.length == 0)
            throw new IllegalArgumentException("Throwable types must be over at least 1!");
        DyExecutable catchExecutable = new DyInnerExecutable(new DyStructure[]{parent});
        ReferringVariable variable = new ReferringVariable(catchExecutable, null, throwableParamterName);
        invokation.accept(catchExecutable, variable);
        this.catchesExecutable.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(List.of(throwableTypes), throwableParamterName), catchExecutable));
        return this;
    }

    public TryCatchSelection doFinally(Consumer<DyExecutable> invokation) {
        DyExecutable finallyExecutable = new DyInnerExecutable(new DyStructure[]{parent});
        invokation.accept(finallyExecutable);
        this.finallyExecutable = finallyExecutable;
        return this;
    }

    public ReferringVariable initialize(DyType type, String name, Variable value) {
        ReferringVariable variable = new ReferringVariable(this.tryExecutable, type, name);
        this.initialVariables.add(new AbstractMap.SimpleEntry<>(variable, value));
        return variable;
    }

    @Override
    public String toCompilableString() {
        if (this.finallyExecutable == null && this.catchesExecutable.isEmpty())
            throw new IllegalCallerException("Try-Catch must have at one catch or one finally");
        StringBuilder builder = new StringBuilder();
        builder.append("try")
                .append(" ");
        if (!this.initialVariables.isEmpty()) {
            builder.append("(")
                    .append(String.join(
                            "; ",
                            this.initialVariables.stream()
                                    .map( entry -> {
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append(entry.getKey().getType().toTypeString())
                                                .append(" ")
                                                .append(entry.getKey().getName())
                                                .append(" ")
                                                .append("=")
                                                .append(" ")
                                                .append(entry.getValue().toVariableString());
                                        return stringBuilder.toString();
                                    })
                                    .toList()
                                    .toArray(new String[0])
                            )
                    )
                    .append(")")
                    .append(" ");
        }
        builder.append("{")
                .append("\n")
                .append(this.tryExecutable.toCompilableString())
                .append("\n")
                .append("}");
        Iterator<Map.Entry<Map.Entry<List<DyType>, String>, DyExecutable>> iterator = this.catchesExecutable.iterator();
        for (Map.Entry<Map.Entry<List<DyType>, String>, DyExecutable> entry = iterator.next(); iterator.hasNext(); entry = iterator.next()) {
            builder.append(" ")
                    .append("catch")
                    .append(" ")
                    .append("(")
                    .append(String.join(
                            " | ",
                            entry.getKey().getKey().stream()
                                    .map(DyType::toTypeString)
                                    .toList()
                                    .toArray(new String[0])
                    ))
                    .append(" ")
                    .append(entry.getKey().getValue())
                    .append(")")
                    .append(" ")
                    .append("{")
                    .append("\n")
                    .append(entry.getValue().toCompilableString())
                    .append("\n")
                    .append("}");
        }
        if (this.finallyExecutable != null)
            builder.append(" ")
                    .append("fiannly")
                    .append(" ")
                    .append("{")
                    .append("\n")
                    .append(this.finallyExecutable.toCompilableString())
                    .append("\n")
                    .append("}");
        return builder.toString();
    }
}
