import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        /*
         * matrix = 1 1 2 4
         * 0 1 1 2
         * 0 0 0 0
         */

        // hitung baris yang 0 semua
        int cnt = 0;
        boolean flag;
        for (int i = 0; i <= Determinan.getLastIdxRow(matrix); i++) {
            flag = true;
            for (int j = 0; j <= Determinan.getLastIdxRow(matrix); j++) {
                if (matrix[i][j] != 0) {
                    flag = false;
                    continue; // skip baris
                }
            }
            if (flag) {
                cnt++;
            }
        }
        // cnt = jumlah parameter
        String[] solusi = new String[Determinan.getLastIdxCol(matrix)];
        for (int i = 0; i < cnt; i++) {
            int x = 0;
            char ch = (char) ('a' + x);
            solusi[Determinan.getLastIdxRow(matrix) - (x++)] = ch + "";
        }

        for (int i = Determinan.getLastIdxRow(matrix) - cnt; i >= 0; i--) {
            int x = 0;
            solusi[i] = Double.toString(matrix[i][Determinan.getLastIdxCol(matrix)]);
            for (int j = i + 1; j <= Determinan.getLastIdxCol(matrix) - 1; j++) {
                if (cnt > 0) {
                    if (j > (Determinan.getLastIdxCol(matrix) - 1 - cnt)) {
                        solusi[i] += "-" + Double.toString(matrix[i][j]) + solusi[j];
                    } else {
                        solusi[i] += "-" + Double.toString(matrix[i][j]);
                    }
                }

            }
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(solusi[i]);
        }
    }
}
