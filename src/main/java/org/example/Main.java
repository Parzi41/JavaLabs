package org.example;

public class Main {
    public static void main(String[] args)
    {
        StringCalculator calculator = new StringCalculator();
        System.out.println(calculator.add(""));
        System.out.println(calculator.add("1"));
        System.out.println(calculator.add("1,2"));
        System.out.println(calculator.add("1,2,3,4,5"));
        System.out.println(calculator.add("1\n2,3"));
        System.out.println(calculator.add("1,\n"));
        System.out.println(calculator.add("//;\n1;2"));
        System.out.println(calculator.add("1,-2,3,-4"));
        System.out.println(calculator.add("1000,999,1001"));
    }
}