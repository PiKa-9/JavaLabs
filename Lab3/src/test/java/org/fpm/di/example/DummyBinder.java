package org.fpm.di.example;

import org.fpm.di.Binder;

import java.util.HashMap;
import java.util.Map;

public class DummyBinder implements Binder {
    private final Map<Class<?>, Object> binding = new HashMap<>();

    @Override
    public <T> void bind(Class<T> clazz) { binding.put(clazz, null); }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        binding.put(clazz, implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        binding.put(clazz, instance);
    }

    public <T> Object resolve(Class<T> clazz) {
        Object obj = binding.get(clazz);

        if (obj == null) {
            return clazz;
        }
        if (!(obj instanceof Class<?>)) {
            // Object is an instance
            return obj;
        } else {
            return resolve((Class<?>) obj);
        }
    }
}
