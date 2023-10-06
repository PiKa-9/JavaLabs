package org.matrix;

import java.util.Arrays;
import java.util.Random;

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

    public static Matrix getIdentityMatrix(int n) {
        if (n < 0) {
            System.out.println("Can't create matrix with negative dimensions. Returning null.");
            return null;
        }
        Matrix res = new Matrix(n, n);
        for (int i = 0; i < n; ++i) { res.fillOneValue(i, i, 1); }
        return res;
    }

    public static Matrix getRandomRowMatrix(int n, double lowerBound, double upperBound) {
        // [lowerBound, upperBound) - range of random values
        // if lowerBound = upperBound, then all filled values equal lowerBound
        if (n < 0) {
            System.out.println("Can't create matrix with negative dimension. Returning null.");
            return null;
        }
        if (lowerBound > upperBound) {
            System.out.println("Invalid range. Returning null.");
            return null;
        }
        Matrix res = new Matrix(1, n);
        Random random = new Random();
        for (int i = 0; i < n; ++i) {
            if (lowerBound == upperBound) {
                res.fillOneValue(0, i, lowerBound);
            } else {
                res.fillOneValue(0, i, random.nextDouble(lowerBound, upperBound));
            }
        }
        return res;
    }

    public static Matrix getRandomColumnMatrix(int n, double lowerBound, double upperBound) {
        // [lowerBound, upperBound) - range of random values
        // if lowerBound = upperBound, then all filled values equal lowerBound
        if (n < 0) {
            System.out.println("Can't create matrix with negative dimension. Returning null.");
            return null;
        }
        if (lowerBound > upperBound) {
            System.out.println("Invalid range. Returning null.");
            return null;
        }
        Matrix res = new Matrix(n, 1);
        Random random = new Random();
        for (int i = 0; i < n; ++i) {
            if (lowerBound == upperBound) {
                res.fillOneValue(i, 0, lowerBound);
            } else {
                res.fillOneValue(i, 0, random.nextDouble(lowerBound, upperBound));
            }
        }
        return res;
    }


    public void swapRows(int row1, int row2) {
        double[] tmp = data[row1];
        data[row1] = data[row2];
        data[row2] = tmp;
    }
    public void addRows(double coefficient, int rowToAdd, int rowToModify) {
        for (int j = 0; j < colCount; ++j) {
            data[rowToModify][j] += coefficient * data[rowToAdd][j];
        }
    }
    public void divideRowByScalar(double scalar, int row) {
        if (scalar == 0) {return;}
        for (int j = 0; j < colCount; ++j) {
            data[row][j] /= scalar;
        }
    }
    public static void transformToTriangularForm(Matrix left, Matrix right) throws Exception {
        int n = left.rowCount;
        for (int j = 0; j < n; ++j) {
            // Find the row with non-zero element in j-th column
            for (int i = j; i < n; ++i) {
                if (left.getElement(i, j) != 0) {
                    left.swapRows(j, i);
                    right.swapRows(j, i);
                    break;
                }
            }
            if (left.getElement(j, j) == 0) { throw new Exception("Determinant of matrix is 0, can't calculate Inverse matrix."); }
            double x = left.getElement(j, j);
            left.divideRowByScalar(x, j);
            right.divideRowByScalar(x, j);
            for (int i = j+1; i < n; ++i) {
                double coef = -left.getElement(i, j);
                left.addRows(coef, j, i);
                right.addRows(coef, j, i);
            }
        }
    }
    public static void transformFromTriangularToDiagonalForm(Matrix left, Matrix right) {
        int n = left.rowCount;
        for (int j = n-1; j > 0; --j) {
            for (int i = j-1; i >= 0; --i) {
                double coef = -left.getElement(i, j)/left.getElement(j, j);
                left.addRows(coef, j, i);
                right.addRows(coef/left.getElement(j, j), j, i);
            }
        }
    }
    public Matrix getInverse() {
        if (rowCount != colCount) {
            System.out.println("Dimensions aren't equal, can't calculate Inverse matrix. Returning null.");
            return null;
        }
        Matrix left = new Matrix(this);
        Matrix right = Matrix.getIdentityMatrix(rowCount);

        // Transform the left matrix to Upper Triangular form, with 1s on the main diagonal
        try {
            transformToTriangularForm(left, right);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Returning null.");
            return null;
        }

        // Transform the left matrix from Triangular to Diagonal form
        transformFromTriangularToDiagonalForm(left, right);

        // Return the right matrix (it will be Inverse to original)
        return right;
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
