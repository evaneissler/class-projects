package Project_6;

import java.util.*;

/**
 * @author Evan Eissler
 */

public class Project6 {
    public static void main(String args[]) {

        // Takes input from command line as well as standard input
        System.out.println("Please enter you input below:");
        Scanner scanner = new Scanner(System.in);

        // Read first line of input to get number of words that will follow
        Integer wordCount = Integer.valueOf(scanner.nextLine());
        String[] unsortedWords = new String[wordCount];

        // Stores each word into a String array
        for(int i=0;i<wordCount;i++) {
            unsortedWords[i] = scanner.nextLine();
        }

        // Creates a copy of the words array for each sorting algorithm, so that the next algorithm isn't affected by the previous
        String[] list1 = Arrays.copyOf(unsortedWords, unsortedWords.length);
        String[] list2 = Arrays.copyOf(unsortedWords, unsortedWords.length);
        String[] list3 = Arrays.copyOf(unsortedWords, unsortedWords.length);
        String[] list4 = Arrays.copyOf(unsortedWords, unsortedWords.length);
        String[] list5 = Arrays.copyOf(unsortedWords, unsortedWords.length);

        // Creates instances of each sorting algorithm with the corresponding
        SelectionSort selection = new SelectionSort(list1);
        InsertionSort insertion = new InsertionSort(list2);
        HeapSort heap = new HeapSort(list3);
        MergeSort merge = new MergeSort(list4);
        QuickSort quick = new QuickSort(list5);

        // Run selection sort and get time in milliseconds
        long selectionSortStart = System.nanoTime();
        Integer selectionComparisions = selection.sort();
        long selectionSortFinish = System.nanoTime();
        long selectionSortTime = selectionSortFinish - selectionSortStart;

        // Run insertion sort and get time in milliseconds
        long insertionSortStart = System.nanoTime();
        Integer insertionComparisions = insertion.sort();
        long insertionSortFinish = System.nanoTime();
        long insertionSortTime = insertionSortFinish - insertionSortStart;

        // Run heap sort and get time in milliseconds
        long heapSortStart = System.nanoTime();
        Integer heapComparisions = heap.sort();
        long heapSortFinish = System.nanoTime();
        long heapSortTime = heapSortFinish - heapSortStart;

        // Run merge sort and get time in milliseconds
        long mergeSortStart = System.nanoTime();
        Integer mergeComparisions = merge.sort();
        long mergeSortFinish = System.nanoTime();
        long mergeSortTime = mergeSortFinish - mergeSortStart;

        // Run quick sort and get time in milliseconds
        long quickSortStart = System.nanoTime();
        Integer quickComparisions = quick.sort();
        long quickSortFinish = System.nanoTime();
        long quickSortTime = quickSortFinish - quickSortStart;

        // Print output table with the number of comparisons and the time in milliseconds
        System.out.println("");
        System.out.println("Algorithm             |   Comparisons  |  Time (Milliseconds)  ");
        System.out.println("======================+================+=======================");
        System.out.println("Selection Sort        |  " + String.format("%1$12s", selectionComparisions) + "  |  " + String.format("%1$.4f", (double)selectionSortTime/1000000));
        System.out.println("----------------------+----------------+-----------------------");
        System.out.println("Insertion Sort        |  " + String.format("%1$12s", insertionComparisions) + "  |  " + String.format("%1$.4f", (double)insertionSortTime/1000000));
        System.out.println("----------------------+----------------+-----------------------");
        System.out.println("Heap Sort             |  " + String.format("%1$12s", heapComparisions) + "  |  " + String.format("%1$.4f", (double)heapSortTime/1000000));
        System.out.println("----------------------+----------------+-----------------------");
        System.out.println("Merge Sort            |  " + String.format("%1$12s", mergeComparisions) + "  |  " + String.format("%1$.4f", (double)mergeSortTime/1000000));
        System.out.println("----------------------+----------------+-----------------------");
        System.out.println("Quick Sort            |  " + String.format("%1$12s", quickComparisions) + "  |  " + String.format("%1$.4f", (double)quickSortTime/1000000));
        System.out.println("----------------------+----------------+-----------------------");
    }
}
