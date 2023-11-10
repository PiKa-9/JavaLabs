package org.livetesting.classes;

import javax.inject.Inject;

public class Cyclic1 {
    @Inject
    public Cyclic1(Cyclic3 cyclic3) {}
}
