package org.picotesting.classes;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SingletonWithInjection {
    private final Prototype dependency;

    @Inject
    public SingletonWithInjection(Prototype p) {
        this.dependency = p;
    }

    public Prototype getDependency() {
        return dependency;
    }
}
