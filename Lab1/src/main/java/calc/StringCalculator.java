package calc;

import java.util.ArrayList;

public class StringCalculator {
    public int add(String numbers) throws InvalidInputException {
        if (numbers.equals("")) return 0;

        ArrayList<Character> delimiters = new ArrayList<>();
        delimiters.add(','); delimiters.add('\n');
        if (numbers.matches("//[^0-9]\n(.|\n)*")) {
            delimiters.add(numbers.charAt(2));
            numbers = numbers.substring(4);
        }
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
                } else if (delimiters.contains(c)) {
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
