package org.picotesting.classes;

import javax.inject.Inject;

public class NotCyclic {
    @Inject
    public NotCyclic(Prototype p1, Prototype p2) {

    }
}
