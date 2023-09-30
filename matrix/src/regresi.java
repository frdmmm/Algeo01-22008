public class regresi {
    public static void main(String[] args){
        double[][] matrix = Matrix.readmatrix();
        regresiL(matrix);
    }

    public static double colSum(double[][] matrix, int col){
        double out = 0;
        for (int i = 0; i <= Determinan.getLastIdxRow(matrix); i++){
            out += matrix[i][col];
        }
        return out;
    }
    
    public static void regresiL(double[][] matrix){
        double[][] tabelRegresi = new double[Determinan.getLastIdxCol(matrix) + 1][Determinan.getLastIdxCol(matrix) + 2];
        int k = 1;
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
        Matrix.solusi(tabelRegresi, true, false, "test.txt");
        // luaran bentuk f(x) = b1 + b2x + b3x2 + ...
    }
}
