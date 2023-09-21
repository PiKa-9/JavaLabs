import calc.StringCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;
public class StringCalculatorTest {
    private StringCalculator calculator;
    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "StringCalculator_addTests.csv")
    void addTest(int expectedSum, String numbers) {
        assertEquals(expectedSum, calculator.add(numbers));
    }
}
