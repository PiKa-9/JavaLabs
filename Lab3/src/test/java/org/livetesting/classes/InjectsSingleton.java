package org.livetesting.classes;

import javax.inject.Inject;

public class InjectsSingleton {
    private MySingleton singleton;

    @Inject
    public InjectsSingleton(MySingleton singleton) {
        this.singleton = singleton;
    }

    public MySingleton getSingleton() {
        return singleton;
    }
}
