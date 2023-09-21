import calc.InvalidInputException;
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
    private String ProcessSlashCharacters(String numbers) {
        numbers = numbers.replace("\\n", "\n");
        numbers = numbers.replace("\\t", "\t");
        return numbers;
    }
    @ParameterizedTest
    @CsvFileSource(resources = "StringCalculator_validInputTests.csv")
    void validInputTest(int expectedSum, String numbers) {
        numbers = ProcessSlashCharacters(numbers);
        try {
            assertEquals(expectedSum, calculator.add(numbers));
        } catch (InvalidInputException e) {
            fail("Exception was thrown on valid test");
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "StringCalculator_invalidInputTests.csv")
    void invalidInputTest(String expectedExceptionMessage, String numbers) {
        numbers = ProcessSlashCharacters(numbers);
        try {
            calculator.add(numbers);
            fail("Exception wasn't thrown on invalid test");
        } catch(InvalidInputException e) {
            assertEquals(expectedExceptionMessage, e.getMessage());
        }
    }
}
