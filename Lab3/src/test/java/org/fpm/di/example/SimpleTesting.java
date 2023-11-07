package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.Environment;

public class SimpleTesting {
    private Container container;

    public static void main(String[] args) {
        SimpleTesting t = new SimpleTesting();
        t.setUp();
        System.out.println("singleton");
        System.out.println(t.container.getComponent(MySingleton.class));
        System.out.println(t.container.getComponent(MySingleton.class));

        System.out.println("\nprototype");
        System.out.println(t.container.getComponent(MyPrototype.class));
        System.out.println(t.container.getComponent(MyPrototype.class));
    }

    public void setUp() {
        Environment env = new DummyEnvironment();
        container = env.configure(new MyConfiguration());
    }

}
