package org.example;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        //Empty matrix
        Matrix emptyMatrix = new Matrix(2, 2);
        emptyMatrix.printMatrix();

        //Matrix from values
        int[][] values = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrixFromValues = new Matrix(values);
        matrixFromValues.printMatrix();

        //Copy matrix
        Matrix matrixCopy = new Matrix(matrixFromValues);
        matrixCopy.printMatrix();

        //-----------------------------------------------
        //Get row and column
        int[] row = matrixFromValues.getRow(0);
        for(int i = 0; i < row.length; i++) {
            System.out.print(row[i]);
        }
        System.out.println();

        int[] column = matrixFromValues.getColumn(1);
        for(int i = 0; i < column.length; i++) {
            System.out.println(column[i]);
        }
        System.out.println();
        //-----------------------------------------------


        //-----------------------------------------------
        //Get matrix size
        int[] size = matrixFromValues.getSize();
        for(int i = 0; i < size.length; i++) {
            System.out.print(size[i] + " ");
        }
        System.out.println("\n");

        //-----------------------------------------------
        //Equals and hashCode
        System.out.println("Equals:");
        System.out.println(matrixFromValues.equals(matrixCopy));

        HashSet<Matrix> matrixSet = new HashSet<>();
        matrixSet.add(matrixFromValues);

        System.out.println();

        System.out.println("HashCode:");
        System.out.println(matrixSet.contains(matrixCopy));
        System.out.println();
        //----------------------------------------------------

        //---------------------------------------------------
        //Add matrix and multiply by scalar
        Matrix sumMatrix = matrixFromValues.add(matrixCopy);
        System.out.println("Matrix sum: ");
        sumMatrix.printMatrix();

        Matrix scaledMatrix = matrixFromValues.multiplyByScalar(3);
        System.out.println("Multiplied by scalar matrix: ");
        scaledMatrix.printMatrix();
        //-------------------------------------------------------

        //-----------------------------------------------------
        //Multiply two matrix's
        Matrix productMatrix = matrixFromValues.multiply(matrixCopy);
        System.out.println("Multiplied matrix's:");
        productMatrix.printMatrix();
        //-----------------------------------------------------------
    }
}