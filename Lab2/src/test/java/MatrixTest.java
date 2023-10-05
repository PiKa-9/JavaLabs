import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.matrix.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /* Custom matrix equality check with epsilon-precision */
    public boolean matrixesEqual(double[][] A, double[][] B, double eps) {
        if ((A.length == 0) && (B.length == 0)) { return true; }
        if (((A.length == 0) && (B.length > 0)) || ((A.length > 0) && (B.length == 0))) { return false; }
        if ((A.length != B.length) || (A[0].length != B[0].length)) { return false; }
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                if (Math.abs(A[i][j] - B[i][j]) > eps) { return false; }
            }
        }
        return true;
    }

    @BeforeEach void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    public void ShouldCreateEmptyMatrix() {
        Matrix A = new Matrix();
        assert (A.getData().length == 0);
    }
    @Test
    public void ShouldCreateMatrixByGivenDimensions() {
        Matrix A = new Matrix(3, 2);
        assert (A.getData().length == 3) && (A.getData()[0].length == 2);
    }
    @Test
    public void ShouldCreateMatrixFromAnotherMatrix() {
        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(A);

        assert (A.getRowCount() == B.getRowCount());
        assert (A.getColCount() == B.getColCount());
        assertArrayEquals(A.getData(), B.getData());
    }

    @Test
    public void ShouldFillOneValue() {
        Matrix A = new Matrix(3, 2);

        A.fillOneValue(2, 1, 2.7);
        A.fillOneValue(0, 0, -1);
        assert (A.getData()[2][1] == 2.7);
        assert (A.getData()[0][0] == -1);
        A.fillOneValue(2, 1, -3.31);
        assert (A.getData()[2][1] == -3.31);
    }
    @ParameterizedTest
    @CsvSource({"3,2", "2,2", "-1,0", "0,-23"})
    public void ShouldNotFillOneValue(int i, int j) {
        Matrix A = new Matrix(3, 2);
        double[][] dataOriginal = A.getData();

        A.fillOneValue(i, j, 2.7);

        assertArrayEquals(dataOriginal, A.getData());
        assertEquals("Invalid index range. Value wasn't filled.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldFillMatrix() {
        Matrix A = new Matrix(3, 2);
        double[][] dataToFill = new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}};

        A.fillMatrix(dataToFill);

        assertArrayEquals(dataToFill, A.getData());
    }
    @ParameterizedTest
    @CsvSource({"3,3", "2,2", "0,0"})
    public void ShouldNotFillMatrix(int rowCount, int colCount) {
        Matrix A = new Matrix(rowCount, colCount);
        double[][] dataOriginal = A.getData();
        double[][] dataToFill = new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}};

        A.fillMatrix(dataToFill);

        assertArrayEquals(dataOriginal, A.getData());
        assertEquals("Invalid shape of input values. Values weren't filled.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldReturnElement() {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});

        assertEquals(2.213, A.getElement(1, 1));
    }
    @ParameterizedTest
    @CsvSource({"3,3", "2,2", "-1,0", "0,-1"})
    public void ShouldNotReturnElement(int i, int j) {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});

        A.getElement(i, j);

        assertEquals("Invalid index range. Returning 0.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldReturnRow() {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});

        assertArrayEquals(new double[]{-2.3, 2.213}, A.getRow(1));
    }
    @ParameterizedTest
    @CsvSource({"4", "-1"})
    public void ShouldNotReturnRow(int i) {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});

        A.getRow(i);

        assertEquals("Invalid index range. Returning NULL.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldReturnCol() {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});

        assertArrayEquals(new double[]{1, -2.3, 0}, A.getCol(0));
    }
    @ParameterizedTest
    @CsvSource({"4", "-1"})
    public void ShouldNotReturnCol(int j) {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});

        A.getCol(j);

        assertEquals("Invalid index range. Returning NULL.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldReturnMatrixDimensions() {
        Matrix A = new Matrix(3, 2);
        assertArrayEquals(new int[]{3, 2}, A.getDim());
    }

    @Test
    void equalsTest() {
        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(3, 2);
        Matrix C = new Matrix(3, 1);
        Matrix D = new Matrix(2, 2);
        Matrix E = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});
        B.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});
        C.fillMatrix(new double[][]{{1}, {-2.3}, {0}});
        D.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}});
        E.fillMatrix(new double[][]{{1, 2.01}, {-2.3, 2.213}, {0, 3.3}});

        assertTrue(A.equals(B));
        assertFalse(A.equals(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}}));
        assertFalse(A.equals(C));
        assertFalse(A.equals(D));
        assertFalse(A.equals(E));
    }
    @Test
    void hashCodeTest() {
        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(3, 2);
        Matrix C = new Matrix(2, 1);
        Matrix D = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});
        B.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});
        C.fillMatrix(new double[][]{{-12.}, {-2.}});
        D.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {-0.1, 3.4}});

        assertEquals(A.hashCode(), B.hashCode());
        assertNotEquals(A.hashCode(), C.hashCode());
        assertNotEquals(A.hashCode(), D.hashCode());
    }

    @Test
    void ShouldAddMatrix() {
        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{-1, 3.2}, {1, 2}, {-2.3, 2.213}});
        B.fillMatrix(new double[][]{{1, -2.}, {-2., 2.2}, {-3.3, 2.213}});

        Matrix res = A.add(B);

        assertEquals(res.getRowCount(), A.getRowCount());
        assertEquals(res.getColCount(), A.getColCount());
        assertTrue(matrixesEqual(res.getData(), new double[][]{{0, 1.2}, {-1., 4.2}, {-5.6, 4.426}}, 1e-8));
    }
    @ParameterizedTest
    @CsvSource({"4,3", "3,1"})
    public void ShouldNotAddMatrix(int rowCount, int colCount) {
        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(rowCount, colCount);

        A.add(B);

        assertEquals("Matrixes don't have the same dimensions. Returning null.", outputStreamCaptor.toString().trim());
    }

    @Test
    void ShouldMultiplyByScalar() {
        Matrix A = new Matrix(2, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}});

        Matrix res = A.multByScalar(2.2);

        assertEquals(res.getRowCount(), A.getRowCount());
        assertEquals(res.getColCount(), A.getColCount());
        assertTrue(matrixesEqual(res.getData(), new double[][]{{2.2, 4.4}, {-5.06, 4.8686}}, 1e-8));
    }

    @Test
    void ShouldMultiplyMatrixes() {
        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(2, 3);
        A.fillMatrix(new double[][]{{-1, 1.5}, {1., 2.}, {-2.3, 2.}});
        B.fillMatrix(new double[][]{{1, -2., 0}, {-2., 0.5, 1.1}});

        Matrix res = A.mult(B);

        assertEquals(A.getRowCount(), res.getRowCount());
        assertEquals(B.getColCount(), res.getColCount());
        assertTrue(matrixesEqual(new double[][]{{-4, 2.75, 1.65}, {-3, -1, 2.2}, {-6.3, 5.6, 2.2}}, res.getData(), 1e-8));
    }
    @ParameterizedTest
    @CsvSource({"3,2", "1,1"})
    public void ShouldNotMultiplyMatrix(int rowCount, int colCount) {
        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(rowCount, colCount);

        A.mult(B);

        assertEquals("Wrong dimensions, can't multiply. Returning null.", outputStreamCaptor.toString().trim());
    }

    @Test
    void ShouldTranspose() {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{-1, 1.5}, {1., 2.}, {-2.3, 2.}});

        Matrix res = A.transpose();

        assertEquals(A.getColCount(), res.getRowCount());
        assertEquals(A.getRowCount(), res.getColCount());
        assertTrue(matrixesEqual(new double[][]{{-1, 1., -2.3}, {1.5, 2., 2.}}, res.getData(), 1e-8));
    }

    @Test
    void ShouldReturnDiagonalMatrix() {
        double[] arr = new double[]{-2.2, 3.1, 0.2};

        Matrix res = Matrix.getDiagonalMatrix(arr);

        assertTrue(matrixesEqual(new double[][]{{-2.2, 0, 0}, {0, 3.1, 0}, {0, 0, 0.2}}, res.getData(), 1e-8));
    }
}
