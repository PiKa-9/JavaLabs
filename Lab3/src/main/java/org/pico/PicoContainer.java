package org.pico;

import org.fpm.di.Container;
import org.pico.exception.BindingNotFoundException;
import org.pico.exception.CyclicException;
import org.pico.exception.InjectConstructorNotFoundException;
import org.pico.exception.UnknownException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.util.*;

public class PicoContainer implements Container {
    private final GraphData graph;
    private List<Class<?>> currentClasses; // to handle cyclic dependency

    public PicoContainer(GraphData graph) {
        this.graph = graph;
    }

    public void clearCurrentClasses() {
        this.currentClasses = new ArrayList<>();
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        clearCurrentClasses();

        try {
            return _getComponent(clazz);
        } catch (BindingNotFoundException e) {
            System.out.println("The " + e.getMessage() + " has no binding. Can't create an instance");
            return null;
        } catch (InjectConstructorNotFoundException e) {
            System.out.println("Can't instantiate with default constructor, @Inject constructor not found in " + e.getMessage());
            return null;
        } catch (CyclicException e) {
            System.out.println("Cyclic dependency has occurred");
            return null;
        } catch (UnknownException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public <T> T _getComponent(Class<T> clazz) throws CyclicException, BindingNotFoundException, InjectConstructorNotFoundException, UnknownException {
        if (currentClasses.contains(clazz)) {
            throw new CyclicException();
        } else {
            currentClasses.add(clazz);
        }

        if (graph.hasSingleton(clazz)) {
            currentClasses.remove(currentClasses.size() - 1);
            return (T) graph.getSingleton(clazz);
        }
        if (clazz.isAnnotationPresent(Singleton.class)) {
            T instance = (T) createInstance(clazz);
            graph.setSingleton(clazz, instance);

            return _getComponent(clazz);
        }
        if (graph.hasImpl(clazz)) {
            return (T) _getComponent(graph.getClassImpl(clazz));
        }
        if (graph.hasOrdinaryClass(clazz)) {
            return (T) createInstance(clazz);
        }

        throw new BindingNotFoundException(clazz.getName());
    }

    private Object createInstance(Class<?> clazz) throws CyclicException, BindingNotFoundException, InjectConstructorNotFoundException, UnknownException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor<?> constructor: constructors) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                // Constructor with @Inject annotation was found -> initialize it
                List<Object> params = new ArrayList<>();
                for (Class<?> param: constructor.getParameterTypes()) {
                    params.add(this._getComponent(param));
                }

                try {
                    // If instantiation is successful, then remove 'clazz' from currentClasses
                    currentClasses.remove(currentClasses.size() - 1);
                    return constructor.newInstance(params.toArray());
                } catch (Exception e) {
                    throw new UnknownException("Unknown error. Can't create instance with " + constructor.getName());
                }
            }
        }

        if ((constructors.length == 1) && (constructors[0].getParameterCount() == 0)) {
            // There is a zero-argument constructor -> initialize it
            try {
                // If instantiation is successful, then remove 'clazz' from currentClasses
                currentClasses.remove(currentClasses.size() - 1);
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new UnknownException("Unknown error. Can't create instance of " + clazz.getName());
            }
        }

        // Constructor with @Inject annotation or with zero-arguments(the default one) wasn't found -> throw exception
        throw new InjectConstructorNotFoundException(clazz.getName());
    }
}
