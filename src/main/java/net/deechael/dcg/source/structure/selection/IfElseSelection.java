package net.deechael.dcg.source.structure.selection;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.execution.DyExecutable;
import net.deechael.dcg.source.structure.execution.DyInnerExecutable;
import net.deechael.dcg.source.structure.invokation.Invokation;
import net.deechael.dcg.source.structure.requirement.Requirement;

import java.util.*;
import java.util.function.Consumer;

public class IfElseSelection implements Invokation {

    private final DyStructure parent;

    private final List<Map.Entry<Requirement, DyExecutable>> ifs = new ArrayList<>();
    private DyExecutable _else = null;

    public IfElseSelection(DyStructure parent) {
        this.parent = parent;
    }

    public IfElseSelection doIf(Requirement requirement, Consumer<DyExecutable> invokation) {
        DyInnerExecutable executable = new DyInnerExecutable(new DyStructure[]{parent});
        invokation.accept(executable);
        this.ifs.add(new AbstractMap.SimpleEntry<>(requirement, executable));
        return this;
    }

    public IfElseSelection doElse(Consumer<DyExecutable> invokation) {
        DyInnerExecutable executable = new DyInnerExecutable(new DyStructure[]{parent});
        invokation.accept(executable);
        this._else = executable;
        return this;
    }

    @Override
    public String toCompilableString() {
        if (this.ifs.isEmpty())
            throw new IllegalCallerException("There is no if in selection!");
        StringBuilder builder = new StringBuilder();
        Iterator<Map.Entry<Requirement, DyExecutable>> iterator = this.ifs.iterator();
        if (iterator.hasNext())
            for (Map.Entry<Requirement, DyExecutable> entry = iterator.next(); iterator.hasNext(); entry = iterator.next()) {
                builder.append("if")
                        .append(" ")
                        .append("(")
                        .append(entry.getKey().toRequirementString())
                        .append(")")
                        .append(" ")
                        .append("{")
                        .append("\n")
                        .append(entry.getValue().toCompilableString())
                        .append("\n")
                        .append("}");
                if (iterator.hasNext())
                    builder.append(" ")
                            .append("else")
                            .append(" ");
            }
        if (this._else != null) {
            builder.append(" ")
                    .append("else")
                    .append(" ")
                    .append("{")
                    .append(this._else.toCompilableString())
                    .append("\n")
                    .append("}");
        }
        builder.append("\n");
        return builder.toString();
    }

}
