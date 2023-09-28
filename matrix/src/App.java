import test.Determinan;

public class App {
    public static void main(String args[]) {
        // tes
        double[][] matrix = new double[3][3];
        Determinan.readMatrix(matrix);
        Determinan.printMatrix(matrix);
        System.out.println(Determinan.determinanKofaktor(matrix));
        System.out.println(Determinan.determinanReduksiBaris(matrix));
        double[][] inverse = new double[3][3];
        inverse = Determinan.balikan(matrix);
        Determinan.printMatrix(inverse);

    }
}
