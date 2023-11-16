package org.example;

public class Matrix {
    private int rows;
    private int columns;
    private int[][] data;

    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Colums and rows must be bigger than 0");
        }

        this.rows = rows;
        this.columns = columns;
        this.data = new int[rows][columns];
    }

    public Matrix(int[][] values) {
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

    public Matrix(Matrix other) {
        this.rows = other.getRows();
        this.columns = other.getColumns();
        this.data = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] = other.getElement(i, j);
            }
        }
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

        int[] row = new int[columns];

        for(int i = 0; i < columns; i++) {
            row[i] = data[rowIndex][i];
        }

        return row;
    }

    public int[] getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= columns) {
            throw new IllegalArgumentException("Wrong column index");
        }

        int[] column = new int[rows];

        for(int i = 0; i < rows; i++) {
            column[i] = data[i][columnIndex];
        }

        return column;
    }

    public void setElement(int row, int column, int value) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IllegalArgumentException("Wrong matrix cords");
        }
        data[row][column] = value;
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