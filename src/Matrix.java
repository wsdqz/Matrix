import java.util.Scanner;
public class Matrix {
    // чтобы переменные использовались во всех функциях
    private int[][] matrix;
    private int rows;
    private int cols;

    // ввод матрицы
    public void inputMatrix(Scanner sc) {
        System.out.print("Введите количество строк матрицы: ");
        rows = sc.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        cols = sc.nextInt();
        matrix = new int[rows][cols];

        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Элемент [" + i + "][" + j + "]: ");
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    // вывод матрицы
    public void displayMatrix() {
        System.out.println("Исходная матрица:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // A^T
    public void transposeMatrix() {
        int[][] transpose = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }

        System.out.println("Транспонированная матрица:");
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print(transpose[i][j] + " ");
            }
            System.out.println();
        }
    }

    // diag A
    public void diagMatrix() {
        System.out.print("Диагональ матрицы: ");
        for (int i = 0; i < Math.min(rows, cols); i++) {
            System.out.print(matrix[i][i] + " ");
        }
        System.out.println("");
    }

    // tr A
    public void traceMatrix() {
        int trace = 0;
        for (int i = 0; i < Math.min(rows, cols); i++) {
            trace += matrix[i][i];
        }
        System.out.println("Трейс матрицы: " + trace);
    }

    // определитель матрицы
    public int determinantMatrix() {
        if (rows != cols) {
            System.out.println("Матрица не является квадратной, определитель нельзя вычислить.");
            return 0;
        }
        if (rows == 2) {
            int det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            System.out.println("Определитель матрицы: " + det);
            return det;
        }
        if (rows == 3) {
            int det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                    - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                    + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
            System.out.println("Определитель матрицы: " + det);
            return det;
        }
        if (rows == 4) {
            int det = 0;
            for (int i = 0; i < rows; i++) {
                int[][] minor = new int[3][3];
                for (int j = 1; j < rows; j++) {
                    int minorCol = 0;
                    for (int k = 0; k < cols; k++) {
                        if (k != i) {
                            minor[j - 1][minorCol] = matrix[j][k];
                            minorCol++;
                        }
                    }
                }
                int sign = (i % 2 == 0) ? 1 : -1;
                det += sign * matrix[0][i] * determinant3x3(minor);
            }
            System.out.println("Определитель матрицы: " + det);
            return det;
        }
        if (rows == 5) {
            int det = 0;
            for (int i = 0; i < rows; i++) {
                int[][] minor = new int[4][4];
                for (int j = 1; j < rows; j++) {
                    int minorCol = 0;
                    for (int k = 0; k < cols; k++) {
                        if (k != i) {
                            minor[j - 1][minorCol] = matrix[j][k];
                            minorCol++;
                        }
                    }
                }
                int sign = (i % 2 == 0) ? 1 : -1;
                det += sign * matrix[0][i] * determinant4x4(minor);
            }
            System.out.println("Определитель матрицы: " + det);
            return det;
        }
        if (rows == 6) {
            int det = 0;
            for (int i = 0; i < rows; i++) {
                int[][] minor = new int[5][5];
                for (int j = 1; j < rows; j++) {
                    int minorCol = 0;
                    for (int k = 0; k < cols; k++) {
                        if (k != i) {
                            minor[j - 1][minorCol] = matrix[j][k];
                            minorCol++;
                        }
                    }
                }
                int sign = (i % 2 == 0) ? 1 : -1;
                det += sign * matrix[0][i] * determinant5x5(minor);
            }
            System.out.println("Определитель матрицы: " + det);
            return det;
        }
        System.out.println("Вы ввели матрицу больше чем 6x6.");
        return 0;
    }

    // переменные для использования нахождений в матрицах 4х4, 5х5
    private int determinant3x3(int[][] minor) {
        return matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
    }

    private int determinant4x4(int[][] minor) {
        int det = 0;
        for (int i = 0; i < 3; i++) { // rows 3
            int[][] minor4 = new int[3][3];
            for (int j = 1; j < 4; j++) { // rows 4
                int minorCol = 0;
                for (int k = 0; k < 3; k++) { // cols 3
                    if (k != i) {
                        minor4[j - 1][minorCol] = minor[j][k];
                        minorCol++;
                    }
                }
            }
            int sign = (i % 2 == 0) ? 1 : -1;
            det += sign * minor[0][i] * determinant3x3(minor4);
        }
        return det;
    }

    private int determinant5x5(int[][] minor) {
        int det = 0;
        for (int i = 0; i < 5; i++) { // rows 5
            int[][] minor5 = new int[4][4];
            for (int j = 1; j < 5; j++) { // rows 5
                int minorCol = 0;
                for (int k = 0; k < 4; k++) { // cols 4
                    if (k != i) {
                        minor5[j - 1][minorCol] = minor[j][k];
                        minorCol++;
                    }
                }
            }
            int sign = (i % 2 == 0) ? 1 : -1;
            det += sign * matrix[0][i] * determinant4x4(minor5);
        }
        return det;
    }


    // ранг матрицы
    public void rankMatrix() {
        int[][] matrixCopy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, matrixCopy[i], 0, cols);
        }

        int rank = 0;

        for (int i = 0; i < Math.min(rows, cols); i++) {
            // поиск первого ненулевого элемента в текущем столбце
            int firstNonZeroRow = -1;
            for (int j = i; j < rows; j++) {
                if (matrixCopy[j][i] != 0) {
                    firstNonZeroRow = j;
                    break;
                }
            }

            // переход к следующему столбцу
            if (firstNonZeroRow == -1) {
                continue;
            }

            // + ранг при нахождении 0
            rank++;

            int[] temp = matrixCopy[i];
            matrixCopy[i] = matrixCopy[firstNonZeroRow];
            matrixCopy[firstNonZeroRow] = temp;

            //  все нижележащие строки в текущем столбце к 0
            for (int j = i + 1; j < rows; j++) {
                int factor = matrixCopy[j][i] / matrixCopy[i][i];
                for (int k = i; k < cols; k++) {
                    matrixCopy[j][k] -= factor * matrixCopy[i][k];
                }
            }
        }

        System.out.println("Ранг матрицы: " + rank);
    }
    // вывод всех данных
    public static void main(String[] args) {
        System.out.println("*** Программа находит транспонированую матрицу, диагональ и трейс матрицы, опредилитель и ранг матрицы ***");
        Scanner sc = new Scanner(System.in);
        Matrix matrixObj = new Matrix(); // создание объекта
        matrixObj.inputMatrix(sc); // ввод матрицы
        matrixObj.displayMatrix(); // вывод исходной матрицы
        matrixObj.transposeMatrix(); // транспонирование матрицы
        matrixObj.diagMatrix(); // диагональ матрицы
        matrixObj.traceMatrix(); // трейс матрицы
        matrixObj.determinantMatrix(); // определитель матрицы
        matrixObj.rankMatrix(); // ранг матрицы
    }
}
