package calc;

import java.util.ArrayList;
import java.util.HashSet;

public class StringCalculator {
    public int add(String numbers) throws InvalidInputException {
        if (numbers.equals("")) return 0;

        HashSet<Character> delimiters = new HashSet<>();
        delimiters.add(','); delimiters.add('\n');
        if (numbers.matches("//[^0-9]\n(.|\n)*")) {
            delimiters.add(numbers.charAt(2));
            numbers = numbers.substring(4);
        }
        if (numbers.equals("")) return 0;

        int sum = 0;
        boolean waitingNumber = true;
        String currentNumber = "";
        String negatives = "";
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
                    if (currentNumber.charAt(0) == '-') {
                        negatives = negatives + " " + currentNumber;
                    } else {
                        if (!isBiggerThan1000(currentNumber)) sum += Integer.parseInt(currentNumber);
                    }
                    currentNumber = "";
                    waitingNumber = true;
                } else {
                    throw new InvalidInputException("Incorrect input");
                }
            }
        }
        if (waitingNumber) { throw new InvalidInputException("Incorrect input"); }
        if (currentNumber.charAt(0) == '-') {
            negatives = negatives + " " + currentNumber;
        } else {
            if (!isBiggerThan1000(currentNumber)) sum += Integer.parseInt(currentNumber);
        }


        if (!negatives.equals("")) {
            throw new InvalidInputException("Negatives not allowed:" + negatives);
        }

        return sum;
    }

    private static boolean isBiggerThan1000(String num) {
        int j = 0;
        while ((j < num.length()-1) && (num.charAt(j) == '0')) j++;
        num = num.substring(j);
        if ((num.length() <= 3) || num.equals("1000")) {
            return false;
        }
        return true;
    }
}
