package Project_4;

import java.util.*;

/**
 * @author Evan Eissler
 */

public class Project4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please paste in your input expression below:");
            String line = scanner.nextLine();
            StringTokenizer stream = new StringTokenizer(line, " ");

            // Initialize array with specific length based on input tokens to ensure efficient space is used
            ArrayBinaryTree tree = new ArrayBinaryTree((int)Math.pow(2, stream.countTokens()));

            // See if the input was a prefix or postfix expression and handle accordingly
            if (stream.nextToken().equals("@")) {
                // Postfix requires the input string to be reversed before input into the build method
                StringTokenizer reverseStream = new StringTokenizer(new StringBuilder(line.substring(1)).reverse().toString(), " ");
                build(reverseStream, tree, 0, true);
                
                // Traverse the created tree in a preorder traversal to print out prefix expression
                System.out.print("Prefix Notation: ");
                tree.preOrder(0);
                System.out.println("");

                System.out.println("Visual Binary Tree:");
                tree.breadthFirstTraversal(0);
                System.out.println("");
            } else {
                build(stream, tree, 0, false);

                // Traverse the created tree in a postorder traversal to print out postfix expression
                System.out.print("Postfix Notation: ");
                tree.postOrder(0);
                System.out.println("");

                System.out.println("Visual Binary Tree:");
                tree.breadthFirstTraversal(0);
                System.out.println("");
            }
        }
    }

    /**
     * Builds array based binary tree from input using ArrayBinaryTree
     * @param stream The StringTokenizer containing the input
     * @param tree ArrayBinaryTree binary tree being created and modified
     * @param index Integer current index at which a new element is being added
     * @param isPostFix Boolean to control which method of building tree is used as prefix and postfix are different
     * @return String with created nodes
     */
    public static String build(StringTokenizer stream, ArrayBinaryTree tree, int index, boolean isPostFix) {
        String token = stream.nextToken();
        // Check for operator or operand, operands are external nodes of the tree so their children are null
        if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
            String temp = tree.newNode(token, index);
            tree.addLeft(null, 2*index+1);
            tree.addRight(null, 2*index+2);
            return temp;
        } else {
            // Operators are internal nodes so adds children recursively until external node is found
            String temp = tree.newNode(token, index);
            // If the expression is post fix, the order of left and right children being added is reversed
            if (isPostFix) {
                tree.addRight(build(stream, tree, 2*index+2, true), index);
                tree.addLeft(build(stream, tree, 2*index+1, true), index);
            } else {
                tree.addLeft(build(stream, tree, 2*index+1, false), index);
                tree.addRight(build(stream, tree, 2*index+2, false), index);
            }
            return temp;
        }
    }
}