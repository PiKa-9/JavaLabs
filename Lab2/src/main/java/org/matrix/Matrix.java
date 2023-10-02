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
            return data[i];
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
