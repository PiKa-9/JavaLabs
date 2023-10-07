import org.junit.jupiter.api.Test;
import org.matrix.MatrixElementImpl;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixElementImplTest {
    @Test
    void ShouldAddElements() {
        MatrixElementImpl<Integer> ele1 = new MatrixElementImpl(2);
        MatrixElementImpl<Double> ele2 = new MatrixElementImpl(-23.41);
        MatrixElementImpl<String> ele3 = new MatrixElementImpl("hello");
        MatrixElementImpl<String[]> ele4 = new MatrixElementImpl(new String[]{"a", "b"});

        assertEquals(ele1.add(ele1).getValue(), 4);
        assertEquals(ele2.add(ele2).getValue(), -46.82);
        assertEquals(ele3.add(ele3).getValue(), "hellohello");
        assertEquals(ele4.add(ele4), null);
    }
    @Test
    void ShouldReturnStringRepresentation() {
        MatrixElementImpl<Integer> ele1 = new MatrixElementImpl(2);
        MatrixElementImpl<Double> ele2 = new MatrixElementImpl(-23.41);
        MatrixElementImpl<String> ele3 = new MatrixElementImpl("hello");

        assertEquals(ele1.getString(), "2");
        assertEquals(ele2.getString(), "-23.41");
        assertEquals(ele3.getString(), "hello");
    }
}
