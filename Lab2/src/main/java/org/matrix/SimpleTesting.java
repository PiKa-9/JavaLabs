package org.matrix;


public class SimpleTesting {
    public static void displayMatrix(Matrix matrix) {
        if (matrix == null) {
            System.out.println("NULL\n");
            return;
        }
        if (matrix.getRowCount() == 0) {
            System.out.println("Empty matrix\n");
            return;
        }
        System.out.printf("Size: %d × %d \n", matrix.getRowCount(), matrix.getColCount());
        for (int i = 0; i < matrix.getRowCount(); ++i) {
            for (int j = 0; j < matrix.getColCount(); ++j) {
                System.out.printf("%.2f ", matrix.getElement(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Matrix A = new Matrix(3, 3);
        Matrix B = new Matrix(2, 2);
        A.fillMatrix(new double[][]{{1, 1, 1}, {-1, 1, -1.6}, {-1.22, 1, 1}});
        B.fillMatrix(new double[][]{{-2.3, 2.213}, {0, 3.3}});

        displayMatrix(A.getInverse());
//        displayMatrix(A.add(B));
//        displayMatrix(A.multByScalar(-1.2));
//        displayMatrix(A.mult(A.transpose()));
//        displayMatrix(A.transpose());
//        displayMatrix(Matrix.getDiagonalMatrix(new double[]{1, -23, 0.2043}));
//        displayMatrix(Matrix.getIdentityMatrix(2));
//        displayMatrix(Matrix.getRandomRowMatrix(3, -4, 1));
//        displayMatrix(Matrix.getRandomColumnMatrix(3, -4, 1));
    }
}
