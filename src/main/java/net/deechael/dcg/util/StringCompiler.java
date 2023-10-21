package net.deechael.dcg.util;

import net.deechael.dcg.source.structure.DyGenericable;
import net.deechael.dcg.source.structure.DyParameter;
import net.deechael.dcg.source.structure.DyParameterable;
import net.deechael.dcg.source.structure.importation.DyExportable;
import net.deechael.dcg.source.structure.invokation.Invokation;
import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.type.GenericType;
import net.deechael.dcg.source.variable.JvmVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class StringCompiler {

    public static String compileAnnotations(Map<DyType, Map<String, JvmVariable>> annotations) {
        StringBuilder builder = new StringBuilder();
        if (annotations.isEmpty())
            return "";
        for (Map.Entry<DyType, Map<String, JvmVariable>> entry : annotations.entrySet()) {
            builder.append("@").append(entry.getKey().toTypeString());
            if (entry.getValue().isEmpty()) {
                builder.append("\n");
                continue;
            }
            builder.append("(");
            builder.append(String.join(", ", entry.getValue()
                    .entrySet()
                    .stream()
                    .map(value -> new StringBuilder()
                            .append(value.getKey())
                            .append("=")
                            .append(value.getValue().toVariableString())
                    )
                    .map(StringBuilder::toString)
                    .toList()
                    .toArray(new String[0])));
            builder.append(")\n");
        }
        return builder.append(" ").toString();
    }

    public static String compileImports(Iterable<DyExportable> imports) {
        List<DyExportable> sortedExportables = new ArrayList<>();
        for (DyExportable exportable : imports)
            sortedExportables.add(exportable);
        sortedExportables.sort((o1, o2) -> {
            if (o1.isStaticExportable() && !o2.isStaticExportable())
                return 1;
            else if (!o1.isStaticExportable() && o2.isStaticExportable())
                return -1;
            return 0;
        });
        StringBuilder builder = new StringBuilder();
        for (DyExportable exportable : imports) {
            builder.append("import")
                    .append(" ");
            if (exportable.isStaticExportable())
                builder.append("static")
                        .append(" ");
            builder.append(exportable.toExportableString())
                    .append(";")
                    .append("\n");
        }
        return builder.toString();
    }

    public static String compileInvokations(Iterable<Invokation> invokations) {
        StringBuilder builder = new StringBuilder();
        Iterator<Invokation> iterator = invokations.iterator();
        for (Invokation invokation = iterator.next(); iterator.hasNext(); invokation = iterator.next()) {
            builder.append(invokation.toCompilableString())
                    .append("\n");
        }
        return builder.toString();
    }

    public static String compileGenericables(DyGenericable genericable) {
        StringBuilder builder = new StringBuilder();
        builder.append("<");
        Iterator<GenericType> iterator = genericable.listGenerics().iterator();
        if (iterator.hasNext())
            for (GenericType type = iterator.next(); iterator.hasNext(); type = iterator.next()) {
                builder.append(type.toGenericTypeString());
                if (iterator.hasNext())
                    builder.append(",")
                            .append(" ");
            }
        builder.append(">");
        return builder.toString();
    }

    public static String compileParameters(DyParameterable parameterable) {
        StringBuilder builder = new StringBuilder();
        Iterator<DyParameter> iterator = parameterable.listParameters().iterator();
        if (iterator.hasNext())
            for (DyParameter parameter = iterator.next(); iterator.hasNext(); parameter = iterator.next()) {
                builder.append(parameter.toCompilableString());
                if (iterator.hasNext())
                    builder.append(", ");
            }
        return builder.toString();
    }

    private StringCompiler() {
    }

}
