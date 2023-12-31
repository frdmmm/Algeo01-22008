import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public double[][] mat;
    public double[][] hasil;
    public boolean mat1 = false;
    public Scanner scan = new Scanner(System.in);
    public boolean print = true;
    public boolean txt = true;
    public boolean interpo = false;
    public boolean reg = false;

    public void run() {
        int choice;

        while (true) {
            Menu();
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    runSubMenu();
                    break;
                case 2:
                    mat1 = true;
                    subsubmenu();
                    mat1 = false;
                    printortxt();
                    boolean metdete = true;
                    System.out.println("Pilih Metode Determinan:");
                    System.out.println("1. Determinan Kofaktor");
                    System.out.println("2. Determinan Adjoin");
                    int pi = scan.nextInt();
                    switch (pi) {
                        case 1:
                            metdete = true;
                            break;
                        case 2:
                            metdete = false;
                            break;
                        case 3:
                            metdete = true;
                            System.out.println("Pilihan invalid. Akan dipilih metode kofaktor");
                            break;
                    }
                    double has;
                    if (metdete) {
                        has = Determinan.determinanKofaktor(mat);
                    } else {
                        has = Determinan.determinanReduksiBaris(mat);
                    }
                    if (print) {
                        System.out.println("Determinan matrix adalah " + has);
                    }
                    if (txt) {
                        System.out.println("Masukkan nama file (contoh:File.txt)");
                        String nama = scan.next();
                        Matrix.writetxtmat(nama, mat);
                        try (BufferedWriter bf = new BufferedWriter(new FileWriter("../test/" + nama, true))) {
                            bf.write("Determinan nya adalah " + has);
                            bf.flush();
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    mat1=true;
                    subsubmenu();
                    mat1=false;
                    if (Determinan.determinanKofaktor(mat) != 0) {
                        printortxt();
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
                    mat1 = true;
                    interpo = true;
                    subsubmenu();
                    mat1 = false;
                    interpo = false;
                    double x = interpolasi.inpX();
                    String[] hasilinter = interpolasi.interpolasiL(mat, x);
                    printortxt();
                    if (print) {
                        System.out.println(hasilinter[0]);
                        System.out.println(hasilinter[1]);
                    }
                    if (txt) {
                        System.out.println("Masukkan nama file (contoh:File.txt)");
                        String nama = scan.next();
                        try (BufferedWriter bf = new BufferedWriter(new FileWriter("../test/" + nama))) {
                            bf.write("" + hasilinter);
                            bf.flush();
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 5:
                    System.out.println("Masukkan nama file:");
                    String str = scan.next();
                    printortxt();
                    double hasil = bicubicspline.bisolusi(str);
                    if (print) {
                        System.out.println("" + hasil);
                    }
                    if (txt) {
                        System.out.println("Masukkan nama file (contoh:File.txt)");
                        String nama = scan.next();
                        try (BufferedWriter bf = new BufferedWriter(new FileWriter("../test/" + nama))) {
                            bf.write("" + hasil);
                            bf.flush();
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 6:
                    reg = true;
                    subsubmenu();
                    reg = false;
                    double[] xk = regresi.inpXK(mat);
                    printortxt();
                    String[] hasilreg = regresi.regresiL(mat, xk);
                    if (print) {
                        System.out.println(hasilreg[0]);
                        System.out.println(hasilreg[1]);
                    }
                    if (txt) {
                        System.out.println("Masukkan nama file (contoh:File.txt)");
                        String nama = scan.next();
                        try (BufferedWriter bf = new BufferedWriter(new FileWriter("../test/" + nama))) {
                            bf.write("" + hasilreg);
                            bf.flush();
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
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

    public void Menu() {
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

    public void runSubMenu() {
        int subChoice;
        String nama = "";
        String[] solusi;
        while (true) {
            displaySubMenu();
            subChoice = scan.nextInt();

            switch (subChoice) {
                case 1:
                    subsubmenu();
                    printortxt();
                    Matrix.echelon(mat);
                    solusi = Matrix.solusi(mat);
                    if (print) {
                        for (int i = 0; i < solusi.length; i++) {
                            System.out.println("x" + (i + 1) + " = " + solusi[i]);
                        }
                    }
                    if (txt) {
                        System.out.println("Masukkan nama file (contoh:File.txt)");
                        nama = scan.next();
                        try (BufferedWriter bf = new BufferedWriter(new FileWriter("../test/" + nama))) {
                            for (int i = 0; i < solusi.length; i++) {
                                bf.write("x" + (i + 1) + " = " + solusi[i]);
                                bf.newLine();
                            }
                            bf.flush();
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                case 2:
                    subsubmenu();
                    printortxt();
                    Matrix.reducechelon(mat);
                    solusi = Matrix.solusi(mat);
                    if (print) {
                        for (int i = 0; i < solusi.length; i++) {
                            System.out.println("x" + (i + 1) + " = " + solusi[i]);
                        }
                    }
                    if (txt) {
                        System.out.println("Masukkan nama file (contoh:File.txt)");
                        nama = scan.next();
                        try (BufferedWriter bf = new BufferedWriter(new FileWriter("../test/" + nama))) {
                            for (int i = 0; i < solusi.length; i++) {
                                bf.write("x" + (i + 1) + " = " + solusi[i]);
                                bf.newLine();
                            }
                            bf.flush();
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                case 3:
                    subsubmenu();
                    printortxt();
                    String hasil = Matrix.splbalikan(mat);
                    if (txt) {
                        System.out.println("Masukkan nama file (contoh:File.txt)");
                        nama = scan.next();
                        try (BufferedWriter bf = new BufferedWriter(new FileWriter("../test/" + nama))) {
                            bf.write(hasil);
                            bf.flush();
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (print) {
                        System.out.println(hasil);
                    }
                    return;
                case 4:
                    subsubmenu();
                    if (mat[0].length!=mat.length+1){
                        System.out.println("Error! Ukuran matriks tidak tepat. Fungsi ini hanya menerima matrikx n.(n+1)");
                    }
                    else{
                        double[] ha = Determinan.kramer(mat);
                        printortxt();
                        if (print) {
                            if (Determinan.determinanKofaktor(mat)!=0){
                                for (int i = 0; i < ha.length; i++) {
                                    System.out.println("x" + (i + 1) + " = " + ha[i]);
                                }
                            }else{
                                System.out.println("Error! Determinan matriks bernilai 0.");
                            }
                        }
                        if (txt) {
                            System.out.println("Masukkan nama file (contoh:File.txt)");
                            nama = scan.next();
                            try (BufferedWriter bf = new BufferedWriter(new FileWriter("../test/" + nama))) {
                                if (Determinan.determinanKofaktor(mat)!=0)
                                {   for (int i = 0; i < ha.length; i++) {
                                        bf.write("x" + (i + 1) + " = " + ha[i]);
                                        bf.newLine();
                                    }
                                }
                                else{
                                    bf.write("Error! Determinan matriks bernilai 0.");
                                }
                                bf.flush();
                                bf.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return;
                case 5:
                    return;
                default:
                    System.out.println("Invalid. Ulangi");
                    break;
            }
        }
    }

    public void displaySubMenu() {
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
                    if (mat1) {
                        if (interpo){
                            mat = interpolasi.inputXY();
                        }
                        else {
                            mat = Matrix.readmatrix1();
                        }
                        return;
                    } else {
                        if (reg) {
                            mat = regresi.inputRegresi();
                        }
                        else {
                            mat = Matrix.readmatrix();
                        }
                        return;
                    }
                case 2:
                    System.out.println("Masukkan nama file:");
                    String str = scan.next();
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
                    txt = false;
                    return;
                case 2:
                    txt = true;
                    print = false;
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
        algeo.run();
    }
}
