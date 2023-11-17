package org.example;

import java.util.Arrays;

public final class ImutableMatrix {
    private final int rows;
    private final int columns;
    private final int[][] data;

    public ImutableMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Columns and rows must be bigger than 0");
        }

        this.rows = rows;
        this.columns = columns;
        this.data = new int[rows][columns];
    }

    public ImutableMatrix(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0) {
            throw new IllegalArgumentException("Incorrect data");
        }

        this.rows = values.length;
        this.columns = values[0].length;
        this.data = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] = values[i][j];
            }
        }
    }

    public ImutableMatrix(ImutableMatrix other) {
        this.rows = other.getRows();
        this.columns = other.getColumns();
        this.data = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(other.data[i], 0, this.data[i], 0, columns);
        }
    }

    public ImutableMatrix add(ImutableMatrix other) {
        if (other == null || this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("You cant add matrix's with different size");
        }

        int[][] resultData = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultData[i][j] = this.data[i][j] + other.data[i][j];
            }
        }

        return new ImutableMatrix(resultData);
    }

    public ImutableMatrix multiplyByScalar(int scalar) {
        int[][] resultData = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultData[i][j] = this.data[i][j] * scalar;
            }
        }

        return new ImutableMatrix(resultData);
    }

    public ImutableMatrix multiply(ImutableMatrix other) {
        if (other == null || this.columns != other.rows) {
            throw new IllegalArgumentException("You cant multiply matrix's with different size");
        }

        int[][] resultData = new int[this.rows][other.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    resultData[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }

        return new ImutableMatrix(resultData);
    }

    public ImutableMatrix transpose() {
        int[][] transposedData = new int[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedData[j][i] = this.data[i][j];
            }
        }
        return new ImutableMatrix(transposedData);
    }

    public static ImutableMatrix diagonalMatrix(int[] vector) {
        int size = vector.length;
        int[][] diagonalData = new int[size][size];
        for (int i = 0; i < size; i++) {
            diagonalData[i][i] = vector[i];
        }
        return new ImutableMatrix(diagonalData);
    }

    public static ImutableMatrix identityMatrix(int size) {
        int[][] identityData = new int[size][size];
        for (int i = 0; i < size; i++) {
            identityData[i][i] = 1;
        }
        return new ImutableMatrix(identityData);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[] getSize() {
        int[] size = new int[2];
        size[0] = rows;
        size[1] = columns;
        return size;
    }

    public int getElement(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IllegalArgumentException("Wrong matrix cords");
        }
        return data[row][column];
    }

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows) {
            throw new IllegalArgumentException("Wrong row index");
        }

        return Arrays.copyOf(data[rowIndex], columns);
    }

    public int[] getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= columns) {
            throw new IllegalArgumentException("Wrong column index");
        }

        int[] column = new int[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = data[i][columnIndex];
        }

        return column;
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}



