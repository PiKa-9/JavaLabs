package org.picotesting.classes;

import javax.inject.Inject;

public class InjectA {
    private final A dependency;

    @Inject
    public InjectA(A a) {
        this.dependency = a;
    }

    public A getDependency() {
        return dependency;
    }
}
