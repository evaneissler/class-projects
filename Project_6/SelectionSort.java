package Project_6;

/**
 * @author Evan Eissler
 */

public class SelectionSort {

    // Store the array of words for use
    String[] inputWords;

    /**
     * Class contructor for Selection Sort
     * @param words The unsorted array of words
     */
    public SelectionSort(String[] words) {
        inputWords = words;
    }

    /**
     * Run the selection sort algorithm
     * @return The count of comparisons
     */
    public Integer sort() {
        // Store the count of comparisions
        int comparisons = 0;
        int n = inputWords.length;
        for(int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for(int j = i + 1; j < n; j++) {
                // Increment comparisons each time two words are compared to each other
                comparisons++;
                if (inputWords[j].compareTo(inputWords[min_idx]) < 0) {
                    min_idx = j;
                }
            }
            // Add words back to array after sorting
            String temp = inputWords[min_idx];
            inputWords[min_idx] = inputWords[i];
            inputWords[i] = temp;
        }

        // Return the count of comparisons
        return comparisons;
    }
}
