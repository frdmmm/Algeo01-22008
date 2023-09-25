import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
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
}
