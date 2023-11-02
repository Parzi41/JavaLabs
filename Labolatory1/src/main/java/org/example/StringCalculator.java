package org.example;

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
            int delimiterEnd = numbers.lastIndexOf("/");
            try{
                delimiter = String.valueOf(numbers.charAt(delimiterEnd + 1));
                numbers = numbers.substring(delimiterEnd + 2);
                numArray = numbers.split("[" + delimiter +"\n]");
            } catch (StringIndexOutOfBoundsException err) {
                throw new IllegalArgumentException("Incorrect data");
            }
        }

        if(numArray == null) {
            if(Character.isDigit(numbers.charAt(numbers.length()-1))) {
                numArray = numbers.split("[,\n]");
            }
            else {
                throw new IllegalArgumentException("Incorrect data");
            }
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
            throw new IllegalArgumentException("Negative numbers: " + negativeNumbers);
        }

        return sum;
    }
}
