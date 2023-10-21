package net.deechael.dcg.source.structure.selection;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.execution.DyExecutable;
import net.deechael.dcg.source.structure.execution.DySwitchExecutable;
import net.deechael.dcg.source.structure.invokation.Invokation;
import net.deechael.dcg.source.variable.Variable;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SwitchCaseSelection implements Invokation {

    private final DyStructure parent;

    private final Variable selector;
    private final List<Map.Entry<Variable[], DyExecutable>> cases = new ArrayList<>();

    private DySwitchExecutable defaultExecutable = null;

    public SwitchCaseSelection(DyStructure parent, Variable selector) {
        this.parent = parent;
        this.selector = selector;
    }

    public SwitchCaseSelection doCase(Variable variable, Consumer<DySwitchExecutable> invokation) {
        return this.doCase(new Variable[]{variable}, invokation);
    }

    public SwitchCaseSelection doCase(Variable[] variables, Consumer<DySwitchExecutable> invokation) {
        DySwitchExecutable executable = new DySwitchExecutable(new DyStructure[]{this.parent});
        invokation.accept(executable);
        this.cases.add(new AbstractMap.SimpleEntry<>(variables, executable));
        return this;
    }

    public SwitchCaseSelection doDefault(Consumer<DySwitchExecutable> invokation) {
        DySwitchExecutable executable = new DySwitchExecutable(new DyStructure[]{this.parent});
        invokation.accept(executable);
        this.defaultExecutable = executable;
        return this;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        builder.append("switch")
                .append(" ")
                .append("(")
                .append(this.selector.toVariableString())
                .append(")")
                .append(" ")
                .append("{")
                .append("\n");
        for (Map.Entry<Variable[], DyExecutable> entry : cases) {
            for (Variable variable : entry.getKey()) {
                builder.append("case")
                        .append(" ")
                        .append(variable.toVariableString())
                        .append(":")
                        .append("\n");
            }
            builder.append(entry.getValue().toCompilableString())
                    .append("\n");
        }
        if (this.defaultExecutable != null)
            builder.append("default")
                    .append(":")
                    .append(this.defaultExecutable.toCompilableString())
                    .append("\n");
        builder.append("}");
        return builder.toString();
    }

}
