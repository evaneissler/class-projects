/**
 * @author Evan Eissler
 * Data Structures Project 1 Revision
 * Color a matrix of pixels
 */

import java.util.*;
/**
 * Class for Project 1 - Color a matrix of pixels
 */
public class Project1Revision {

    /**
     * Main method to execute the program
     * Read system input through scanner and executes recursive methods
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please paste in your input below:");
        int numMatrices = scanner.nextInt();
        List<char[][]> matrices = new ArrayList<>();

        // Loops through each matrix and builds character array
        for (int k = 1; k <= numMatrices; k++) {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            // Input the matrix
            char[][] matrix = new char[rows][columns];
            for (int i = 0; i < rows; i++) {
                String row = scanner.next();
                matrix[i] = row.toCharArray();
            }
            matrices.add(matrix);
        }

        // Goes through the character arrays of each matrix to replace the pixels, get counts, and display output
        for (int k = 0; k < matrices.size(); k++) {
            char[][] matrix = matrices.get(k);
            // Replace '*' with letter
            replaceStars(matrix);
            // Print the matrix after the pixels have been colored
            printMatrix(matrix);
            // Print letter counts of each matrix
            Map<Character, Integer> charOccurrences = countLetterOccurrences(matrix);
            Map<Integer, Integer> occurrenceCount = countOccurrenceOfOccurrence(charOccurrences);
            displayOccurrenceCount(occurrenceCount);
        }
    }

    /**
     * Replaces * with corresponding letters
     * @param matrix The matrix to be processed
     */
    private static void replaceStars(char[][] matrix) {
        // Starting letter to replace * with
        char currentLetter = 'a';
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '*') {
                    // Recursive call to replace * in neighbor pixels
                    replaceAdjacentStars(matrix, i, j, currentLetter);
                    currentLetter++;
                }
            }
        }
    }

    /**
     * Recursively replaces adjacent * with the corresponding letter
     * @param matrix The matrix to be processed
     * @param row The row index currently being searched
     * @param col The column index currently being searched
     * @param letter The letter to replace * with
     */
    private static void replaceAdjacentStars(char[][] matrix, int row, int col, char letter) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length || matrix[row][col] != '*') {
            return;
        }

        matrix[row][col] = letter;
        // Recursively replace adjacent stars
        replaceAdjacentStars(matrix, row - 1, col, letter);
        replaceAdjacentStars(matrix, row + 1, col, letter);
        replaceAdjacentStars(matrix, row, col - 1, letter);
        replaceAdjacentStars(matrix, row, col + 1, letter);
    }

    /**
     * Prints the resulting matrix
     * @param matrix The matrix being printed
     */
    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }

    /**
     * Counts the occurrences of each letter in the matrix
     * @param matrix The matrix to be processed
     * @return Mapping of each letter and its count
     */
    private static Map<Character, Integer> countLetterOccurrences(char[][] matrix) {
        Map<Character, Integer> charOccurrences = new HashMap<>();

        for (char[] row : matrix) {
            for (char column : row) {
                if (Character.isLetter(column) && column != '.') {
                    charOccurrences.put(column, charOccurrences.getOrDefault(column, 0) + 1);
                }
            }
        }

        return charOccurrences;
    }

    /**
     * Counts the occurrences of each letter count
     * @param charOccurrences Mapping of each letter and its count
     * @return Mapping of letter counts and their occurrences
     */
    private static Map<Integer, Integer> countOccurrenceOfOccurrence(Map<Character, Integer> charOccurrences) {
        Map<Integer, Integer> occurrenceCount = new HashMap<>();

        for (int occurrences : charOccurrences.values()) {
            occurrenceCount.put(occurrences, occurrenceCount.getOrDefault(occurrences, 0) + 1);
        }

        return occurrenceCount;
    }

    /**
     * Displays the occurrence count of letter counts
     * @param occurrenceCount Mapping of letter counts and their occurrences
     */
    private static void displayOccurrenceCount(Map<Integer, Integer> occurrenceCount) {
        for (Map.Entry<Integer, Integer> entry : occurrenceCount.entrySet()) {
            System.out.print(entry.getKey() + " " + entry.getValue() + "\n");
        }
        System.out.println();
    }
}
