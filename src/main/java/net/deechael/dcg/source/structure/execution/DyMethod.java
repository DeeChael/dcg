package net.deechael.dcg.source.structure.execution;

import net.deechael.dcg.source.structure.DyAnnotatable;
import net.deechael.dcg.source.structure.DyGenericable;
import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.DyVisible;
import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.type.GenericType;
import net.deechael.dcg.source.variable.JvmVariable;
import net.deechael.dcg.source.variable.Visibility;
import net.deechael.dcg.util.StringCompiler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DyMethod extends DyExecutable implements DyVisible, DyAnnotatable, DyGenericable {

    private final DyStructure[] parentDomains;
    private final Visibility visibility;
    private final Map<DyType, Map<String, JvmVariable>> annotations = new HashMap<>();

    private final DyType returnType;

    public DyMethod(DyStructure[] parentDomains, Visibility visibility, DyType returnType) {
        this.parentDomains = parentDomains;
        this.visibility = visibility;
        this.returnType = returnType;
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        if (!this.annotations.isEmpty())
            builder.append(StringCompiler.compileAnnotations(this.listAnnotations()));
        if (this.visibility != Visibility.DEFAULT)
            builder.append(this.visibility.getVisibilityString())
                    .append(" ");

        return null;
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
    public @NotNull DyStructure[] getParentDomains() {
        return this.parentDomains;
    }

    @Override
    public GenericType addGeneric(@NotNull String typeName, @Nullable DyType extending) {
        return null;
    }

    @Override
    public List<Map.Entry<GenericType, Optional<DyType>>> listGenerics() {
        return null;
    }
}
