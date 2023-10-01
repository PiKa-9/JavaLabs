import org.junit.jupiter.api.Test;
import org.matrix.Matrix;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
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
}
