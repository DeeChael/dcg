package net.deechael.dcg.compiler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class DynamicClassLoader extends ClassLoader {

    private final List<ClassLoader> contextLoaders;
    private final Map<String, Class<?>> generatedClasses = new HashMap<>();

    public DynamicClassLoader(List<ClassLoader> classLoaders) {
        this.contextLoaders = classLoaders;
    }

    @Override
    public Class<?> loadClass(String name) {
        if (this.generatedClasses.containsKey(name)) {
            return this.generatedClasses.get(name);
        }
        for (ClassLoader classLoader : this.contextLoaders) {
            try {
                return classLoader.loadClass(name);
            } catch (ClassNotFoundException ignored) {
            }
        }
        try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        if (this.generatedClasses.containsKey(name)) {
            return this.generatedClasses.get(name);
        }
        for (ClassLoader loader : this.contextLoaders) {
            try {
                Method method = ClassLoader.class.getDeclaredMethod("findClass", String.class);
                method.setAccessible(true);
                return (Class<?>) method.invoke(loader, name);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
            }
        }
        try {
            return super.findClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Class<?> generate0(String className, byte[] bytes) {
        Class<?> clazz = defineClass(className, bytes, 0, bytes.length);
        this.generatedClasses.put(className, clazz);
        return clazz;
    }

}
