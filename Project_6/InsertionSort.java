package Project_6;

/**
 * @author Evan Eissler
 */

public class InsertionSort {

    // Store the array of words for use
    String[] inputWords;

    // Store the count of comparisions
    int comparisons = 0;

    /**
     * Class contructor for Insertion Sort
     * @param words The unsorted array of words
     */
    public InsertionSort(String[] words) {
        inputWords = words;
    }

    /**
     * Run the insertion sort algorithm
     * @return The count of comparisons
     */
    public Integer sort() {
        int n = inputWords.length;
        for(int i = 1; i < n; i++) {
            String key = inputWords[i];
            int j = i - 1;
            comparisons++;
            while(j >= 0 && inputWords[j].compareTo(key) > 0) {
                // Increment comparisons each time two words are compared to each other
                comparisons++;
                inputWords[j + 1] = inputWords[j];
                j = j - 1;
            }
            inputWords[j + 1] = key;
        }

        // Return the count of comparisons
        return comparisons;
    }
}
