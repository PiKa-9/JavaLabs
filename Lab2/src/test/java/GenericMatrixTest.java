import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.matrix.GenericMatrix;
import org.matrix.MatrixElementImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericMatrixTest {
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
    void ShouldDisplayMatrix() {
        GenericMatrix<MatrixElementImpl> matrix = new GenericMatrix<>(1, 3);
        MatrixElementImpl<String> ele1 = new MatrixElementImpl<>("One");
        MatrixElementImpl<String> ele2 = new MatrixElementImpl<>("Two");
        MatrixElementImpl<String> ele3 = new MatrixElementImpl<>("Three");

        matrix.setElement(0, 0, ele1);
        matrix.setElement(0, 1, ele2);
        matrix.setElement(0, 2, ele3);

        matrix.displayMatrix();

        assertEquals("Matrix size: 1 3 \n| One | Two | Three |", outputStreamCaptor.toString().trim());
    }

    @Test
    void ShouldAddMatrixes() {
        GenericMatrix<MatrixElementImpl> A = new GenericMatrix<>(1, 3);
        GenericMatrix<MatrixElementImpl> B = new GenericMatrix<>(1, 3);
        MatrixElementImpl<String> ele1 = new MatrixElementImpl<>("One");
        MatrixElementImpl<String> ele2 = new MatrixElementImpl<>("Two");
        MatrixElementImpl<String> ele3 = new MatrixElementImpl<>("Three");

        A.setElement(0, 0, ele1);
        A.setElement(0, 1, ele2);
        A.setElement(0, 2, ele3);
        B.setElement(0, 0, ele1);
        B.setElement(0, 2, ele3);

        GenericMatrix<MatrixElementImpl> C = A.add(B);
        C.displayMatrix();
        assertEquals("Matrix size: 1 3 \n| OneOne | null | ThreeThree |", outputStreamCaptor.toString().trim());
    }
    @Test
    void ShouldNotAddMatrixes() {
        GenericMatrix<MatrixElementImpl> A = new GenericMatrix<>(1, 3);
        GenericMatrix<MatrixElementImpl> B = new GenericMatrix<>(2, 3);

        GenericMatrix<MatrixElementImpl> C = A.add(B);
        assertEquals(C, null);
    }
}
