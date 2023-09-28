import java.util.Scanner;

public class Main {
    public double[][] mat;

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            displayMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    runSubMenu();
                    break;
                case 2:
                    subsubmenu();
                    break;
                case 3:
                    subsubmenu();
                    break;
                case 4:
                    subsubmenu();
                    break;
                case 5:
                    subsubmenu();
                    break;
                case 6:
                    subsubmenu();
                    break;
                case 7:
                    System.out.println("Exiting the program.");
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
        Scanner scanner = new Scanner(System.in);
        int subChoice;

        while (true) {
            displaySubMenu();
            subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
        Scanner scanner = new Scanner(System.in);
        int subsubChoice;

        while (true) {
            System.out.println("Metoda Input:");
            System.out.println("1. Input User");
            System.out.println("2. Input txt");
            subsubChoice = scanner.nextInt();

            switch (subsubChoice) {
                case 1:
                    mat = Matrix.readmatrix();
                    break;
                case 2:
                    System.out.println("Masukkan nama file:");
                    String str = scanner.nextLine();
                    mat = Matrix.readtxtmat(str);
                    break;
                case 3:
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