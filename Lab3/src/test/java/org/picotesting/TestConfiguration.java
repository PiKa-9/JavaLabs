package org.picotesting;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;
import org.picotesting.classes.*;


public class TestConfiguration implements Configuration {
    @Override
    public void configure(Binder binder) {
        binder.bind(Singleton.class);
        binder.bind(Prototype.class);
        binder.bind(ExtendsSingletonWithBinding.class);
        binder.bind(SingletonWithBinding.class, ExtendsSingletonWithBinding.class);

        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());

        binder.bind(C.class, D.class);

        binder.bind(IImpl.class);
        binder.bind(I.class, IImpl.class);

        binder.bind(I1.class);

        binder.bind(InjectA.class);
        binder.bind(SingletonWithInjection.class);

        binder.bind(WithMultipleConstructors.class);

        binder.bind(WithNotBoundConstructorArgs.class);

        binder.bind(WithoutInjection.class);

        binder.bind(Cyclic1.class);
        binder.bind(Cyclic2.class);
        binder.bind(Cyclic3.class);

        binder.bind(NotCyclic.class);
    }
}
