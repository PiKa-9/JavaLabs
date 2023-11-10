package org.pico;

import org.fpm.di.Binder;

public class PicoBinder implements Binder {
    private GraphData graph;

    public PicoBinder(GraphData graph) {
        this.graph = graph;
    }

    @Override
    public <T> void bind(Class<T> clazz) { graph.addOrdinaryClass(clazz); }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        graph.setClassImpl(clazz, implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        graph.setSingleton(clazz, instance);
    }
}
