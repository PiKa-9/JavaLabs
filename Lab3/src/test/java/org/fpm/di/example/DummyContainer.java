package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContainer implements Container {
    private DummyBinder binder;
    private Map<Class<?>, Object> singleTonInstances;

    public DummyContainer(DummyBinder binder) {
        this.binder = binder;
        this.singleTonInstances = new HashMap<>();
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        Object boundObj = binder.resolve(clazz);

        if (boundObj instanceof Class<?>) {
            Class<?> obj = (Class<?>) boundObj;

            if (singleTonInstances.containsKey(obj)) {
                return (T) singleTonInstances.get(obj);
            }

            T instance = (T) createInstance(obj);
            if (obj.isAnnotationPresent(Singleton.class)) {
                singleTonInstances.put(obj, instance);
            }
            return instance;
        } else {
            // Bound object is an instance of type T
            return (T) boundObj;
        }
    }

    private Object createInstance(Class<?> clazz) {
        for (Constructor<?> constructor: clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                // Constructor with @Inject annotation was found -> init it
                List<Object> params = new ArrayList<>();
                for (Class<?> param: constructor.getParameterTypes()) {
                    params.add(this.getComponent(param));
                }

                try {
                    return constructor.newInstance(params.toArray());
                } catch (Exception e) {
                    return null;
                }
            }
        }

        // There are no constructors with @Inject annotation was found -> init default one
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
