import org.matrix.Matrix;

import java.security.spec.RSAOtherPrimeInfo;

public class SimpleTesting {
    public static void displayMatrix(double[][] data) {
        if (data.length == 0) {
            System.out.println("Empty matrix\n");
            return;
        }
        System.out.printf("Size: %d × %d \n", data.length, data[0].length);
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; ++j) {
                System.out.printf("%.2f ", data[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(2, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});
        B.fillMatrix(new double[][]{{-2.3, 2.213}, {0, 3.3}});

//        displayMatrix(A.add(B).getData());
//        displayMatrix(A.multByScalar(-1.2).getData());
        displayMatrix(B.mult(B).getData());
    }
}
