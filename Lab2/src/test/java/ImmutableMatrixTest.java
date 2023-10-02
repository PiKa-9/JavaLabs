import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.matrix.ImmutableMatrix;
import org.matrix.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ImmutableMatrixTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    void ImmutabilityCheck() {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});
        ImmutableMatrix aImmutable = new ImmutableMatrix(A);

        double[][] data = aImmutable.getData();
        assertArrayEquals(data, aImmutable.getData());
        data[0][0] = 2.2;
        assertFalse(Arrays.deepEquals(data, aImmutable.getData()));
    }

    @Test
    public void ShouldCreateEmptyMatrix() {
        ImmutableMatrix A = new ImmutableMatrix();
        assert (A.getData().length == 0);
    }
    @Test
    public void ShouldCreateMatrixByGivenDimensions() {
        ImmutableMatrix A = new ImmutableMatrix(3, 2);
        assert (A.getData().length == 3) && (A.getData()[0].length == 2);
    }
    @Test
    public void ShouldCreateMatrixFromAnotherMatrix() {
        Matrix A = new Matrix(3, 2);
        ImmutableMatrix B = new ImmutableMatrix(A);

        assert (A.getRowCount() == B.getRowCount());
        assert (A.getColCount() == B.getColCount());
        assertArrayEquals(A.getData(), B.getData());
    }

    @ParameterizedTest
    @CsvSource({"3,2", "2,2", "-1,0", "0,-23"})
    public void ShouldNotFillOneValue(int i, int j) {
        ImmutableMatrix A = new ImmutableMatrix(3, 2);
        double[][] dataOriginal = A.getData();

        A.fillOneValue(i, j, 2.7);

        assertArrayEquals(dataOriginal, A.getData());
        assertEquals("The object is immutable. Value wasn't filled.", outputStreamCaptor.toString().trim());
    }


    @ParameterizedTest
    @CsvSource({"3,3", "2,2", "0,0"})
    public void ShouldNotFillMatrix(int rowCount, int colCount) {
        ImmutableMatrix A = new ImmutableMatrix(rowCount, colCount);
        double[][] dataOriginal = A.getData();
        double[][] dataToFill = new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}};

        A.fillMatrix(dataToFill);

        assertArrayEquals(dataOriginal, A.getData());
        assertEquals("The object is immutable. Values weren't filled.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldReturnElement() {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});
        ImmutableMatrix aImmutable = new ImmutableMatrix(A);


        assertEquals(2.213, aImmutable.getElement(1, 1));
    }
    @ParameterizedTest
    @CsvSource({"3,3", "2,2", "-1,0", "0,-1"})
    public void ShouldNotReturnElement(int i, int j) {
        ImmutableMatrix A = new ImmutableMatrix(3, 2);

        A.getElement(i, j);

        assertEquals("Invalid index range. Returning 0.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldReturnRow() {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});
        ImmutableMatrix aImmutable = new ImmutableMatrix(A);

        assertArrayEquals(new double[]{-2.3, 2.213}, aImmutable.getRow(1));
    }
    @ParameterizedTest
    @CsvSource({"4", "-1"})
    public void ShouldNotReturnRow(int i) {
        ImmutableMatrix A = new ImmutableMatrix(3, 2);

        A.getRow(i);

        assertEquals("Invalid index range. Returning NULL.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldReturnCol() {
        Matrix A = new Matrix(3, 2);
        A.fillMatrix(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}});
        ImmutableMatrix aImmutable = new ImmutableMatrix(A);

        assertArrayEquals(new double[]{1, -2.3, 0}, aImmutable.getCol(0));
    }
    @ParameterizedTest
    @CsvSource({"4", "-1"})
    public void ShouldNotReturnCol(int j) {
        ImmutableMatrix A = new ImmutableMatrix(3, 2);

        A.getCol(j);

        assertEquals("Invalid index range. Returning NULL.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldReturnMatrixDimensions() {
        ImmutableMatrix A = new ImmutableMatrix(3, 2);
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
        ImmutableMatrix aImmutable = new ImmutableMatrix(A);
        ImmutableMatrix bImmutable = new ImmutableMatrix(B);
        ImmutableMatrix cImmutable = new ImmutableMatrix(C);
        ImmutableMatrix dImmutable = new ImmutableMatrix(D);
        ImmutableMatrix eImmutable = new ImmutableMatrix(E);

        assertTrue(aImmutable.equals(bImmutable));
        assertFalse(aImmutable.equals(new double[][]{{1, 2}, {-2.3, 2.213}, {0, 3.3}}));
        assertFalse(aImmutable.equals(cImmutable));
        assertFalse(aImmutable.equals(dImmutable));
        assertFalse(aImmutable.equals(eImmutable));
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
        ImmutableMatrix aImmutable = new ImmutableMatrix(A);
        ImmutableMatrix bImmutable = new ImmutableMatrix(B);
        ImmutableMatrix cImmutable = new ImmutableMatrix(C);
        ImmutableMatrix dImmutable = new ImmutableMatrix(D);

        assertEquals(aImmutable.hashCode(), bImmutable.hashCode());
        assertNotEquals(aImmutable.hashCode(), cImmutable.hashCode());
        assertNotEquals(aImmutable.hashCode(), dImmutable.hashCode());
    }
}
