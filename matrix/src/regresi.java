public class regresi {
    public static double colSum(double[][] matrix, int col){
        double out = 0;
        for (int i = 0; i <= Determinan.getLastIdxRow(matrix); i++){
            out += matrix[i][col];
        }
        return out;
    }
    
    public static String regresiL(double[][] matrix){
        double[][] tabelRegresi = new double[Determinan.getLastIdxCol(matrix) + 1][Determinan.getLastIdxCol(matrix) + 2];
        int k = 1;
        String output = "";
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
            output = out[0];
            return output;
        }
        else {
            output += "f(x) = ";
            for (int i = 0; i < out.length; i++){
                if (Matrix.isNumber(out[i])){
                    if (Double.parseDouble(out[i]) == 0){
                        continue;
                    }
                }

                if (i == 0){
                    output += "(" + out[i] + ")";
                }
                else{
                    output += "(" + out[i] + ")" + "x" + i;
                }
                if (i != out.length - 1) output += " + ";
            }
            return output;
        }
    }
}
