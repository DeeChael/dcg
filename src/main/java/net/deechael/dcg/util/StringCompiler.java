package net.deechael.dcg.util;

import net.deechael.dcg.structure.importation.DyExportable;
import net.deechael.dcg.variable.DyType;
import net.deechael.dcg.variable.JvmVariable;

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
                    .map( value -> new StringBuilder()
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
        StringBuilder builder = new StringBuilder();
        // TODO
        return builder.toString();
    }

    private StringCompiler() {
    }

}
