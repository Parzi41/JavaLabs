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
        String delimiter = ",";
        String newNumbers = "";
        String[] numArray = null;

        if (numbers.startsWith("//[")) {
            String temp = "";
            int indexstart = numbers.indexOf("[")+1;
            int indexstop = numbers.indexOf("]");
            while (indexstart != indexstop)
            {
                temp += numbers.charAt(indexstart);
                indexstart++;
            }
            newNumbers = numbers.substring(indexstop + 1);
            delimiter = temp;
            numArray = newNumbers.split(Pattern.quote(delimiter) + "|\n");
        }

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

        if(numArray == null){
            numArray = numbers.split("[" + delimiter + "\n]");
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
