package Project_5;

import java.util.*;

/**
 * @author Evan Eissler
 * Handles user input into program and calls corresponding methods
 */
public class Project5 {

    /**
     * Main method handles user input into program and calls corresponding methods
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Boolean running = true;

        // Initalizes new PolyList instance to store polynomials input through command-line
        PolyList polynomialList = new PolyList();

        // Continuously accepts new input until QUIT is given
        // Initializes a StringTokenizer instance on input to separate polynomials and calls corresponding methods
        while (running) {
            System.out.println("Please paste in your input below:");
            String line = scanner.nextLine();
            StringTokenizer stream = new StringTokenizer(line, " ");
            String operation = stream.nextToken();

            if (operation.equals("INSERT")) {
                polynomialList.insert(stream);
            } else if (operation.equals("DELETE")) {
                polynomialList.delete(stream);
            } else if (operation.equals("SEARCH")) {
                polynomialList.search(stream);
            } else {
                polynomialList.quit();
                scanner.close();
                running = false;
            }
        }
    }
}
