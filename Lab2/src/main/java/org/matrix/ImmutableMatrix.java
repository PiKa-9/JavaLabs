package org.matrix;

import java.util.Arrays;

final public class ImmutableMatrix {
    final private int rowCount;
    final private int colCount;
    final private double[][] data;

    public ImmutableMatrix() {
        rowCount = 0;
        colCount = 0;
        data = new double[0][0];
    }
    public ImmutableMatrix(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.data = new double[rowCount][colCount];
    }
    public ImmutableMatrix(Matrix matrix) {
        rowCount = matrix.getRowCount();
        colCount = matrix.getColCount();
        double[][] dataToCopy = matrix.getData();
        data = new double[rowCount][colCount];
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                data[i][j] = dataToCopy[i][j];
            }
        }
    }

    public void fillOneValue(int i, int j, double val) { System.out.println("The object is immutable. Value wasn't filled."); }
    public void fillMatrix(double[][] data) {
        System.out.println("The object is immutable. Values weren't filled.");
    }

    public double getElement(int i, int j) {
        if ((0 <= i) && (i < rowCount) && (0 <= j) && (j < colCount)) {
            return data[i][j];
        } else {
            System.out.println("Invalid index range. Returning 0.");
            return 0;
        }
    }
    public double[] getRow(int i) {
        if ((0 <= i) && (i < rowCount)) {
            double[] row = new double[colCount];
            System.arraycopy(data[i], 0, row, 0, colCount);
            return row;
        } else {
            System.out.println("Invalid index range. Returning NULL.");
            return null;
        }
    }
    public double[] getCol(int j) {
        if ((0 <= j) && (j < colCount)) {
            double[] col = new double[rowCount];
            for (int i = 0; i < rowCount; ++i) {
                col[i] = data[i][j];
            }
            return col;
        } else {
            System.out.println("Invalid index range. Returning NULL.");
            return null;
        }
    }

    public int[] getDim() {
        return new int[]{rowCount, colCount};
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) { return false; }
        ImmutableMatrix matrix = (ImmutableMatrix) o;
        if ((rowCount != matrix.getRowCount()) || (colCount != matrix.getColCount())) {return false;}
        if (Arrays.deepEquals(data, matrix.getData())) {return true;}
        return false;
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(data);
        result = 31 * result + rowCount;
        result = 31 * result + colCount;
        return result;
    }

    public int getRowCount() {
        return rowCount;
    }
    public int getColCount() {
        return colCount;
    }
    public double[][] getData() {
        double[][] dataCopy = new double[rowCount][colCount];
        for (int i = 0; i < rowCount; ++i) {
            System.arraycopy(data[i], 0, dataCopy[i], 0, colCount);
        }
        return dataCopy;
    }
}
