package net.deechael.dcg.source.structure;

import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.variable.JvmVariable;
import net.deechael.dcg.source.variable.internal.ReferringVariable;
import net.deechael.dcg.util.StringCompiler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DyParameter extends ReferringVariable implements DyAnnotatable {

    protected final Map<DyType, Map<String, JvmVariable>> annotations = new HashMap<>();

    public DyParameter(DyStructure structure, DyType type, String name) {
        super(structure, type, name);
    }

    @Override
    public void annotate(@NotNull DyType annotationType, @Nullable Map<String, JvmVariable> values) {
        this.annotations.put(annotationType, values != null ? values : new HashMap<>());
    }

    @Override
    public @NotNull Map<DyType, Map<String, JvmVariable>> listAnnotations() {
        return this.annotations;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        if (!this.annotations.isEmpty())
            builder.append(StringCompiler.compileAnnotations(this.annotations))
                    .append(" ");
        builder.append(this.getType().toTypeString())
                .append(" ")
                .append(this.getName());
        return builder.toString();
    }

}
