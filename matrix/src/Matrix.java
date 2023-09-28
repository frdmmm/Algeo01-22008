import java.util.Scanner;
import java.util.Arrays;

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

    public static solusi[] caris(double[][] ma) {
        int row = ma.length;
        int col = ma[0].length - 1;
        solusi[] sa = new solusi[row];
        for (int i = 0; i < row; i++) {
            sa[i].teger = ma[i][row];
            sa[i].stt.pengali = 0;
            sa[i].stt.var = "x" + (i + 1);
        }
        for (int i = row - 1; i >= 0; i--) {
            int count = 0;
            int[] idx = new int[col];
            for (int j = 0; j < col - 2; j--) {
                if (ma[i][j] != 0.0) {
                    idx[count] = j;
                    count++;
                }
            }
            if (count == 1) {
                for (int k = 0; k < i; k++) {
                    sa[k].teger = sa[k].teger - ma[k][idx[0]];
                    ma[k][idx[0]] = 0.0;
                }
            } else if (count > 1) {
                for (int l = 0; l < count; l++) {
                    for (int k = 0; k < i; k++) {
                        sa[k].stt.pengali = (ma[k][idx[l]]);
                        ma[k][idx[l]] = 0.0;
                    }
                }
            }
        }
        return sa;
    }
}

class inkstr {
    public double pengali;
    public String var;

    public static inkstr kalivar(inkstr x, int a) {
        inkstr hasil = x;
        hasil.pengali *= a;
        return hasil;
    }

    public static void printvar(inkstr x) {
        if (x.pengali != 0) {
            System.out.println(x.pengali + x.var);
        }
    }

    public String getVar() {
        return var;
    }

    public double getPengali() {
        return pengali;
    }
}

class solusi {
    public inkstr stt;
    public double teger;

    public inkstr getStt() {
        return stt;
    }

    public double getTeger() {
        return teger;
    }

    public void printsolusi(solusi sa) {
        System.out.print(sa.teger + " ");
        inkstr.printvar(sa.getStt());
    }

    public solusi kaliso(solusi sa, int a) {
        solusi hasil = sa;
        hasil.teger *= a;
        hasil.stt = inkstr.kalivar(hasil.getStt(), a);
        return hasil;
    }
}
