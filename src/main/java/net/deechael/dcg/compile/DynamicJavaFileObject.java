package net.deechael.dcg.compile;

import net.deechael.dcg.source.structure.DyGeneratable;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;

final class DynamicJavaFileObject extends SimpleJavaFileObject {

    private DyGeneratable generatable;
    private final ByteArrayOutputStream byteArrayOutputStream;

    DynamicJavaFileObject(DyGeneratable generatable, String className, Kind kind) {
        super(URI.create(className + kind.extension), kind);
        this.generatable = generatable;
        this.byteArrayOutputStream = new ByteArrayOutputStream();
    }

    public DyGeneratable getGeneratable() {
        return generatable;
    }

    @Override
    public OutputStream openOutputStream() {
        return byteArrayOutputStream;
    }

    public byte[] getBytes() {
        return this.byteArrayOutputStream.toByteArray();
    }

}
