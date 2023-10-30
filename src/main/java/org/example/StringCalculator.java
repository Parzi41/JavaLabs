package org.example;
import java.util.ArrayList;
import java.util.List;

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
                System.out.println("Incorrect data");
                return 0;
            }
        }


        String[] numArray = numbers.split("[" + delimiter + "\n]");
        List<Integer> negativeNumbers = new ArrayList<>();

        int parsedNum;
        int sum = 0;

        for (String num : numArray) {
            if (!num.isEmpty()) {
                parsedNum = Integer.parseInt(num);
                if (parsedNum < 0) {
                    negativeNumbers.add(parsedNum);
                } else if (parsedNum <= 1000) {
                    sum += parsedNum;
                }
            } else {
                System.out.println("Incorrect data");
                return 0;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            System.out.println("Negative numbers: " + negativeNumbers);
            return 0;
        }

        return sum;
    }
}
