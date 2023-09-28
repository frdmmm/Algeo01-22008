import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilih;
        boolean lanjut = true;

        while (lanjut) {
            MainMenu();
            pilih = scanner.nextInt();

            switch (pilih) {
                case 1:
                    handleSubMenu();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    lanjut = false;
                    break;
                default:
                    System.out.println("Pilihan invalid. Ulangi");
            }
        }
    }

    public static void MainMenu() {
        System.out.println("MENU:");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linier Berganda");
        System.out.println("7. Keluar");
    }

    public static void handleSubMenu() {
        Scanner scanner = new Scanner(System.in);
        int subChoice;
        boolean lansub = true;
        while (lansub) {
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
                    lansub = false;
                default:
                    System.out.println("Invalid. Ulangi.");
            }
        }
    }

    public static void handlesubsubmenu() {
        Scanner scanner = new Scanner(System.in);
        int subChoice;
        boolean lansub = true;
        while (lansub) {
            displaysubsubmenu();
            subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    double[][] mat = Matrix.readmatrix();
                    break;
                case 2:
                    break;
                case 3:
                    lansub = false;
                default:
                    System.out.println("Invalid. Ulangi.");
            }
        }
    }

    public static void displaysubsubmenu() {
        System.out.println("1. Input user");
        System.out.println("2. Input txt");
        System.out.println("3. Keluar");
    }

    public static void displaySubMenu() {
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("5. Keluar");
    }
}
