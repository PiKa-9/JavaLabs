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
        // Matrix class testing
//        Matrix A = new Matrix(3, 3);
//        Matrix B = new Matrix(3, 3);
//        A.fillMatrix(new double[][]{{1, -2, 3}, {-1, 0, -1.6}, {1, 1, 1}});
//        B.fillMatrix(new double[][]{{-1, 0.4, -3}, {-1, 0.5, -1.6}, {1, 1, 1}});
//
//        displayMatrix(A.getInverse());
//        displayMatrix(A.add(B));
//        displayMatrix(A.multByScalar(-1.2));
//        displayMatrix(A.mult(A.transpose()));
//        displayMatrix(A.transpose());
//        displayMatrix(Matrix.getDiagonalMatrix(new double[]{1, -23, 0.2043}));
//        displayMatrix(Matrix.getIdentityMatrix(2));
//        displayMatrix(Matrix.getRandomRowMatrix(3, -4, 1));
//        displayMatrix(Matrix.getRandomColumnMatrix(3, -4, 1));

        // Immutable Matrix testing
//        Matrix A = new Matrix(3, 3);
//        A.fillMatrix(new double[][]{{1, 1, 1}, {-1, 1, -1.6}, {-1.22, 1, 1}});
//        ImmutableMatrix immutableA = new ImmutableMatrix(3, 3);
//        immutableA.fillMatrix(new double[][]{{1, 1, 1}, {-1, 1, -1.6}, {-1.22, 1, 1}});
//        double[][] data = immutableA.getData();
//        System.out.println(immutableA.getElement(0, 0));
//        data[0][0] = 2.2;
//        System.out.println(immutableA.getElement(0, 0));

        // Generic Matrix Testing
        MatrixElementImpl<String> ele1 = new MatrixElementImpl<>("Hi");
        MatrixElementImpl<String> ele2 = new MatrixElementImpl<>("Hello");
        MatrixElementImpl<String> ele3 = new MatrixElementImpl<>("Bye");
        MatrixElementImpl<String> ele4 = new MatrixElementImpl<>("Goodbye");
        MatrixElementImpl<String> ele5 = new MatrixElementImpl<>(" somebody");
        MatrixElementImpl<String> ele6 = new MatrixElementImpl<>(" world!");

        GenericMatrix<MatrixElementImpl<String>> M = new GenericMatrix<>(2, 2);
        GenericMatrix<MatrixElementImpl<String>> B = new GenericMatrix<>(2, 2);

        M.setElement(0, 0, ele1);
        M.setElement(0, 1, ele2);
        M.setElement(1, 0, ele3);
        M.setElement(1, 1, ele4);
        B.setElement(0, 0, ele5);
        B.setElement(0, 1, ele6);
        B.setElement(1, 0, ele5);
        B.setElement(1, 1, ele6);

        GenericMatrix<MatrixElementImpl<String>> C = M.add(B);

        M.displayMatrix();
        B.displayMatrix();
        C.displayMatrix();
    }
}
