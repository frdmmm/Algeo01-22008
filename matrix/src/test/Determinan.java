package test;
import java.util.Scanner;

public class Determinan {
    public static int getLastIdxRow(double[][] matrix){
        return (matrix.length - 1);
    }
    public static int getLastIdxCol(double[][] matrix){
        return (matrix[0].length - 1);
    }
    public static void readMatrix(double[][] matrix) {
        Scanner input = new Scanner(System.in);
        for (int row = 0; row <= getLastIdxRow(matrix); row++){
            for (int col = 0; col <= getLastIdxCol(matrix); col++){
                matrix[row][col] = input.nextInt();
            }
        }
    }
    public static void printMatrix(double[][] matrix) {
        for (int row = 0; row <= getLastIdxRow(matrix); row++) {
            for (int col = 0; col <= getLastIdxCol(matrix); col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
    public static double[][] cofactor(double[][] matrix, int row, int col){
        double[][] kof = new double[getLastIdxRow(matrix)][getLastIdxCol(matrix)];
        int cRow = 0, cCol = 0;
        for (int i = 0; i <= getLastIdxRow(matrix); i++){
            if (i != row) {
                for (int j = 0; j <= getLastIdxCol(matrix); j++){
                    if (j != col) {
                        kof[cRow][cCol++] = matrix[i][j];
                    }
                }
                cRow++;
                cCol = 0;
            }
        }
        return kof;
    }
    public static int plusMin(int row, int col){
        if ((row % 2 == 0 && col % 2 == 0) || (row % 2 == 1 && col == 1)) return 1;
        return -1;
    }
    public static double[][] copyMatrix(double[][] matrix) {
       double[][] copy = new double[getLastIdxRow(matrix) + 1][getLastIdxCol(matrix) + 1];
       for (int i = 0; i <= getLastIdxRow(matrix); i++) {
           for (int j = 0; j <= getLastIdxCol(matrix); j++) {
               copy[i][j] = matrix[i][j];
           }
       }
       return copy;
   }
    public static void subtractKRow(double[][] matrix, int row1, int row2){
        double k = (matrix[row2][row1] / matrix[row1][row1]);
        for (int col = 0; col < matrix[0].length; col++){
            matrix[row2][col] -= k * matrix[row1][col];
        }
   }
    public static double determinanKofaktor(double[][] matrix){
        // asumsi matrix n x n, n>=1
        if (getLastIdxRow(matrix) == 0) return (matrix[0][0]); // matrix 1x1
        if (getLastIdxRow(matrix) == 1) return ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0])); // matrix 2x2 ad-bc

        double out = 0;
        for (int j = 0; j <= getLastIdxCol(matrix); j++){
            out += plusMin(0, j) * matrix[0][j] * determinanKofaktor(cofactor(matrix, 0, j));
        }
        return out;
    }

    public static double determinanReduksiBaris(double[][] matrix){
        double [][] copyM = copyMatrix(matrix);
        // asumsi matrix adalah matrix n x n
        if (getLastIdxRow(matrix) == 0) return (matrix[0][0]); // matrix 1x1

        for (int c = 0; c <= getLastIdxCol(matrix); c++) {
            for (int i = c + 1; i <= getLastIdxRow(matrix); i++) {
                if (copyM[i][c] != 0) {
                    subtractKRow(copyM, c, i);
                }
            }
        }
        double determinan = copyM[0][0];
        for (int i = 1; i <= getLastIdxRow(matrix); i++){
            determinan *= copyM[i][i];
        }
        return (determinan);
    }
}