package Project_6;

import java.util.*;

/**
 * @author Evan Eissler
 */

public class MergeSort {

    String[] inputWords;

    Integer comparisons = 0;

    /**
     * Class contructor for Merge Sort
     * @param words The unsorted array of words
     */
    public MergeSort(String[] words) {
        inputWords = words;
    }

    /**
     * Run the sorting algorithm for Merge Sort
     * @return Integer count of comparisons
     */
    public Integer sort() {
        Comparator comparator = new Comparator();
        mergeSort(inputWords, comparator);
        return comparisons;
    }

    /**
     * Merge two halfs back into one array
     * @param S1 The first half of the array
     * @param S2 The second half of the array
     * @param S The final array with the halfs merger together
     * @param comp Comparator object to compare strings
     */
    public void merge(String[] S1, String[] S2, String[] S, Comparator comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0)) {
                S[i+j] = S1[i++]; // copy ith element of S1 and increment i
            } else {
                S[i+j] = S2[j++]; // copy jth element of S2 and increment j
            }
        }
    }

    /**
     * Runs the merge sort algorithm
     * @param S Array being sorted
     * @param comp Comparator object for comparisons
     */
    public void mergeSort(String[] S, Comparator comp) {
        int n = S.length;
        if (n < 2) return; // array is trivially sorted
        // divide
        int mid = n/2;
        String[] S1 = Arrays.copyOfRange(S, 0, mid); // copy of first half
        String[] S2 = Arrays.copyOfRange(S, mid, n); // copy of second half
        // conquer (with recursion)
        mergeSort(S1, comp); // sort copy of first half
        mergeSort(S2, comp); // sort copy of second half
        // merge results
        merge(S1, S2, S, comp); // merge sorted halves back into original
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
