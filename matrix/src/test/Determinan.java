package test;
import java.util.Scanner;
import java.util.HashMap;

// Determinan kofaktor, determinan balikan baris, balikan
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
                matrix[row][col] = input.nextDouble();
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

    // matriks balikan
    public static double[][] transpose(double[][] matrix){
        double[][] out = new double[getLastIdxCol(matrix) + 1][getLastIdxRow(matrix) + 1];
        for (int i = 0; i <= getLastIdxRow(out); i++){
            for (int j = 0; j <= getLastIdxCol(out); j++){
                out[i][j] = matrix[j][i];
            }
        }
        return out;
    }

    public static double[][] balikan(double[][] matrix){
        double[][] adjoin = new double[getLastIdxRow(matrix) + 1][getLastIdxCol(matrix) + 1];
        double det = determinanKofaktor(matrix);
        
        if ((determinanKofaktor(matrix) != 0)){
            if (getLastIdxCol(matrix) == 0) return matrix; // matrix 1x1
            if (getLastIdxCol(matrix) == 1){ // matrix 2x2
                adjoin[0][0] = matrix[1][1] / det;
                adjoin[1][1] = matrix[0][0] / det;
                adjoin[0][1] = matrix[0][1] * -1 / det;
                adjoin[1][0] = matrix[1][0] * -1 / det;
                return adjoin;
            }
            // matrix n x n (n >= 3)
            for (int i = 0; i <= getLastIdxRow(matrix); i++){
                for (int j = 0; j <= getLastIdxCol(matrix); j++){
                    adjoin[i][j] = (plusMin(i, j) * determinanKofaktor(cofactor(matrix, i, j))) / det;
                }
            }
            adjoin = transpose(adjoin);
            
            return adjoin;
        }
        return adjoin; // Tidak ada balikan, determinan = 0 (belum selesai)
    }

    // Kramer

    public static double[] kramer(double[][] matrix){
        int col = 0;
        int nSol = 0;
        double[][] SPL = new double[getLastIdxRow(matrix) + 1][getLastIdxCol(matrix)];
        double[][] SPL2 = new double[getLastIdxRow(matrix) + 1][1];
        double[][] kr = new double[getLastIdxRow(matrix) + 1][getLastIdxCol(matrix)];
        double[] out = new double[getLastIdxRow(matrix) + 1]; 
        double det2;
        // Ax = b
            // Inisialisasi SPL (Matriks A)
            for (int i = 0; i <= getLastIdxCol(SPL); i++){
                for (int j = 0; j <= getLastIdxCol(SPL); j++){
                    SPL[i][j] = matrix[i][j];
                }
            }
            double det1 = determinanKofaktor(SPL);
            //Inisialisasi SPL2 (Matriks b)
            for (int i = 0; i <= getLastIdxRow(SPL2); i++){
                SPL2[i][0] = matrix[i][getLastIdxCol(matrix)];
            }

        for (int x = 0; x <= getLastIdxRow(matrix); x++){
            // cari solusi
            for (int i = 0; i <= getLastIdxRow(kr); i++){
                for (int j = 0; j <= getLastIdxCol(kr); j++){
                    if (j != col){
                        kr[i][j] = SPL[i][j];
                    }
                    else{
                        kr[i][j] = SPL2[i][0];
                    }
                }
            }
            col++;
            det2 = determinanKofaktor(kr);
            out[nSol++] = (det2/det1);
        }
        return out;
    } 

    /*public static void main(String[] args){
        double[][] matrix = new double[3][4];
        readMatrix(matrix);
        
        String[] solusi = new String[getLastIdxCol(matrix)]; 
        for (int i = 0; i <= getLastIdxRow(matrix); i++){
            for (int j = i; j < getLastIdxCol(matrix); j++){
                if (matrix[i][j] == 1){
                    solusi[j] = Double.toString(matrix[i][getLastIdxCol(matrix)]);
                    break;
                }
            }
        }

        int idx = 0;
        for (int i = 0; i < solusi.length; i++){
            char ch = (char) ('a' + idx);
            if (solusi[i] == null){
                solusi[i] = ch + "";
                idx++;
            }
        }

        HashMap<Integer, Double> hm = new HashMap<>();
        for (int i = getLastIdxRow(matrix); i >= 0; i--){
            for (int j = i; j < getLastIdxCol(matrix); j++){
                if ((matrix[i][j] == 1) && (j == getLastIdxCol(matrix) - 1)){
                    hm.put(j, matrix[i][j + 1]);
                }
                else if ((matrix[i][j] == 1) && (j != getLastIdxCol(matrix) - 1)){
                    double tempI = matrix[i][getLastIdxCol(matrix)];
                    String tempS = "";
                    for (int k = j + 1; k < getLastIdxCol(matrix); k++){
                        if (hm.get(k) == null){
                            if  (matrix[i][k] != 0){
                                tempS += (matrix[i][k] > 0 ? " - " : "") + (matrix[i][k] > 0 ? "" : " + "); 
                                tempS += Double.toString((matrix[i][k] > 0 ? matrix[i][k] : matrix[i][k] * -1)) + "(" + solusi[k] + ")";
                            }
                        }
                        else{
                            tempI -= matrix[i][k] * hm.get(k);
                        }
                    }
                    
                    if (idx == 0) hm.put(j, tempI);
                    
                    tempS = tempS.trim();
                    if (tempI == 0){
                        solusi[j] = tempS;
                    }
                    else{
                        solusi[j] = Double.toString(tempI) + " " + tempS;
                    }
                    break;
                }
            }
        }

        // print sol
        for (int i = 0; i < solusi.length; i++){
            System.out.print("x" + (i + 1) + " = ");
            System.out.println(solusi[i]);
        }
        // 1 3 -2 0 2 0 0
        // 0 0 1 2 0 3 1
        // 0 0 0 0 0 1 0.3
        // 0 0 0 0 0 0 0

        // 1 1 2 4
        // 0 1 1 2
        // 0 0 0 0
    }*/
}