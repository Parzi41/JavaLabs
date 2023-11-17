package org.example;

import java.util.Arrays;
import java.util.Random;

public final class ImutableMatrix {
    private final int rows;
    private final int columns;
    private final double[][] data;

    public ImutableMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Columns and rows must be bigger than 0");
        }

        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
    }

    public ImutableMatrix(double[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0) {
            throw new IllegalArgumentException("Incorrect data");
        }

        this.rows = values.length;
        this.columns = values[0].length;
        this.data = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] = values[i][j];
            }
        }
    }

    public ImutableMatrix(ImutableMatrix other) {
        this.rows = other.getRows();
        this.columns = other.getColumns();
        this.data = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(other.data[i], 0, this.data[i], 0, columns);
        }
    }

    public ImutableMatrix add(ImutableMatrix other) {
        if (other == null || this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("You cant add matrix's with different size");
        }

        double[][] resultData = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultData[i][j] = this.data[i][j] + other.data[i][j];
            }
        }

        return new ImutableMatrix(resultData);
    }

    public ImutableMatrix multiplyByScalar(int scalar) {
        double[][] resultData = new double[rows][columns];
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

        double[][] resultData = new double[this.rows][other.columns];
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
        double[][] transposedData = new double[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedData[j][i] = this.data[i][j];
            }
        }
        return new ImutableMatrix(transposedData);
    }

    public static ImutableMatrix diagonalMatrix(int[] vector) {
        int size = vector.length;
        double[][] diagonalData = new double[size][size];
        for (int i = 0; i < size; i++) {
            diagonalData[i][i] = vector[i];
        }
        return new ImutableMatrix(diagonalData);
    }

    public static ImutableMatrix identityMatrix(int size) {
        double[][] identityData = new double[size][size];
        for (int i = 0; i < size; i++) {
            identityData[i][i] = 1;
        }
        return new ImutableMatrix(identityData);
    }

    public static ImutableMatrix randomRowMatrix(int columns, int maxValue) {
        Random random = new Random();
        double[][] rowData = new double[1][columns];

        for (int j = 0; j < columns; j++) {
            rowData[0][j] = random.nextInt(maxValue + 1);
        }

        return new ImutableMatrix(rowData);
    }

    public static ImutableMatrix randomColumnMatrix(int rows, int maxValue) {
        Random random = new Random();
        double[][] columnData = new double[rows][1];

        for (int i = 0; i < rows; i++) {
            columnData[i][0] = random.nextInt(maxValue + 1);
        }

        return new ImutableMatrix(columnData);
    }

    public ImutableMatrix toTriangularForm() {
        double[][] triangularData = new double[rows][columns];
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
                double factor = triangularData[i][k] / triangularData[k][k];
                for (int j = k; j < columns; j++) {
                    triangularData[i][j] -= factor * triangularData[k][j];
                }
            }
        }

        return new ImutableMatrix(triangularData);
    }

    public ImutableMatrix toLowerTriangularForm() {
        double[][] lowerTriangularData = new double[rows][columns];
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
                double factor = lowerTriangularData[k][j] / lowerTriangularData[k][k];
                for (int i = k; i < rows; i++) {
                    lowerTriangularData[i][j] -= factor * lowerTriangularData[i][k];
                }
            }
        }

        return new ImutableMatrix(lowerTriangularData);
    }

    private int findNonZeroColumn(double[][] matrix, int row, int startColumn) {
        for (int j = startColumn + 1; j < columns; j++) {
            if (matrix[row][j] != 0) {
                return j;
            }
        }
        return -1;
    }

    private void swapColumns(double[][] matrix, int col1, int col2) {
        for (int i = 0; i < rows; i++) {
            double temp = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = temp;
        }
    }

    private int findNonZeroRow(double[][] matrix, int startRow, int column) {
        for (int i = startRow + 1; i < rows; i++) {
            if (matrix[i][column] != 0) {
                return i;
            }
        }
        return -1;
    }

    private void swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    public ImutableMatrix inverse() {
        if (rows != columns) {
            throw new UnsupportedOperationException("Matrix must be squared");
        }


        double[][] augmentedMatrixData = new double[rows][2 * columns];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(this.data[i], 0, augmentedMatrixData[i], 0, columns);
            augmentedMatrixData[i][columns + i] = 1;
        }

        for (int k = 0; k < rows; k++) {
            if (augmentedMatrixData[k][k] == 0) {
                int nonZeroRow = -1;
                for (int i = k + 1; i < rows; i++) {
                    if (augmentedMatrixData[i][k] != 0) {
                        nonZeroRow = i;
                        break;
                    }
                }

                if (nonZeroRow == -1) {
                    throw new UnsupportedOperationException("The determinant is 0, the matrix is not invertible");
                }
                double[] temp = augmentedMatrixData[k];
                augmentedMatrixData[k] = augmentedMatrixData[nonZeroRow];
                augmentedMatrixData[k] = temp;
            }

            double pivot = augmentedMatrixData[k][k];
            for (int j = 0; j < 2 * columns; j++) {
                augmentedMatrixData[k][j] /= pivot;
            }

            for (int i = 0; i < rows; i++) {
                if (i != k) {
                    double factor = augmentedMatrixData[i][k];
                    for (int j = 0; j < 2 * columns; j++) {
                        augmentedMatrixData[i][j] -= factor * augmentedMatrixData[k][j];
                    }
                }
            }
        }

        double[][] inverseMatrixData = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(augmentedMatrixData[i], columns, inverseMatrixData[i], 0, columns);
        }

        return new ImutableMatrix(inverseMatrixData);
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

    public double getElement(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IllegalArgumentException("Wrong matrix cords");
        }
        return data[row][column];
    }

    public double[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows) {
            throw new IllegalArgumentException("Wrong row index");
        }

        return Arrays.copyOf(data[rowIndex], columns);
    }

    public double[] getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= columns) {
            throw new IllegalArgumentException("Wrong column index");
        }

        double[] column = new double[rows];
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



