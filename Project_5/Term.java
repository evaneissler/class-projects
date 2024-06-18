package Project_5;

import java.util.*;

/**
 * @author Evan Eissler
 * Stores each polynomial term and handles toString() method to output in proper form
 */
public class Term {
    // Store parts of a polynomial term privately
    private String coefficient;
    private String xexponent;
    private String yexponent;
    private String zexponent;

    /**
     * Constructor takes in single polynomial, separates and stores each term
     * @param polynomial
     */
    public Term(String polynomial) {
        StringTokenizer rawTerm = new StringTokenizer(polynomial, ",");
        coefficient = rawTerm.nextToken();
        xexponent = rawTerm.nextToken();
        yexponent = rawTerm.nextToken();
        zexponent = rawTerm.nextToken();
    }

    /**
     * Outputs term in proper form to be displayed
     * Handles negatives, -1, 1, 0 exponent and coefficient cases when displaying to show proper form
     */
    @Override public String toString() {
        return (coefficient.equals("1") ? (xexponent.equals("0") && yexponent.equals("0") && zexponent.equals("0")) ? "1" : "" : 
        coefficient.equals("-1") ? (xexponent.equals("0") && yexponent.equals("0") && zexponent.equals("0")) ? "- 1 " : "- " : 
        coefficient.substring(0,1).equals("-") ? ("- " + coefficient.substring(1)) : 
        coefficient)
        + (xexponent.equals("0") ? "" : xexponent.equals("1") ? "(x)" : ("(x^" + xexponent + ")"))
        + (yexponent.equals("0") ? "" : yexponent.equals("1") ? "(y)" : ("(y^" + yexponent + ")"))
        + (zexponent.equals("0") ? "" : zexponent.equals("1") ? "(z)" : ("(z^" + zexponent + ")"))
        + " ";
    }

    /**
     * @return Boolean value representing if the term is negative, used for displaying negative sign properly
     */
    public boolean isNegative() {
        return coefficient.substring(0,1).equals("-");
    }
}
