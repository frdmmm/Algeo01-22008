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

    public static String interpolasiL(double[][] xy) {
        String output = "";
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
            output = out[0];
            return output;
        } else {
            output += "f(x) = ";
            for (int i = 0; i < out.length; i++) {
                if (Matrix.isNumber(out[i])) {
                    if (Double.parseDouble(out[i]) == 0) {
                        continue;
                    }
                }

                if (i == 0) {
                    output += "(" + out[i] + ")";
                } else {
                    output += "(" + out[i] + ")" + "x^" + i;
                }
                if (i != out.length - 1)
                    output += " + ";
            }
            return output;
        }
    }

}
