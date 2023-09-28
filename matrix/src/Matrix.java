import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import test.*;

public class Matrix {
    public static double[][] readtxtmat(String str) {
        String path = str;

        // Initialize a list to store the rows of the matrix
        List<List<Double>> matrix = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split("\\s+");
                List<Double> row = new ArrayList<>();
                for (String element : elements) {
                    row.add(Double.parseDouble(element));
                }
                matrix.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int Row = matrix.size();
        int Col = matrix.get(0).size();
        double[][] normal = new double[Row][Col];

        for (int i = 0; i < Row; i++) {
            List<Double> row = matrix.get(i);
            normal[i] = new double[Col];

            for (int j = 0; j < Col; j++) {
                normal[i][j] = row.get(j);
            }
        }
        return normal;

    }

    public static void main(String[] args) {
        double[][] ma = readmatrix();
        echelon(ma);
        printmatrix(ma);
        solusi(ma);
    }

    public static double[][] readmatrix() {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        double[][] mat = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = scanner.nextDouble();
            }
        }
        return mat;
    }

    public static void echelon(double[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int i = 0;
        int j = 0;
        // cari baris utama yang tidak0
        while (i < row && j < col - 1) {
            int a = i;
            while (mat[a][j] == 0.0) {
                a++;
                if (a == row) {
                    break;
                }
            }
            if (a != row) {// swap
                double[] temp = mat[a];
                mat[a] = mat[i];
                mat[i] = temp;
                double pembagi = mat[i][j];// buat satu utama
                for (int k = 0; k < col; k++) {
                    mat[i][k] /= pembagi;
                }
                for (int k = 0; k < row; k++) {
                    if (k > i) {
                        double pengurang = mat[k][j];
                        for (int l = 0; l < col; l++) {
                            mat[k][l] -= pengurang * mat[i][l];
                        }
                    }
                }
                i++;
                j++;
            } else {
                j++;
            }
        }
    }

    public static void reducechelon(double[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int i = 0;
        int j = 0;
        // cari baris utama yang tidak0
        while (i < row && j < col - 1) {
            int a = i;
            while (mat[a][j] == 0.0) {
                a++;
                if (a == row) {
                    break;
                }
            }
            if (a != row) {// swap
                double[] temp = mat[a];
                mat[a] = mat[i];
                mat[i] = temp;
                double pembagi = mat[i][j];// buat satu utama
                for (int k = 0; k < col; k++) {
                    mat[i][k] /= pembagi;
                }
                for (int k = 0; k < row; k++) {
                    if (k != i) {
                        double pengurang = mat[k][j];
                        for (int l = 0; l < col; l++) {
                            mat[k][l] -= pengurang * mat[i][l];
                        }
                    }
                }
                i++;
                j++;
            } else {
                j++;
            }
        }
    }

    public static void printmatrix(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.printf("%f", mat[i][j]);
                if (j != (mat[0].length - 1)) {
                    System.out.printf(" ");
                } else {
                    System.out.printf("\n");
                }
            }
        }
    }

    public static double[][] inversejordan(double[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        double[][] hasil = new double[row][col];
        for (int c = 0; c < row; c++) {
            for (int d = 0; d < col; d++) {
                if (c == d) {
                    hasil[c][d] = 1;
                } else {
                    hasil[c][d] = 0;
                }
            }
        }
        int i = 0;
        int j = 0;
        // cari baris utama yang tidak0
        while (i < row && j < col) {
            int a = i;
            while (mat[a][j] == 0.0) {
                a++;
                if (a == row) {
                    break;
                }
            }
            if (a != row) {// swap
                double[] temp = mat[a];
                mat[a] = mat[i];
                mat[i] = temp;
                double[] temp2 = hasil[a];
                hasil[a] = hasil[i];
                hasil[i] = temp2;
                double pembagi = mat[i][j];// buat satu utama
                for (int k = 0; k < col; k++) {
                    mat[i][k] /= pembagi;
                    hasil[i][k] /= pembagi;
                }
                for (int k = 0; k < row; k++) {
                    if (k != i) {
                        double elbawah = mat[k][j];
                        for (int l = 0; l < col; l++) {
                            mat[k][l] -= elbawah * mat[i][l];
                            hasil[k][l] -= elbawah * hasil[i][l];
                        }
                    }
                }
                i++;
                j++;
            } else {
                j++;
            }
        }
        boolean flag = true;
        for (int a = 0; a < row; a++) {
            for (int b = 0; b < col; b++) {
                if (mat[a][b] != 0) {
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        if (flag) {
            System.out.println("Matriks tidak memiliki balikan. Abaikan jika ada print matrix.");
        }
        return hasil;
    }

    public static void solusi(double[][] matrix) {
        String[] solusi = new String[Determinan.getLastIdxCol(matrix)]; 
        for (int i = 0; i <= Determinan.getLastIdxRow(matrix); i++){
            for (int j = i; j < Determinan.getLastIdxCol(matrix); j++){
                if (matrix[i][j] == 1){
                    solusi[j] = Double.toString(matrix[i][Determinan.getLastIdxCol(matrix)]);
                    break;
                }
            }
        }

        int idx = 0;
        for (int i = 0; i < solusi.length; i++){
            char ch = (char) ('a' + idx);
            if (solusi[i] == null){
                solusi[i] = "1.0" + ch + "";
                idx++;
            }
        }

        HashMap<Integer, Double> hm = new HashMap<>();
        for (int i = Determinan.getLastIdxRow(matrix); i >= 0; i--){
            for (int j = i; j < Determinan.getLastIdxCol(matrix); j++){
                if ((matrix[i][j] == 1) && (j == Determinan.getLastIdxCol(matrix) - 1)){
                    hm.put(j, matrix[i][j + 1]);
                }
                else if ((matrix[i][j] == 1) && (j != Determinan.getLastIdxCol(matrix) - 1)){
                    double tempI = matrix[i][Determinan.getLastIdxCol(matrix)];
                    String tempS = "";
                    for (int k = j + 1; k < Determinan.getLastIdxCol(matrix); k++){
                        if (hm.get(k) == null){
                            if  (matrix[i][k] != 0){
                                tempS += multString(solusi[k], -1 * matrix[i][k]);
                                tempS = smp(tempS);
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
    }

    public static String multString(String str, double val){ // belum selesai
        char[] spl = str.toCharArray();
        String out = "";
        String angka = "";
        //char op;
        
        for (char c : spl){
            if (Character.isDigit(c) || c == '.'){
                angka += c;
            }
            else{
                if (!angka.isEmpty()){
                    System.out.println(angka);
                    out += " " + Double.toString(Double.parseDouble(angka) * val);
                    angka = "";
                }
                out += c + "";
            }
        }
        return out;
    }

    public static String smp(String str){
        char[] part = str.replaceAll("\\s", "").toCharArray();
        String out = "";
        for (int i = 1; i < part.length; i++){
            if (part[i] == '-' && part[i - 1] == '-'){
                part[i] = '+';
                part[i - 1] = ' ';
            }
        }
        for (char c : part){
            if (c != ' ' && c != '+' && c != '-') out += c;
            else if (c == '+' || c == '-'){
                out += " " + c + " ";
            }
        }
        return out;
    }
}
