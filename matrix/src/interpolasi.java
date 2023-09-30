import java.util.Scanner;

public class interpolasi {
    public static void main(String[] args){
        interpolasiL();
    }

    public static void interpolasiL(){
        // input
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        double[][] xy = new double[n][2];
        for (int i = 0; i < n; i++){
            xy[i][0] = input.nextDouble(); // x
            xy[i][1] = input.nextDouble(); // y
        }

        // algoritma
        double[][] matrix = new double[n][n + 1];
        for (int row = 0; row < n; row++){
            for (int col = 0; col < n + 1; col++){
                if (col == 0){
                    matrix[row][col] = 1;
                }
                else if (col == n){
                    matrix[row][col] = xy[row][1];
                }
                else{
                    matrix[row][col] = Math.pow(xy[row][0], col);
                }
            }
        }
        Matrix.echelon(matrix);
        Matrix.solusi(matrix, true, false, "a.txt");     
        // luaran berbentuk f(x) = a1 + a2x + a3x^2 + ...  
    }

}
