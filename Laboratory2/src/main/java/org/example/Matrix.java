package org.example;

import java.util.Arrays;
import java.util.Random;

public class Matrix {
    private int rows;
    private int columns;
    private int[][] data;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Matrix otherMatrix = (Matrix) obj;
        return Arrays.deepEquals(this.data, otherMatrix.data);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.data);
    }

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

    public Matrix add(Matrix other) {
        if (other == null || this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("You cant add matrix with different size");
        }

        int[][] resultData = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultData[i][j] = this.data[i][j] + other.data[i][j];
            }
        }

        return new Matrix(resultData);
    }

    public Matrix multiplyByScalar(int scalar) {
        int[][] resultData = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultData[i][j] = this.data[i][j] * scalar;
            }
        }

        return new Matrix(resultData);
    }

    public Matrix multiply(Matrix other) {
        if (other == null || this.columns != other.rows) {
            throw new IllegalArgumentException("Incorrect matrix sizes");
        }

        int[][] resultData = new int[this.rows][other.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    resultData[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }

        return new Matrix(resultData);
    }

    public Matrix transpose() {
        int[][] transposedData = new int[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedData[j][i] = this.data[i][j];
            }
        }
        return new Matrix(transposedData);
    }

    public static Matrix diagonalMatrix(int[] vector) {
        int size = vector.length;
        int[][] diagonalData = new int[size][size];
        for (int i = 0; i < size; i++) {
            diagonalData[i][i] = vector[i];
        }
        return new Matrix(diagonalData);
    }

    public static Matrix identityMatrix(int size) {
        int[][] identityData = new int[size][size];
        for (int i = 0; i < size; i++) {
            identityData[i][i] = 1;
        }
        return new Matrix(identityData);
    }

    public static Matrix randomRowMatrix(int columns, int maxValue) {
        Random random = new Random();
        int[][] rowData = new int[1][columns];

        for (int j = 0; j < columns; j++) {
            rowData[0][j] = random.nextInt(maxValue + 1);
        }

        return new Matrix(rowData);
    }

    public static Matrix randomColumnMatrix(int rows, int maxValue) {
        Random random = new Random();
        int[][] columnData = new int[rows][1];

        for (int i = 0; i < rows; i++) {
            columnData[i][0] = random.nextInt(maxValue + 1);
        }

        return new Matrix(columnData);
    }

    public Matrix toTriangularForm() {
        int[][] triangularData = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(this.data[i], 0, triangularData[i], 0, columns);
        }

        int minDim = Math.min(rows, columns);

        // Елементарні перетворення над рядками
        for (int k = 0; k < minDim; k++) {
            // Зробимо елемент triangularData[k][k] ненульовим
            if (triangularData[k][k] == 0) {
                int nonZeroRow = findNonZeroRow(triangularData, k, k);
                if (nonZeroRow == -1) {
                    // Матриця вже є в трикутному вигляді
                    break;
                }
                swapRows(triangularData, k, nonZeroRow);
            }

            // Зробимо нульовими елементи подальших рядків у цьому стовпці
            for (int i = k + 1; i < rows; i++) {
                int factor = triangularData[i][k] / triangularData[k][k];
                for (int j = k; j < columns; j++) {
                    triangularData[i][j] -= factor * triangularData[k][j];
                }
            }
        }

        return new Matrix(triangularData);
    }

    public Matrix toLowerTriangularForm() {
        int[][] lowerTriangularData = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(this.data[i], 0, lowerTriangularData[i], 0, columns);
        }

        int minDim = Math.min(rows, columns);

        // Елементарні перетворення над стовпцями
        for (int k = 0; k < minDim; k++) {
            // Зробимо елемент lowerTriangularData[k][k] ненульовим
            if (lowerTriangularData[k][k] == 0) {
                int nonZeroColumn = findNonZeroColumn(lowerTriangularData, k, k);
                if (nonZeroColumn == -1) {
                    // Матриця вже є в нижньотрикутному вигляді
                    break;
                }
                swapColumns(lowerTriangularData, k, nonZeroColumn);
            }

            // Зробимо нульовими елементи подальших стовпців у цьому рядку
            for (int j = k + 1; j < columns; j++) {
                int factor = lowerTriangularData[k][j] / lowerTriangularData[k][k];
                for (int i = k; i < rows; i++) {
                    lowerTriangularData[i][j] -= factor * lowerTriangularData[i][k];
                }
            }
        }

        return new Matrix(lowerTriangularData);
    }

    private int findNonZeroColumn(int[][] matrix, int row, int startColumn) {
        for (int j = startColumn + 1; j < columns; j++) {
            if (matrix[row][j] != 0) {
                return j;
            }
        }
        return -1; // Не знайдено ненульового стовпця
    }

    private void swapColumns(int[][] matrix, int col1, int col2) {
        for (int i = 0; i < rows; i++) {
            int temp = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = temp;
        }
    }

    private int findNonZeroRow(int[][] matrix, int startRow, int column) {
        for (int i = startRow + 1; i < rows; i++) {
            if (matrix[i][column] != 0) {
                return i;
            }
        }
        return -1; // Не знайдено ненульового рядка
    }

    private void swapRows(int[][] matrix, int row1, int row2) {
        int[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
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