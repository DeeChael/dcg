package net.deechael.dcg.source.variable;

public enum Visibility {

    DEFAULT(""),
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected");

    private final String string;

    Visibility(String string) {
        this.string = string;
    }

    public String getVisibilityString() {
        return this.string;
    }

}
