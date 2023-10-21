package net.deechael.dcg.compile;

import net.deechael.dcg.source.structure.DyGeneratable;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

final class DynamicStringObject extends SimpleJavaFileObject {

    private final DyGeneratable generatable;
    private final String content;

    public DynamicStringObject(DyGeneratable generatable, URI uri, Kind kind, String content) {
        super(uri, kind);
        this.generatable = generatable;
        this.content = content;
    }

    public DyGeneratable getGeneratable() {
        return generatable;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return content;
    }

}
