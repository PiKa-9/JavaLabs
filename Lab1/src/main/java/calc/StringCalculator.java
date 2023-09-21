package calc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

public class StringCalculator {
    // Forbidden characters in delimiter: 0123456789[]
    public int add(String numbers) throws InvalidInputException {
        if (numbers.equals("")) return 0;

        HashSet<String> delimiters = new HashSet<>();
        delimiters.add(","); delimiters.add("\n");
        if (numbers.matches("//\\[[^0-9\\[\\]]+]\n(.|\n)*")) {
            int j = 3;
            String delimiter = "";
            while (numbers.charAt(j) != ']') {
                delimiter += numbers.charAt(j);
                j++;
            }
            delimiters.add(delimiter);
            // j - index of ]
            numbers = numbers.substring(j+2);
            if (numbers.equals("")) return 0;
        }


        int sum = 0;
        String negatives = "";
        int i = 0;
        while (i < numbers.length()) {
            String currentNumber = "";
            if (numbers.charAt(i) == '-') {currentNumber = "-"; i++;}

            while ((i < numbers.length()) && Character.isDigit(numbers.charAt(i))) {
                currentNumber += numbers.charAt(i);
                i++;
            }
            if (currentNumber.equals("") || currentNumber.equals("-")) {
                throw new InvalidInputException("Incorrect input");
            }
            if (currentNumber.charAt(0) == '-') {negatives += " " + currentNumber;}
            else if (!isBiggerThan1000(currentNumber)) {sum += Integer.parseInt(currentNumber);}
            if (i >= numbers.length()) {continue;}

            String currentDelimiter = "";
            while ((i < numbers.length()) && !Character.isDigit(numbers.charAt(i))) {
                currentDelimiter += numbers.charAt(i);
                i++;
            }
            if (i >= numbers.length()) {throw new InvalidInputException("Incorrect input");}
            if (delimiters.contains(currentDelimiter)) {
                continue;
            } else if ((numbers.charAt(i-1) == '-') && (currentDelimiter.length() >= 2)) {
                currentDelimiter = currentDelimiter.substring(0, currentDelimiter.length()-1);
                if (delimiters.contains(currentDelimiter)) {i = i - 1;}
                else {throw new InvalidInputException("Incorrect input");}
            } else {
                throw new InvalidInputException("Incorrect input");
            }

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
