package org.picotesting.classes;

import javax.inject.Inject;

public class Cyclic2 {
    @Inject
    public Cyclic2(Cyclic1 cyclic1) {}
}
