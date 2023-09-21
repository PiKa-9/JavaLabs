package calc;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.equals("")) return 0;

        String[] splits = numbers.split(",", -1);
        int sum = 0;
        for (String n: splits) sum += Integer.parseInt(n);
        return sum;
    }
}
