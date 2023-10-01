import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.matrix.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
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
}
