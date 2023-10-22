package net.deechael.dcg.source.structure;

import net.deechael.dcg.source.structure.importation.DyExportable;
import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.variable.JvmVariable;
import net.deechael.dcg.source.variable.Variable;
import net.deechael.dcg.source.variable.Visibility;
import net.deechael.dcg.util.StringCompiler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DyField implements Variable, DyAnnotatable, DyVisible, DyExportable {

    private final DySource domain;
    private final Visibility visibility;
    protected final Map<DyType, Map<String, JvmVariable>> annotations = new HashMap<>();

    private final DyType type;
    private final String name;

    private boolean isFinal = false;
    private boolean isStatic = false;

    private Variable initialization = null;

    public DyField(DySource domain, Visibility visibility, DyType type, String name) {
        this.domain = domain;
        this.visibility = visibility;

        this.type = type;
        this.name = name;
    }

    public void initialize(@NotNull Variable variable) {
        this.initialization = variable;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
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
    public @NotNull DyType getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public @NotNull DyStructure getDomain() {
        return this.domain;
    }

    @Override
    public String toVariableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.domain.getName())
                .append(".");
        if (!this.isStatic())
            builder.append("this")
                    .append(".");
        builder.append(this.getName());
        return builder.toString();
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public @NotNull String toExportableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.domain.getName())
                .append(".")
                .append(this.getName());
        return builder.toString();
    }

    @Override
    public boolean isStaticExportable() {
        return this.isStatic;
    }

    @Override
    public boolean isExportable() {
        return this.isStatic;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        builder.append(StringCompiler.compileAnnotations(this.annotations))
                .append("\n");
        if (this.visibility != Visibility.DEFAULT)
            builder.append(visibility.getVisibilityString())
                    .append(" ");
        if (this.isFinal())
            builder.append("final")
                    .append(" ");
        if (this.isStatic())
            builder.append("static")
                    .append(" ");
        builder.append(this.type.toTypeString())
                .append(" ")
                .append(this.name);
        if (this.initialization != null)
            builder.append(" ")
                    .append("=")
                    .append(" ")
                    .append(this.initialization.toVariableString());
        builder.append(";");
        return builder.toString();
    }

}
