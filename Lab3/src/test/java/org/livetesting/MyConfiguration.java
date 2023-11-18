package org.livetesting;

import org.fpm.di.Binder;
import org.livetesting.classes.*;
import org.livetesting.classes.InjectsSingleton;
import org.livetesting.classes.MySingleton;


public class MyConfiguration implements org.fpm.di.Configuration {
    @Override
    public void configure(Binder binder) {
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());

        binder.bind(MySingleton.class);
        binder.bind(InjectsSingleton.class);

        binder.bind(Cyclic1.class);
        binder.bind(Cyclic2.class);
        binder.bind(Cyclic3.class);
    }
}
