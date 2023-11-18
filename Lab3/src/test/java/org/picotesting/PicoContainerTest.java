package org.picotesting;


import org.fpm.di.Environment;
import org.junit.Before;
import org.junit.Test;
import org.pico.PicoContainer;
import org.pico.exception.BindingNotFoundException;
import org.pico.exception.CyclicException;
import org.pico.exception.InjectConstructorNotFoundException;
import org.picotesting.classes.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PicoContainerTest {
    private PicoContainer container;

    @Before
    public void setUp() {
        Environment env = new TestEnvironment();
        container = (PicoContainer) env.configure(new TestConfiguration());
    }

    @Test
    public void shouldGetPrototype() {
        /*
        binder.bind(Prototype.class);
        */
        assertNotNull(container.getComponent(Prototype.class));
        assertNotSame(container.getComponent(Prototype.class), container.getComponent(Prototype.class));
    }

    @Test
    public void shouldGetSingleton() {
        /*
        Singleton has @Singleton annotation
        binder.bind(Singleton.class);
        */
        assertNotNull(container.getComponent(Singleton.class));
        assertSame(container.getComponent(Singleton.class), container.getComponent(Singleton.class));
    }
    @Test
    public void shouldGetSingletonWithBinding() {
        /*
        SingletonWithBinding has @Singleton annotation
        binder.bind(Prototype.class);
        binder.bind(SingletonWithBinding.class, ExtendsSingletonWithBinding.class);
        */
        assertNotNull(container.getComponent(SingletonWithBinding.class));
        assertSame(container.getComponent(SingletonWithBinding.class), container.getComponent(SingletonWithBinding.class));
    }
    @Test
    public void shouldGetSingletonWithDependencies() {
        /*
        SingletonInjectC has @Inject constructor with Prototype argument
        binder.bind(SingletonWithInjection.class);
        */
        assertNotNull(container.getComponent(SingletonWithInjection.class));
        assertSame(container.getComponent(SingletonWithInjection.class), container.getComponent(SingletonWithInjection.class));
    }


    @Test
    public void shouldBuildInjectionGraph() {
        /*
        B extends A
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        */
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() {
        /*
        InjectA has @Inject constructor with A argument
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        binder.bind(InjectA.class);
        */
        final InjectA hasADependency = container.getComponent(InjectA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }


    @Test
    public void shouldReturnOnlyBoundClasses() {
        /*
        ArrayList.class wasn't bound
        */
        assertNull(container.getComponent(ArrayList.class));
        try {
            container.clearCurrentClasses();
            container._getComponent(ArrayList.class);
        } catch (Exception e) {
            assertEquals(BindingNotFoundException.class, e.getClass());
        }
    }
    @Test
    public void shouldThrowExceptionIfDependencyClassHasNoBind() {
        /*
        D extends C;
        binder.bind(C.class, D.class);
        */
        assertNull(container.getComponent(C.class));
        try {
            container.clearCurrentClasses();
            container._getComponent(C.class);
        } catch (Exception e) {
            assertEquals(BindingNotFoundException.class, e.getClass());
        }
    }

    @Test
    public void shouldSupportInterfaces() {
        /*
        I - interface; A implements I;
        binder.bind(IImpl.class);
        binder.bind(I.class, IImpl.class);
        */
        assertNotNull(container.getComponent(I.class));
        assertNotNull(container.getComponent(IImpl.class));
        assertSame(container.getComponent(I.class).getClass(), container.getComponent(IImpl.class).getClass());
    }
    @Test
    public void shouldNotCreateInstanceFromInterface() {
        /*
        I1 - interface;
        binder.bind(I1.class);
        */
        assertNull(container.getComponent(I1.class));
        try {
            container.clearCurrentClasses();
            container._getComponent(I1.class);
        } catch (Exception e) {
            assertEquals(InjectConstructorNotFoundException.class, e.getClass());
        }
    }

    @Test
    public void shouldThrowExceptionIfConstructorArgumentHasNoBind() {
        /*
        WithNotBoundConstructorArgs - has constructor with String argument, String - not bound
        binder.bind(WithNotBoundConstructorArgs.class);
        */
        assertNull(container.getComponent(WithNotBoundConstructorArgs.class));
        try {
            container.clearCurrentClasses();
            container._getComponent(WithNotBoundConstructorArgs.class);
        } catch (Exception e) {
            assertEquals(BindingNotFoundException.class, e.getClass());
        }
    }
    @Test
    public void shouldNotCreateIfNoInjectConstructor() {
        /*
        WithoutInjection - has declared constructor, but no constructor with @Inject annotation,
            so can't create with the default constructor
        binder.bind(WithoutInjection.class);
        */
        assertNull(container.getComponent(WithoutInjection.class));
        try {
            container.clearCurrentClasses();
            container._getComponent(WithoutInjection.class);
        } catch (Exception e) {
            assertEquals(InjectConstructorNotFoundException.class, e.getClass());
        }
    }
    @Test
    public void shouldCreateFromInjectConstructorWhenSeveralConstructorsGiven() {
        /*
        WithMultipleConstructors - has 2 non-inject declared constructors
        binder.bind(WithMultipleConstructors.class);
        */
        assertNotNull(container.getComponent(WithMultipleConstructors.class));
    }

    @Test
    public void shouldHandleCyclicDependencies() {
        /*
        Dependency in constructors:
        Cyclic1(Cyclic2); Cyclic2(Cyclic3); Cyclic3(Cyclic1);
        binder.bind(Cyclic1.class);
        binder.bind(Cyclic2.class);
        binder.bind(Cyclic3.class);
        */

        assertNull(container.getComponent(Cyclic1.class));
        try {
            container.clearCurrentClasses();
            container._getComponent(Cyclic1.class);
        } catch (Exception e) {
            assertEquals(CyclicException.class, e.getClass());
        }
        try {
            container.clearCurrentClasses();
            container._getComponent(Cyclic2.class);
        } catch (Exception e) {
            assertEquals(CyclicException.class, e.getClass());
        }
        try {
            container.clearCurrentClasses();
            container._getComponent(Cyclic3.class);
        } catch (Exception e) {
            assertEquals(CyclicException.class, e.getClass());
        }
    }
    @Test
    public void shouldNotCountAsCyclicDependency() {
        /*
        The constructor: NotCyclic(Prototype, Prototype)
        binder.bind(Prototype.class);
        binder.bind(NotCyclic.class);
        */
        assertNotNull(container.getComponent(NotCyclic.class));
    }
}
