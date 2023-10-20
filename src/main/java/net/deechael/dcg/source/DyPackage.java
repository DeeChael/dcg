package net.deechael.dcg.source;

import net.deechael.dcg.util.Preconditions;
import net.deechael.dcg.source.variable.Visibility;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DyPackage {

    private final static Pattern ILLEGAL_PACKAGE_REGEX = Pattern.compile("[a-zA-Z$_][a-zA-Z0-9$_]*(\\.[a-zA-Z$_][a-zA-Z0-9$_]*)*");

    private final DynamicSourcer sourcer;
    private final String name;
    private final List<DyClass> classes = new ArrayList<>();

    DyPackage(DynamicSourcer sourcer, String name) {
        Preconditions.regex(name, ILLEGAL_PACKAGE_REGEX, "The value didn't match the regex");

        this.sourcer = sourcer;
        this.name = name;
    }

    @NotNull
    public DyClass newClass(@NotNull Visibility visibility, @NotNull String name) {
        DyClass clazz = new DyClass(visibility, this, name);
        this.classes.add(clazz);
        this.sourcer.classes.add(clazz);
        return clazz;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

}
