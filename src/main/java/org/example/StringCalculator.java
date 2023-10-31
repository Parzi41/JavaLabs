package org.example;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String delimiter = "";
        String[] numArray = null;

        if (numbers.startsWith("//[")) {
            Pattern pattern = Pattern.compile("\\[([^]]+)\\]");
            int end = numbers.lastIndexOf("]");
            int check = numbers.indexOf("]");

            Matcher m = pattern.matcher(numbers);
            while(m.find()) {
                delimiter += m.group(1);
            }

            numbers = numbers.substring(end + 1);

            String[] delimiters = delimiter.split("(?<=\\G.{" + 1 + "})");
            StringBuilder delimiterPattern = new StringBuilder();
            for (String d : delimiters) {
                delimiterPattern.append(Pattern.quote(d)).append("|");
            }
            delimiterPattern.setLength(delimiterPattern.length() - 1);

            numArray = numbers.split(delimiterPattern.toString() + "|\n");
        }

        else if (numbers.startsWith("//")) {
            int delimiterEnd = numbers.indexOf("\n");
            if (delimiterEnd != -1) {
                delimiter = numbers.substring(2, delimiterEnd);
                numbers = numbers.substring(delimiterEnd + 1);
                numArray = numbers.split("[" + delimiter +"\n]");
            } else {
                System.out.println("Incorrect data");
                return 0;
            }
        }

        if(numArray == null){
            numArray = numbers.split("[,\n]");
        }
        List<Integer> negativeNumbers = new ArrayList<>();

        int parsedNum;
        int sum = 0;

        for (String num : numArray) {
            if (num.isEmpty()) {
                continue;
            }
            parsedNum = Integer.parseInt(num);
            if (parsedNum < 0) {
                negativeNumbers.add(parsedNum);
            } else if (parsedNum <= 1000) {
                sum += parsedNum;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            System.out.println("Negative numbers: " + negativeNumbers);
            return 0;
        }

        return sum;
    }
}
