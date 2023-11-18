package org.livetesting;


import org.fpm.di.Environment;
import org.livetesting.classes.*;
import org.pico.PicoContainer;

public class Testing {
    private PicoContainer container;

    public static void main(String[] args) {
        Testing t = new Testing();
        t.setUp();
        t.run0();
        // t.run1();
        // t.run2();
        // t.run3();
    }

    public void setUp() {
        Environment env = new MyEnvironment();
        container = (PicoContainer) env.configure(new MyConfiguration());
    }

    /* Write the logic here */
    public void run0() {
        A result = container.getComponent(A.class);
        B result2 = container.getComponent(B.class);

        System.out.println(result);
        System.out.println(result2);
    }

    public void run1() {
        MySingleton singleton1 = container.getComponent(MySingleton.class);
        MySingleton singleton2 = container.getComponent(MySingleton.class);

        System.out.println(singleton1);
        System.out.println(singleton2);
    }

    public void run2() {
        InjectsSingleton result = container.getComponent(InjectsSingleton.class);
        InjectsSingleton result2 = container.getComponent(InjectsSingleton.class);

        System.out.println(result);
        System.out.println(result.getSingleton());

        System.out.println("========================");

        System.out.println(result2);
        System.out.println(result2.getSingleton());
    }

    public void run3() {
        Cyclic1 result = container.getComponent(Cyclic1.class);

        System.out.println(result);
    }
}
