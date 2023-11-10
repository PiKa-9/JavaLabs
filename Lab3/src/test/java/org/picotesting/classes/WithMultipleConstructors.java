package org.picotesting.classes;

import javax.inject.Inject;

public class WithMultipleConstructors {
    @Inject
    public WithMultipleConstructors(A a) {
    }
    public WithMultipleConstructors() {
    }
}
