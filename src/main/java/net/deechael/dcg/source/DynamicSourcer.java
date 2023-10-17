package net.deechael.dcg.source;

import net.deechael.dcg.variable.Visibility;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DynamicSourcer {

    public final static String[] JVM_KEYWORDS = new String[] {
            "package", "class", "public", "private", "protected", "interface", "enum",
            "final", "abstract", "new", "while", "for", "do", "if", "else", "import",
            "return", "this",
    };

    private final List<DyPackage> packages = new ArrayList<>();
    final List<DyClass> classes = new ArrayList<>();

    public DynamicSourcer() {
    }

    public DyPackage newPackage(String name) {
        return new DyPackage(this, name);
    }

    @NotNull
    public DyClass newClass(@NotNull Visibility visibility, @NotNull String name) {
        DyClass clazz = new DyClass(visibility, null, name);
        this.classes.add(clazz);
        return clazz;
    }

}
