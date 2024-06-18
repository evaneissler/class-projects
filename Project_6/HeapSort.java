package Project_6;

/**
 * @author Evan Eissler
 */

public class HeapSort {

    // Store array of words
    String[] inputWords;

    // Store heap in array form
    String[] heap;

    // Store rank and size for the heap to use
    int size;
    int rank = 0;

    // Store comparisons
    int comparisons = 0;
    int index = 0;

    /**
     * Class contructor for Heap Sort
     * Creates new heap with size to accomodate all the words
     * @param words The unsorted array of words
     */
    public HeapSort(String[] words) {
        inputWords = words;
        size = words.length;
        heap = new String[size]; // Initialize heap array with the same size as inputWords
    }

    /**
     * Run the sorting algorithm for Heap Sort
     * @return Integer count of comparisons
     */
    public Integer sort() {
        for (String word : inputWords) {
            insert(word);
        }
        String[] sortedArray = new String[size];
        while (rank > 0 && index < size) {
            sortedArray[index++] = removeMin();
        }
        return comparisons;
    }

    /**
     * Inserts word into heap in proper way
     * @param key Word being inserted
     */ 
    private void insert(String key) {
        heap[rank] = key;
        upheap(rank);
        rank++;
    }

    /**
     * Removes minimum word from heap in proper way
     * @return The string (word) being removed
     */
    private String removeMin() {
        String min = heap[0];
        swap(0, rank - 1);
        rank--;
        downheap(0);
        return min;
    }
    
    /**
     * Restore heap-order after insertion
     * @param index The current index
     */
    private void upheap(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0) {
            comparisons++;
            if (heap[index].compareTo(heap[parentIndex]) < 0) {
                swap(index, parentIndex);
            }
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    /**
     * Used in upheap and downheap methods for restoring heap-order
     * Swaps first and last heap elements
     * @param index1 First element being swapped
     * @param index2 Second element being swapped
     */
    private void swap(int index1, int index2) {
        String temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /**
     * Restore heap-order after removing an element
     * @param index The current index
     */
    private void downheap(int index) {
        while (index < rank) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
    
            if (leftChildIndex >= rank) {
                break;
            }
    
            int smallerChildIndex = leftChildIndex;
            if (rightChildIndex < rank) {
                if (heap[leftChildIndex].compareTo(heap[rightChildIndex]) > 0) {
                    comparisons++;
                    smallerChildIndex = rightChildIndex;
                }
            }
    
            if (heap[index].compareTo(heap[smallerChildIndex]) > 0) {
                comparisons++;
                swap(index, smallerChildIndex);
                index = smallerChildIndex;
            } else {
                break;
            }
        }
    }    
}

