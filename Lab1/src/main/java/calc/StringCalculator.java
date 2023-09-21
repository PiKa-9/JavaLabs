package calc;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.equals("")) return 0;

        String[] splits = numbers.split(",", -1);
        if (splits.length == 1) {
            return Integer.parseInt(splits[0]);
        }
        return Integer.parseInt(splits[0]) + Integer.parseInt(splits[1]);
    }
}
