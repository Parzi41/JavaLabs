package org.example;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        //Empty matrix
        Matrix emptyMatrix = new Matrix(2, 2);
        emptyMatrix.printMatrix();

        //Matrix from values
        double[][] values = {
                {1, 2, 3},
                {4, 4, 6},
                {7, 8, 9}
        };
        Matrix matrixFromValues = new Matrix(values);
        matrixFromValues.printMatrix();

        //Copy matrix
        Matrix matrixCopy = new Matrix(matrixFromValues);
        matrixCopy.printMatrix();

        //-----------------------------------------------
        //Get row and column
        double[] row = matrixFromValues.getRow(0);
        for(int i = 0; i < row.length; i++) {
            System.out.print(row[i]);
        }
        System.out.println();

        double[] column = matrixFromValues.getColumn(1);
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

        //------------------------------------------------------
        //Transposed matrix
        Matrix transposedMatrix = matrixFromValues.transpose();
        System.out.println("Transposed matrix:");
        transposedMatrix.printMatrix();
        //------------------------------------------------------

        //------------------------------------------------------
        //Diagonal matrix
        int[] diagonalVector = {2, 4, 6};
        Matrix diagonalMatrix = Matrix.diagonalMatrix(diagonalVector);

        System.out.println("Diagonal matrix:");
        diagonalMatrix.printMatrix();
        //-------------------------------------------------------

        //------------------------------------------------------
        //Identity matrix
        int sizeIdentity = 3;
        Matrix identityMatrix = Matrix.identityMatrix(sizeIdentity);

        System.out.println("Identity matrix:");
        identityMatrix.printMatrix();
        //------------------------------------------------------

        //------------------------------------------------------
        //Row matrix
        int columns = 5;
        int maxValue = 10;
        Matrix randomRowMatrix = Matrix.randomRowMatrix(columns, maxValue);

        System.out.println("Row matrix with random values:");
        randomRowMatrix.printMatrix();
        //------------------------------------------------------

        //------------------------------------------------------
        //Column matrix
        int rows = 5;
        maxValue = 10;
        Matrix randomColumnMatrix = Matrix.randomColumnMatrix(rows, maxValue);

        System.out.println("Column matrix with random values:");
        randomColumnMatrix.printMatrix();
        //------------------------------------------------------

        //-------------------------------------------------------
        //Lower and upper triangular matrix's
        Matrix lowerTriangularMatrix = matrixFromValues.toLowerTriangularForm();

        System.out.println("Lower triangular:");
        lowerTriangularMatrix.printMatrix();

        //Upper triangular
        Matrix upperTriangularMatrix = matrixFromValues.toTriangularForm();

        System.out.println("Upper triangular:");
        upperTriangularMatrix.printMatrix();
        //---------------------------------------------------------

        //---------------------------------------------------------
        //Inverse matrix
        Matrix inverseMatrix = matrixFromValues.inverse();

        System.out.println("Inverse matrix:");
        inverseMatrix.printMatrix();
        //---------------------------------------------------------

        //---------------------------------------------------------
        //Generic


        //---------------------------------------------------------
    }
}