package calc;

import calc.InvalidInputException;
public class StringCalculator {
    public int add(String numbers) throws InvalidInputException {
        if (numbers.equals("")) return 0;

        int sum = 0;
        boolean waitingNumber = true;
        String currentNumber = "";
        for (int i = 0; i < numbers.length(); ++i) {
            char c = numbers.charAt(i);
            if (waitingNumber) {
                if (Character.isDigit(c)) {
                    currentNumber = currentNumber + c;
                    waitingNumber = false;
                } else if (c == '-') {
                    currentNumber = "-";
                } else {
                    throw new InvalidInputException("Incorrect input");
                }
            } else {
                if (Character.isDigit(c)) {
                    currentNumber = currentNumber + c;
                } else if ((c == ',') || (c == '\n')) {
                    sum += Integer.parseInt(currentNumber);
                    currentNumber = "";
                    waitingNumber = true;
                } else {
                    throw new InvalidInputException("Incorrect input");
                }
            }
        }
        if (waitingNumber) { throw new InvalidInputException("Incorrect input"); }
        sum += Integer.parseInt(currentNumber);

        return sum;
    }
}
