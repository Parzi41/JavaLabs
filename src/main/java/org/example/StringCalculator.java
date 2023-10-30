package org.example;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",";
        if (numbers.startsWith("//")) {
            int delimiterEnd = numbers.indexOf("\n");
            if (delimiterEnd != -1) {
                delimiter = numbers.substring(2, delimiterEnd);
                numbers = numbers.substring(delimiterEnd + 1);
            } else {
                throw new IllegalArgumentException("Incorrect data");
            }
        }


        String[] numArray = numbers.split("[" + delimiter + "\n]");
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
