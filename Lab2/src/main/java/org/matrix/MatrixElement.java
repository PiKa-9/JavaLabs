package org.matrix;

public interface MatrixElement<Value> {
    MatrixElement<Value> add(MatrixElement<Value> b);
    String getString();
    Value getValue();
}
