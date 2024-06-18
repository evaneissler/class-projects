package Project_5;

import java.util.*;

/**
 * @author Evan Eissler
 * Stores terms for each polynomial and handles toString() method for a polynomial
 */
public class Polynomial {
    // Stores name of polynomial and DLList instance of terms
    private String name;
    private DLList<Term> terms;

    /**
     * Constructor to create Polynomial being stored in PolyList and create Term instances for each term in the Polynomial
     * @param inputName The string name given to the polynomial at time of input
     * @param input The input stream sequence current delimited by spaces that is used to get each team of the polynomial
     */
    public Polynomial(String inputName, StringTokenizer input) {
        name = inputName;

        // Creates a new linked list instance to store each term
        terms = new DLList<Term>();

        // Creates a new term instance for each term in the input polynomial
        while (input.hasMoreTokens()) {
            terms.insertAtCurrent(new Term(input.nextToken()));
        }
    }

    /**
     * Overrides the toString() method to display the polynomial in visual form
     */
    @Override public String toString() {
        String outputString = name + " = ";
        terms.last();
        // Iterates through terms in proper order and adds them to the output sequence
        while(!terms.atFirst()) {
            outputString = outputString + terms.dataRead().toString();
            terms.previous();

            outputString = outputString + (terms.dataRead().isNegative() ? "" : "+ ");
        }
        outputString = outputString + terms.dataRead().toString();
        return outputString;
    }
    
    /**
     * @return The name of this polynomial instance
     */
    public String name() {
        return name;
    }
}
