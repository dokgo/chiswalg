import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;


public class RoomsSolution {
    private int numberOfNodes;
    private Stack<Integer> stack;
    private static int INF = Integer.MAX_VALUE / 2;


    public RoomsSolution() {
        stack = new Stack<>();
    }


    public void tsp(int adjacencyMatrix[][]) {

        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];

        visited[1] = 1;
        stack.push(1);

        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;

        boolean minFlag = false;
        System.out.print(1 + "\t");


        while (!stack.isEmpty()) {

            element = stack.peek();

            i = 1;

            min = Integer.MAX_VALUE;

            while (i <= numberOfNodes) {

                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0) {

                    if (min > adjacencyMatrix[element][i]) {

                        min = adjacencyMatrix[element][i];

                        dst = i;

                        minFlag = true;

                    }

                }

                i++;

            }

            if (minFlag) {

                visited[dst] = 1;

                stack.push(dst);

                System.out.print(dst + "\t");

                minFlag = false;

                continue;

            }

            stack.pop();

        }

    }


    public static void main(String... arg) throws IOException {

        //TODO: Add proper input reader

        int number_of_nodes;
        int number_of_vertices;

        Scanner scanner = null;
        FileReader reader = null;

        try {

            reader = new FileReader("input");
            // Enter number of roomsu
            scanner = new Scanner(reader);

            number_of_nodes = scanner.nextInt();
            number_of_vertices = scanner.nextInt();


            int distance_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];

            for (int k = 1; k <= number_of_vertices; k++) {
//                String[] vals = scanner.nextLine().split("\\s");


                int i = scanner.nextInt(); // Integer.parseInt(vals[0]);
                int j = scanner.nextInt(); // Integer.parseInt(vals[1]);
                int weight = scanner.nextInt(); //Integer.parseInt(vals[2]);

                distance_matrix[i][j] = weight;
            }

            // Scan adjacency_matrix
            for (int i = 1; i <= number_of_nodes; i++) {
                for (int j = 1; j <= number_of_nodes; j++) {
                    if (i != j && distance_matrix[i][j] == 0) {
                        distance_matrix[i][j] = INF;
                    }
                    System.out.print(distance_matrix[i][j] + " ");
                }
                System.out.println();

            }


            for (int i = 1; i <= number_of_nodes; i++) {

                for (int j = 1; j <= number_of_nodes; j++) {

                    if ( distance_matrix[i][j] == 1 && distance_matrix[j][i] == 0) {

                        distance_matrix[j][i] = 1;

                    }

                }

            }

            System.out.println("Rooms sequence: ");

            RoomsSolution tspNearestNeighbour = new RoomsSolution();

            tspNearestNeighbour.tsp(distance_matrix);

        } catch (InputMismatchException | FileNotFoundException inputMismatch) {

            System.out.println("Wrong Input format");

        } finally {
            if (scanner != null)
                scanner.close();
            if (reader != null)
                reader.close();
        }

        scanner.close();

    }

}


