package Project_6;

/**
 * @author Evan Eissler
 */

public class QuickSort {

    String[] inputWords;
    Integer comparisons = 0;

    /**
     * Class contructor for Quick Sort
     * @param words The unsorted array of words
     */
    public QuickSort(String[] words) {
        inputWords = words;
    }

    /**
     * Run the sorting algorithm for Quick Sort
     * @return Integer count of comparisons
     */
    public Integer sort() {
        Comparator comparator = new Comparator();
        quickSortInPlace(inputWords, comparator, 0, inputWords.length - 1);
        return comparisons;
    }

    /**
     * Runs the quick sort algorithm
     * @param S The array being sorted
     * @param comp Comparator object to handle string comparisons
     * @param a Left pointer
     * @param b Right pointer
     */
    private void quickSortInPlace(String[] S, Comparator comp, int a, int b) {
        if (a >= b) return; // subarray is trivially sorted
        int left = a;
        int right = b-1;
        String pivot = S[b];
        String temp; // temp object used for swapping
        while (left <= right) {
            // scan until reaching value equal or larger than pivot (or right marker)
            while (left <= right && comp.compare(S[left], pivot) < 0) left++;
            // scan until reaching value equal or smaller than pivot (or left marker)
            while (left <= right && comp.compare(S[right], pivot) > 0) right--;
            if (left <= right) { // indices did not strictly cross
            // so swap values and shrink range
            temp = S[left]; S[left] = S[right]; S[right] = temp;
            left++; right--;
            }
        }
        // put pivot into its final place (currently marked by left index)
        temp = S[left]; S[left] = S[b]; S[b] = temp;
        // make recursive calls
        quickSortInPlace(S, comp, a, left - 1);
        quickSortInPlace(S, comp, left + 1, b);
    }

    /**
     * Internal class to handle string comparisons in the same way as the book
     */
    private class Comparator {
        public Integer compare(String element1, String element2) {
            comparisons++;
            return element1.compareTo(element2);
        }
    }
}
