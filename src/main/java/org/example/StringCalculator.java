package org.example;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] numArray = numbers.split("[,\n]");
        int sum = 0;
        for (String num : numArray) {
            if (!num.isEmpty()) {
                sum += Integer.parseInt(num);
            } else {
                throw new IllegalArgumentException("Incorrect data");
            }
        }

        return sum;
    }
}
