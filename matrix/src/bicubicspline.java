import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class bicubicspline {
    public static double bisolusi(String str) {
        List<Double> matrix = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("../test/" + str))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split("\\s+");
                for (String element : elements) {
                    matrix.add(Double.parseDouble(element));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int col = matrix.size() - 2;
        double[][] normal = new double[col][1];
        double[] cari = new double[2];
        for (int i = 0; i < col; i++) {
            normal[i][0] = matrix.get(i);
        }
        int n = 0;
        for (int i = col; i < matrix.size(); i++) {
            cari[n] = matrix.get(i);
            n++;
        }
        double[][] bicubicbase = Matrix.readtxtmat("bicubic.txt");
        bicubicbase = Matrix.inversejordan(bicubicbase);
        double[][] solusi = Matrix.kalimatri(bicubicbase, normal);
        double sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int idx = (4 * i) + j;
                sum += solusi[idx][0] * (Math.pow(cari[0], i)) * (Math.pow(cari[1], j));
            }
        }
        return sum;
    }

}
