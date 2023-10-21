package net.deechael.dcg.source.structure.method;

import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.variable.Visibility;
import net.deechael.dcg.util.StringCompiler;

public class DyClassMethod extends DyExecutableMethod {

    private boolean isFinal = false;
    private boolean isNative = false;
    private boolean isSynchronized = false;

    public DyClassMethod(DyStructure[] parentDomains, Visibility visibility, DyType returnType, String name) {
        super(parentDomains, visibility, returnType, name);
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setNative(boolean isNative) {
        this.isNative = isNative;
    }

    public boolean isNative() {
        return isNative;
    }

    public void setSynchronized(boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    public boolean isSynchronized() {
        return isSynchronized;
    }

    @Override
    public String toCompilableString() {
        StringBuilder builder = new StringBuilder();
        if (!this.annotations.isEmpty())
            builder.append(StringCompiler.compileAnnotations(this.listAnnotations()));
        if (this.visibility != Visibility.DEFAULT)
            builder.append(this.visibility.getVisibilityString())
                    .append(" ");
        if (this.isFinal())
            builder.append("final")
                    .append(" ");
        if (this.isStatic())
            builder.append("static")
                    .append(" ");
        if (this.isSynchronized())
            builder.append("synchronized")
                    .append(" ");
        if (this.isNative()) {
            builder.append("native")
                    .append(" ");
            if (this.getReturnType() != null)
                builder.append(this.getReturnType().toTypeString());
            else
                builder.append("void");
            builder.append(" ")
                    .append(this.getName())
                    .append("(")
                    .append(StringCompiler.compileParameters(this))
                    .append(")")
                    .append(";");
        } else {
            if (this.getReturnType() != null)
                builder.append(this.getReturnType().toTypeString());
            else
                builder.append("void");
            builder.append(" ")
                    .append(this.getName())
                    .append("(")
                    .append(StringCompiler.compileParameters(this))
                    .append(")")
                    .append(" ")
                    .append("{")
                    .append("\n");
            builder.append(StringCompiler.compileInvokations(this.listInvokations()))
                    .append("\n")
                    .append("}");
        }
        return builder.toString();
    }

}
