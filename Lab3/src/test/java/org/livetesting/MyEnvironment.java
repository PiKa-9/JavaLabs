package org.livetesting;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.pico.GraphData;
import org.pico.PicoBinder;
import org.pico.PicoContainer;

public class MyEnvironment implements org.fpm.di.Environment {

    @Override
    public Container configure(Configuration configuration) {
        GraphData graph = new GraphData();
        Binder binder = new PicoBinder(graph);
        configuration.configure(binder);

        return new PicoContainer(graph);
    }
}
