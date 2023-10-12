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
    @AfterEach void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    public void ShouldCreateEmptyMatrix() {
        Matrix A = new ImmutableMatrix();

        assert (A.getRowCount() == 0);
        assert (A.getColCount() == 0);
        assertArrayEquals(A.getData(), new double[0][0]);
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
}
