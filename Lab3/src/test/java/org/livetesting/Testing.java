package org.livetesting;


import org.fpm.di.Environment;
import org.livetesting.classes.Cyclic1;
import org.livetesting.classes.InjectsSingleton;
import org.pico.PicoContainer;

public class Testing {
    private PicoContainer container;

    public static void main(String[] args) {
        Testing t = new Testing();
        t.setUp();
        // t.run1();
        t.run2();
    }

    public void setUp() {
        Environment env = new MyEnvironment();
        container = (PicoContainer) env.configure(new MyConfiguration());
    }

    /* Write the logic here */
    public void run1() {
        InjectsSingleton result = container.getComponent(InjectsSingleton.class);
        InjectsSingleton result2 = container.getComponent(InjectsSingleton.class);

        System.out.println(result);
        System.out.println(result.getSingleton());

        System.out.println("========================");

        System.out.println(result2);
        System.out.println(result2.getSingleton());
    }

    public void run2() {
        Cyclic1 result = container.getComponent(Cyclic1.class);

        System.out.println(result);
    }
}
