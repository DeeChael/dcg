package net.deechael.dcg.source;

import net.deechael.dcg.source.variable.Visibility;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicSourcer {

    public final static String[] JVM_KEYWORDS = new String[] {
            "package", "class", "public", "private", "protected", "interface", "enum",
            "final", "abstract", "new", "while", "for", "do", "if", "else", "import",
            "return", "this",
    };

    private final Map<String, DyPackage> packages = new HashMap<>();
    final List<DyClass> classes = new ArrayList<>();

    public DynamicSourcer() {
    }

    public DyPackage newPackage(String name) {
        if (!this.packages.containsKey(name))
            this.packages.put(name, new DyPackage(this, name));
        return this.packages.get(name);
    }

    @NotNull
    public DyClass newClass(@NotNull Visibility visibility, @NotNull String name) {
        DyClass clazz = new DyClass(visibility, null, name);
        this.classes.add(clazz);
        return clazz;
    }

}
