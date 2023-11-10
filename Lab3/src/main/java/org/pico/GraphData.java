package org.pico;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphData {
    private final Map<Class<?>, Class<?>> classMap = new HashMap<>();
    private final Set<Class<?>> classes = new HashSet<>();
    private final Map<Class<?>, Object> singletonClasses = new HashMap<>();

    public boolean hasImpl(Class<?> clazz) {
        return classMap.containsKey(clazz);
    }
    public boolean hasSingleton(Class<?> clazz) {
        return singletonClasses.containsKey(clazz);
    }
    public boolean hasOrdinaryClass(Class<?> clazz) {
        return classes.contains(clazz);
    }

    public Class<?> getClassImpl(Class<?> clazz) { return classMap.get(clazz); }
    public Object getSingleton(Class<?> clazz) {
        return singletonClasses.get(clazz);
    }


    public void setClassImpl(Class<?> clazz, Class<?> impl) {
        classMap.put(clazz, impl);
    }
    public void addOrdinaryClass(Class<?> clazz) {
        classes.add(clazz);
    }
    public <T> void setSingleton(Class<T> clazz, T instance) {
        singletonClasses.put(clazz, instance);
    }
}
