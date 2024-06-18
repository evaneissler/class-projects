package Project_5;

import java.util.*;

/**
 * @author Evan Eissler
 * Handles INSERT, DELETE, SEARCH, and QUIT methods and stores list of polynomials
 */
public class PolyList {
    // Privately stores doubly linked list of polynomials
    private DLList<Polynomial> polyList; 

    /**
     * Contructor initializes a new Doubly Linked List instance with type Polynomial
     */
    public PolyList() {
        polyList = new DLList<Polynomial>();
    }

    /**
     * Handles INSERT functionality
     * Display error if polynomial is already in list
     * @param input The input stream delimited by spaces into individual polynomials
     */
    public void insert(StringTokenizer input) {
        String name = input.nextToken();
        Boolean nameExists = false;

        // Checks to make sure list is not empty before looking through all the current polynomials
        // Gets and compares name of each current polynomial to the one being added and sees if they match
        if (!polyList.isEmpty()) {
            polyList.first();
            while (!polyList.atLast()) {
                if (polyList.dataRead().name().equals(name)) {
                    nameExists = true;
                }
                polyList.next();
            }
            if (polyList.dataRead().name().equals(name)) {
                nameExists = true;
            }
        }

        // If the name of the input polynomial is already in the list, the error message is displayed, otherwise the polynomial is added
        if (nameExists == true) {
            System.out.println("POLYNOMIAL " + name + " ALREADY INSERTED");
        } else {
            Polynomial newPolynomial = new Polynomial(name, input);
            polyList.insertAtCurrent(newPolynomial);
            System.out.println(newPolynomial.toString());
        }
    }

    /**
     * Handles DELETE functionality
     * Display error if polynomial does not exist
     * @param input The input stream delimited by spaces into individual polynomials
     */
    public void delete(StringTokenizer input) {
        String searchName = input.nextToken();
        Boolean deleted = false;

        // Checks to make sure list is not empty before looking through all the current polynomials
        // Gets and compares name of each current polynomial to the one being deleted and sees if they match
        // If the name input matches the name of a current polynomial, that polynomial is deleted
        if (!polyList.isEmpty()) {
            polyList.first();
            while (!polyList.atLast()) {
                if (polyList.dataRead().name().equals(searchName)) {
                    deleted = true;
                    polyList.deleteAtCurrent();
                }
                polyList.next();
            }
            if (polyList.dataRead().name().equals(searchName)) {
                deleted = true;
                polyList.deleteAtCurrent();
            }
        }

        // If the deletion succeeds, the success message is displayed otherwise the error message is displayed
        if (deleted) {
            System.out.println("POLYNOMIAL " + searchName + " SUCCESSFULLY DELETED");
        } else {
            System.out.println("POLYNOMIAL " + searchName + " DOES NOT EXIST");
        }
    }

    /**
     * Handles SEARCH functionality
     * Display error if polynomial does not exist
     * @param input The input stream delimited by spaces into individual polynomials
     */
    public void search(StringTokenizer input) {
        String searchName = input.nextToken();
        Boolean searchFound = false;

        // Checks to make sure list is not empty before looking through all the current polynomials
        // Gets and compares name of each current polynomial to the one being searched and sees if they match
        // If the name input matches the name of a current polynomial, that polynomial is printed
        if (!polyList.isEmpty()) {
            polyList.first();
            while (!polyList.atLast()) {
                if (polyList.dataRead().name().equals(searchName)) {
                    System.out.println(polyList.dataRead().toString());
                    searchFound = true;
                }
                polyList.next();
            }
            if (polyList.dataRead().name().equals(searchName)) {
                System.out.println(polyList.dataRead().toString());
                searchFound = true;
            }
        }

        // If the polynomial is not found in the list, the error message is displayed
        if (searchFound == false) {
            System.out.println("POLYNOMIAL " + searchName + " DOES NOT EXIST");
        }
    }

    /**
     * Handles QUIT functionality
     * Removes all polynomial instances from polyList
     */
    public void quit() {
        // Removes the last node until the list is empty
        while (!polyList.isEmpty()) {
            polyList.deleteLast();
        }
    }
}
