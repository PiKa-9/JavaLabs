package org.fpm.di.example;

import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.pico.GraphData;
import org.pico.PicoBinder;
import org.pico.PicoContainer;

public class DummyEnvironment implements Environment {

    @Override
    public Container configure(Configuration configuration) {
        GraphData graph = new GraphData();
        PicoBinder binder = new PicoBinder(graph);
        configuration.configure(binder);

        return new PicoContainer(graph);
    }
}
