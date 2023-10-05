import java.util.Scanner;

public class interpolasi {

    public static double[][] inputXY(){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        double[][] xy = new double[n][2];
        for (int i = 0; i < n; i++){
            xy[i][0] = input.nextDouble();
            xy[i][1] = input.nextDouble();
        }
        return xy;
    }

    public static String[] interpolasiL(double[][] xy) {
        Scanner input = new Scanner(System.in);
        double x = input.nextDouble();

        String output[] = new String[2];
        output[0] = "";
        output[1] = "";
        int n = xy.length;

        double[][] matrix = new double[n][n + 1];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n + 1; col++) {
                if (col == 0) {
                    matrix[row][col] = 1;
                } else if (col == n) {
                    matrix[row][col] = xy[row][1];
                } else {
                    matrix[row][col] = Math.pow(xy[row][0], col);
                }
            }
        }
        Matrix.echelon(matrix);
        String[] out = Matrix.solusi(matrix);
        // luaran berbentuk f(x) = a1 + a2x + a3x^2 + ...
        if (out[0] == "Solusi tidak ada.") {
            output[0] = out[0];
            output[1] = "";
            return output;
        } else {
            output[0] += "f(x) = ";
            for (int i = 0; i < out.length; i++) {
                if (Matrix.isNumber(out[i])) {
                    if (Double.parseDouble(out[i]) == 0) {
                        continue;
                    }
                }

                if (i == 0) {
                    output[0] += "(" + out[i] + ")";
                } else {
                    output[0] += "(" + out[i] + ")" + "x^" + i;
                }
                if (i != out.length - 1)
                    output[0] += " + ";
            }
            // hitung f(x)
            if (regresi.isParameter(out)){
                output[1] += "f(" + x + ") =";
                for (int i = 0; i < out.length; i++) {
                    if (Matrix.isNumber(out[i])) {
                        if (Double.parseDouble(out[i]) == 0) {
                            continue;
                        }
                    }

                    if (i == 0) {
                        output[1] += "(" + out[i] + ")";
                    } else {
                        output[1] += "(" + out[i] + ")" + Math.pow(x, i);
                    }
                    if (i != out.length - 1)
                        output[1] += " + ";     
                }
            }
            else {
                double sum = Double.parseDouble(out[0]);
                for (int i = 1; i < out.length; i++){
                    sum += Double.parseDouble(out[i]) * Math.pow(x, i);
                }
                output[1] += "f(" + x + ") =";
                output[1] += " " + sum;
            }

            return output;
        }
    }
}
