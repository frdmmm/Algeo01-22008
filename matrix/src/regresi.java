import java.util.Scanner;

public class regresi {
    public static double[][] inputRegresi(){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();

        double[][] mat = new double[m][n + 1];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n + 1; j++){
                mat[i][j] = input.nextDouble();
            }
        }
        return mat;
    }

    public static double colSum(double[][] matrix, int col){
        double out = 0;
        for (int i = 0; i <= Determinan.getLastIdxRow(matrix); i++){
            out += matrix[i][col];
        }
        return out;
    }

    public static boolean isParameter(String[] arr){
        for (int i = 0; i < arr.length; i++){
            if (!Matrix.isNumber(arr[i])) return true;
        }
        return false;
    }
    
    public static String[] regresiL(double[][] matrix){
        Scanner input = new Scanner(System.in);
        double[] xk = new double[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++){
            xk[i] = input.nextDouble();
        }

        double[][] tabelRegresi = new double[Determinan.getLastIdxCol(matrix) + 1][Determinan.getLastIdxCol(matrix) + 2];
        int k = 1;
        String[] output = new String[2];
        output[0] = "";
        output[1] = "";
        for (int i = 0; i <= Determinan.getLastIdxRow(tabelRegresi); i++){
            for (int j = 0; j <= Determinan.getLastIdxCol(tabelRegresi); j++){
                if (j == 0){
                    if (i == 0) tabelRegresi[i][j] = Determinan.getLastIdxRow(matrix) + 1;
                    else {
                        tabelRegresi[i][j] = colSum(matrix, k++);
                    }
                }
                else{
                    if (i == 0){
                        if (j == Determinan.getLastIdxCol(tabelRegresi)){
                            tabelRegresi[i][j] = colSum(matrix, 0);
                        }
                        else {
                            tabelRegresi[i][j] = colSum(matrix, j);
                        }
                    }
                    else{
                        for (int p = 0; p <= Determinan.getLastIdxCol(matrix); p++){
                            if (j == Determinan.getLastIdxCol(tabelRegresi)){
                                tabelRegresi[i][j] += colSum(matrix, i) * matrix[p][0];
                            }
                            else {
                                tabelRegresi[i][j] += colSum(matrix, i) * matrix[p][j];
                            }
                        }
                    }
                }
        
            }

        }
        Matrix.echelon(tabelRegresi);
        String[] out = Matrix.solusi(tabelRegresi);
        // luaran bentuk f(x) = b1 + b2x + b3x2 + ...
        if (out[0] == "Solusi tidak ada."){
            output[0] = out[0];
            output[1] = "";
            return output;
        }
        else {
            output[0] += "f(x) = ";
            for (int i = 0; i < out.length; i++){
                if (Matrix.isNumber(out[i])){
                    if (Double.parseDouble(out[i]) == 0){
                        continue;
                    }
                }

                if (i == 0){
                    output[0] += "(" + out[i] + ")";
                }
                else{
                    output[0] += "(" + out[i] + ")" + "x" + i;
                }
                if (i != out.length - 1) output[0] += " + ";
            }

            // hitung f(xk)
            if (isParameter(out)){
                output[1] += "f(xk) = ";
                for (int i = 0; i < out.length; i++){
                    if (Matrix.isNumber(out[i])){
                        if (Double.parseDouble(out[i]) == 0){
                            continue;
                        }
                    }

                    if (i == 0){
                        output[1] += "(" + out[i] + ")";
                    }
                    else{
                        output[1] += "(" + out[i] + ")" + xk + i;
                    }
                    if (i != out.length - 1) output[1] += " + ";
                }
            }
            else {
                double outxk = Double.parseDouble(out[0]);
                for (int i = 1; i < out.length; i++){
                    outxk += Double.parseDouble(out[i]) * xk[i - 1];
                }
                output[1] = "f(xk) = ";
                output[1] += String.valueOf(outxk);
            }      

            return output;
        }
    }
}
