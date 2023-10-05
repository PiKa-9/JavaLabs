package org.matrix;

import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) { return false; }
        Matrix matrix = (Matrix) o;
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

    public Matrix add(Matrix matrix) {
        if ((rowCount != matrix.getRowCount()) || (colCount != matrix.getColCount())) {
            System.out.println("Matrixes don't have the same dimensions. Returning null.");
            return null;
        }
        Matrix res = new Matrix(rowCount, colCount);
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                res.fillOneValue(i, j, data[i][j] + matrix.getElement(i, j));
            }
        }
        return res;
    }
    public Matrix multByScalar(double scalar) {
        Matrix res = new Matrix(rowCount, colCount);
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                res.fillOneValue(i, j, scalar * data[i][j]);
            }
        }
        return res;
    }

    public static double dotProduct(double[] a, double[] b) {
        double res = 0;
        if (a.length == b.length) {
            for (int i = 0; i < a.length; ++i) { res += a[i]*b[i]; }
        }
        return res;
    }
    public Matrix mult(Matrix matrix) {
        if (colCount != matrix.getRowCount()) {
            System.out.println("Wrong dimensions, can't multiply. Returning null.");
            return null;
        }
        Matrix res = new Matrix(rowCount, matrix.getColCount());
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < matrix.getColCount(); ++j) {
                res.fillOneValue(i, j, dotProduct(this.getRow(i), matrix.getCol(j)));
            }
        }
        return res;
    }

    public Matrix transpose() {
        Matrix res = new Matrix(colCount, rowCount);
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                res.fillOneValue(j, i, data[i][j]);
            }
        }
        return res;
    }

    public static Matrix getDiagonalMatrix(double[] arr) {
        Matrix res = new Matrix(arr.length, arr.length);
        for (int i = 0; i < arr.length; ++i) { res.fillOneValue(i, i, arr[i]); }
        return res;
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
