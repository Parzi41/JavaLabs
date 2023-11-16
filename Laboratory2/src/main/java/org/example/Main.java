package org.example;

public class Main {
    public static void main(String[] args) {
        Matrix emptyMatrix = new Matrix(2, 2);
        emptyMatrix.printMatrix();

        int[][] values = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrixFromValues = new Matrix(values);
        matrixFromValues.printMatrix();

        Matrix matrixCopy = new Matrix(matrixFromValues);
        matrixCopy.printMatrix();

        int[] row = matrixFromValues.getRow(0);
        for(int i = 0; i < row.length; i++) {
            System.out.print(row[i]);
        }
        System.out.println();

        int[] column = matrixFromValues.getColumn(1);
        for(int i = 0; i < column.length; i++) {
            System.out.println(column[i]);
        }
    }
}