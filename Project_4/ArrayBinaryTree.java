package Project_4;

import java.util.*;

/**
 * @author Evan Eissler
 */

public class ArrayBinaryTree {

    private String[] tree;

    /**
     * Constructor to initialize array for storing binary tree with required size
     * @param inputSize The size required for the array to store all the elements of input and their children
     */
    public ArrayBinaryTree(int inputSize) {
        tree = new String[inputSize];
    }

    /**
     * Creates new node and adds to tree array
     * @param item Element being stored in the new node
     * @param rank The position of the new node in the array based binary tree
     * @return The new node
     */
    public String newNode(String item, int rank) {
        tree[rank] = item;
        return item;
    }

    /**
     * Adds new node as left child of rank
     * @param item The element of the node being added
     * @param rank The position of the parent node
     */
    public void addLeft(String item, int rank) {
        tree[2*rank+1] = item;
    }

    /**
     * Adds new node as right child of rank
     * @param item The element of the node being added
     * @param rank The position of the parent node
     */
    public void addRight(String item, int rank) {
        tree[2*rank+2] = item;
    }
    
    /**
     * Returns the indicies of the children of the given parent node
     * @param parent The index of the parent node whose children are being accessed
     * @return An iterable arrayList with the index values of the children
     */
    public Iterable<Integer> children(int parent) {
        ArrayList<Integer> children = new ArrayList<Integer>();
        if (tree[2*parent+1] != null && tree[2*parent+2] != null) {
            children.add(2*parent+1);
            children.add(2*parent+2);
        }
        return children;
    }

    /**
     * Recursively traverses the binary tree using the preorder method
     * Parent nodes are visited before children nodes
     * @param root The index of the root node where the traversal will start
     */
    public void preOrder(int root) {
        System.out.print(tree[root] + " ");
        for (Integer child : children(root)) {
            preOrder(child);
        }
        return;
    }

    /**
     * Recursively traverses the binary tree using the postorder method
     * Parent nodes are visitied after children nodes
     * @param root The index of the root node where the traversal will start
     */
    public void postOrder(int root) {
        for (Integer child : children(root)) {
            postOrder(child);
        }
        System.out.print(tree[root] + " ");
        return;
    }

    /**
     * Uses the breadth-first traversal method to traverse the binary tree and print in proper order
     * @param root The index of the root node where the traversal will start
     */
    public void breadthFirstTraversal(int root) {
        // This algorithm follows the breadth-first tree traversal method
        // Sequence stores output elements and spaces for printing
        ArrayList<String> sequence = new ArrayList<String>();

        // Initialize queue with root node
        Queue queue = new Queue();
        queue.enqueue(root);

        // Rows and columns used for print formatting
        int currentRow = 1;
        int columnCount = 1;
        int i = 0;

        // Calculates the spaces required for printing based on the number of levels in the tree
        int spacesBetween = (int) (Math.log(tree.length) / Math.log(2)) + 1;

        // Add spaces for the first row of the binary tree
        for (int k = 0; k < spacesBetween/2; k++) {
            sequence.add("  ");
        }

        // Follow the breadth-first traversal algorithm by visiting and removing the parent node and then adding it's children to the queue
        while (!queue.isEmpty()) {
            int element = queue.dequeue();
            
            // Store a place holder for null nodes to keep proper spacing
            if (element == -1) {
                sequence.add("");
            } else {
                sequence.add(tree[element]);
            }
            
            // Add new line character when all the elements of a row have been added to sequence
            if (i + 1 == columnCount) {
                columnCount += currentRow * 2;
                currentRow += 1;
                sequence.add("\n");
                spacesBetween = spacesBetween / 2;
                for (int j = 0; j < spacesBetween; j++) {
                    sequence.add(" ");
                }
            } else {
                for (int k = 0; k < spacesBetween; k++) {
                    sequence.add("  ");
                }
            }

            // Add children to queue
            // If a node has no children, add -1 to queue to keep spacing requirements
            if (element != -1) {
                int count = 0;
                // Access children() results with iterator
                for (Integer child : children(element)) {
                    queue.enqueue(child);
                    count++;
                }
                if (count == 0 && !queue.isEmpty()) {
                    queue.enqueue(-1);
                    queue.enqueue(-1);
                }
                i++;
            }
        }

        // Print out sequence generated from breadth-first traversal
        for (int j = 0; j < sequence.size(); j++) {
            System.out.print(sequence.get(j));
        }
    }

    // Class created to allow breadth-first traversal to use queue method
    public class Queue {

        private ArrayList<Integer> queue = new ArrayList<Integer>();

        private int index = 0;

        /**
         * Adds element to end of queue
         * @param element The element being added to queue
         */
        public void enqueue(int element) {
            queue.add(element);
        }

        /**
         * Removes and returns first element of queue
         * Uses index to store current first element of array
         * @return Integer value that is the next in queue
         */
        public Integer dequeue() {
            int first = queue.get(index);
            index = index + 1;
            return first;
        }

        /**
         * Returns boolean value indicating if the queue is empty or not
         * @return boolean indicating whether or not queue is empty
         */
        public boolean isEmpty() {
            return (index == queue.size());
        }
    }
}