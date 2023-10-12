package org.matrix;

public final class ImmutableMatrix extends Matrix {
    public ImmutableMatrix() {
        super();
    }
    public ImmutableMatrix(int rowCount, int colCount) {
        super(rowCount, colCount);
    }
    public ImmutableMatrix(Matrix matrix) {
        super(matrix);
    }

    @Override
    public void fillOneValue(int i, int j, double val) { System.out.println("The object is immutable. Value wasn't filled."); }
    @Override
    public void fillMatrix(double[][] data) {
        System.out.println("The object is immutable. Values weren't filled.");
    }
}
