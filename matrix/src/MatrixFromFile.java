import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixFromFile {
    public static void main(String[] args) {
        // Specify the path to your text file
        String filePath = "data.txt";

        // Initialize a list to store the rows of the matrix
        List<List<Double>> matrix = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                String[] elements = line.split("\\s+");
                List<Double> row = new ArrayList<>();

                // Convert each element to a double and add it to the row
                for (String element : elements) {
                    row.add(Double.parseDouble(element));
                }

                // Add the row to the matrix
                matrix.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Now, you have your matrix in the 'matrix' list
        // You can access elements using matrix.get(row).get(column)

        // Example: Print the matrix
        for (List<Double> row : matrix) {
            for (Double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}