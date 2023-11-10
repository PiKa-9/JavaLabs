package org.livetesting.classes;

import javax.inject.Inject;

public class Cyclic3 {
    @Inject
    public Cyclic3(Cyclic2 cyclic2) {}
}
