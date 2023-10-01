package org.matrix;

public class Matrix {
    private int rowCount;
    private int colCount;
    private double[][] data;

    public Matrix() {
        rowCount = 0;
        colCount = 0;
        data = new double[0][0];
    }
    public Matrix(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.data = new double[rowCount][colCount];
    }
    public Matrix(Matrix matrix) {
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

    public void fillOneValue(int i, int j, double val) {
        if ((0 <= i) && (i < rowCount) && (0 <= j) && (j < colCount)) {
            data[i][j] = val;
        } else {
            System.out.println("Invalid index range. Value wasn't filled.");
        }
    }
    public void fillMatrix(double[][] data) {
        if ((rowCount == data.length) && (colCount == data[0].length)) {
            for (int i = 0; i < rowCount; ++i) {
                for (int j = 0; j < colCount; ++j) {
                    this.data[i][j] = data[i][j];
                }
            }
        } else {
            System.out.println("Invalid shape of input values. Values weren't filled.");
        }
    }

    public int getRowCount() {
        return rowCount;
    }
    public int getColCount() {
        return colCount;
    }
    public double[][] getData() {
        return data;
    }
}
