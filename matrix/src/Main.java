import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public double[][] mat;
    public double[][] hasil;
    public boolean determinan = false;
    public Scanner scan = new Scanner(System.in);
    public boolean print = true;
    public boolean txt = true;

    public void runMenu() {
        int choice;

        while (true) {
            displayMenu();
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    runSubMenu();
                    break;
                case 2:
                    determinan = true;
                    subsubmenu();
                    determinan = false;
                    printortxt();
                    double has = Determinan.determinanKofaktor(mat);
                    if (print) {
                        System.out.println("Determinan matrix adalah " + has);
                    }
                    if (txt) {
                        System.out.println("Masukkan nama file (contoh:File.txt)");
                        String nama = scan.next();
                        Matrix.writetxtmat(nama, mat);
                        try (BufferedWriter bf = new BufferedWriter(new FileWriter(nama, true))) {
                            bf.write("Determinan nya adalah " + has);
                            bf.flush();
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // masukkan dalam file(pengingat)
                    break;
                case 3:
                    subsubmenu();
                    printortxt();
                    if (Determinan.determinanKofaktor(mat) != 0) {
                        mat = Determinan.balikan(mat);
                        if (print) {
                            Matrix.printmatrix(mat);
                        }
                        if (txt) {
                            System.out.println("Masukkan nama file (contoh:File.txt)");
                            String nama = scan.next();
                            Matrix.writetxtmat(nama, mat);
                        }
                    } else {
                        System.out.println("Matriks tidak memiliki balikan");
                    }
                    break;
                case 4:
                    subsubmenu();
                    printortxt();
                    break;
                case 5:
                    subsubmenu();
                    printortxt();
                    break;
                case 6:
                    subsubmenu();
                    printortxt();
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid. Ulangi");
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linier Berganda");
        System.out.println("7. Keluar");
        System.out.print("Masukkan pilihan: ");
    }

    private void runSubMenu() {
        int subChoice;

        while (true) {
            displaySubMenu();
            subChoice = scan.nextInt();

            switch (subChoice) {
                case 1:
                    subsubmenu();
                    printortxt();
                    Matrix.echelon(mat);
                    Matrix.solusi(mat);
                    return;
                case 2:
                    subsubmenu();
                    return;
                case 3:
                    subsubmenu();
                    return;
                case 4:
                    subsubmenu();
                    return;
                default:
                    System.out.println("Invalid. Ulangi");
                    break;
            }
        }
    }

    private void displaySubMenu() {
        System.out.println("SPL:");
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("5. Back");
        System.out.print("Pilihan: ");
    }

    public void subsubmenu() {
        int subsubChoice;

        while (true) {
            System.out.println("Metoda Input:");
            System.out.println("1. Input User");
            System.out.println("2. Input txt");
            subsubChoice = scan.nextInt();

            switch (subsubChoice) {
                case 1:
                    if (determinan) {
                        mat = Matrix.readmatrix1();
                        return;
                    } else {
                        mat = Matrix.readmatrix();
                        return;
                    }
                case 2:
                    System.out.println("Masukkan nama file:");
                    String str = scan.nextLine();
                    mat = Matrix.readtxtmat(str);
                    return;
                default:
                    System.out.println("Invalid. Ulangi");
                    break;
            }
        }
    }

    public void printortxt() {
        int pout;
        while (true) {
            System.out.println("Metode output");
            System.out.println("1. Print");
            System.out.println("2. File");
            System.out.println("3. Keduanya");
            pout = scan.nextInt();
            switch (pout) {
                case 1:
                    print = true;
                    return;
                case 2:
                    txt = true;
                    return;
                case 3:
                    print = true;
                    txt = true;
                    return;
                default:
                    System.out.println("Invalid. Ulangi");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Main algeo = new Main();
        algeo.runMenu();
    }
}
