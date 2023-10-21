package net.deechael.dcg.compile;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.util.ArrayList;
import java.util.List;

final class DynamicJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private final List<DynamicJavaFileObject> cache = new ArrayList<>();
    private DynamicJavaFileObject javaFileObject;

    public DynamicJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    public DynamicJavaFileObject getLastJavaFileObject() {
        this.cache.remove(this.javaFileObject);
        return javaFileObject;
    }

    public List<DynamicJavaFileObject> readCache() {
        List<DynamicJavaFileObject> caches = new ArrayList<>(this.cache);
        this.cache.clear();
        return caches;
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
        DynamicJavaFileObject javaFileObject = new DynamicJavaFileObject(((DynamicStringObject) sibling).getGeneratable(), className, kind);
        this.cache.add(javaFileObject);
        return this.javaFileObject = javaFileObject;
    }

}
