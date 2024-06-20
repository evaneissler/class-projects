package Lab01;

import java.awt.event.*;
import java.io.*;

/* Evan Eissler
 * Techniques In Programming
 * September 5, 2023
 * Lab01
*/

public class Lab01Control extends Lab01View implements ActionListener {

    public static void main(String args[]) {
        new Lab01Control();
    }

    public Lab01Control() {
        // Add action listeners here
        createButton.addActionListener(this);
        executeButton.addActionListener(this);
        quitButton.addActionListener(this);
        resetButton.addActionListener(this);
        // First Matrix Buttons
        clearFirstMatrix.addActionListener(this);
        saveFirstMatrix.addActionListener(this);
        openFirstMatrix.addActionListener(this);
        // Second Matrix Buttons
        clearSecondMatrix.addActionListener(this);
        saveSecondMatrix.addActionListener(this);
        openSecondMatrix.addActionListener(this);
        // Result Window
        clearResultMatrix.addActionListener(this);
        saveResultMatrix.addActionListener(this);
        openResultMatrix.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // Get Which Button Was Clicked
        Object clickedButton = e.getSource();
        System.out.print("BUTTON CLICKED");

        // Starting Window Buttons
        if (clickedButton.equals(createButton)) {
            create();
        }
        if (clickedButton.equals(executeButton)) {
            execute();
        }
        if (clickedButton.equals(quitButton)) {
            quit();
        }
        if (clickedButton.equals(resetButton)) {
            reset();
        }

        // Clear Buttons for first, second, and result
        if (clickedButton.equals(clearFirstMatrix)) {
            clear(0);
        }
        if (clickedButton.equals(clearSecondMatrix)) {
            clear(1);
        }
        if (clickedButton.equals(clearResultMatrix)) {
            clear(2);
        }

        // Save Buttons for first, second, and result
        if (clickedButton.equals(saveFirstMatrix)) {
            save(0);
        }
        if (clickedButton.equals(saveSecondMatrix)) {
            save(1);
        }
        if (clickedButton.equals(saveResultMatrix)) {
            save(2);
        }

        // Open Buttons for first, second, and result
        if (clickedButton.equals(openFirstMatrix)) {
            open(0);
        }
        if (clickedButton.equals(openSecondMatrix)) {
            open(1);
        }
        if (clickedButton.equals(openResultMatrix)) {
            open(2);
        }
    } 

    public void create() {
        // Get value of fields
        if (!firstMatrixRowsInput.getText().equals("")) {
            firstRows = Integer.parseInt(firstMatrixRowsInput.getText());
        }
        if (!firstMatrixColsInput.getText().equals("")) {
            firstCols = Integer.parseInt(firstMatrixColsInput.getText());
        }
        if (!secondMatrixRowsInput.getText().equals("")) {
            secondRows = Integer.parseInt(secondMatrixRowsInput.getText());
        }
        if (!secondMatrixColsInput.getText().equals("")) {
            secondCols = Integer.parseInt(secondMatrixColsInput.getText());
        }
        // Get selected operation
        if (!firstCreate) {
            firstMatrixWindow.dispose();
            secondMatrixWindow.dispose();
        }
        // Set secondary windows
        displayFirstMatrixWindow(false, true, false);
        displaySecondMatrixWindow(false, true, false);

        firstCreate = false;
        // Show button clicked
        System.out.println("Create");
    }

    public void execute() {
        // Get array of doubles for each row
        getMatrixValues();
        // Check which operation was selected
        if (selectOperation.getSelectedItem().equals("Transpose")) {
            transposeMatrix();
        }
        if (selectOperation.getSelectedItem().equals("Addition")) {
            addMatrix();
        }
        if (selectOperation.getSelectedItem().equals("Subtraction")) {
            subtractMatrix();
        }
        if (selectOperation.getSelectedItem().equals("Multiplication")) {
            multiplyMatrix();
        }  

        // Show button clicked
        System.out.println("Execute");
    }

    public void quit() {

        // Show button clicked
        System.out.println("Quit");
    }

    public void reset() {
        firstMatrixRowsInput.setText("");
        firstMatrixColsInput.setText("");
        secondMatrixRowsInput.setText("");
        secondMatrixColsInput.setText("");
        System.out.println("Reset");
    }

    public void getMatrixValues() {
        firstMatrix = new Double[firstRows][firstCols];
        secondMatrix = new Double[secondRows][secondCols];
        resultMatrix = new Double[firstRows][firstCols];
        // Use to loop through all input fields
        int currentFirstIndex = 0;
        int currentSecondIndex = 0;
        // Convert all first matrix inputs into a matrix
        for(int i = 0; i < firstRows; i++) {
            for(int j = 0; j < firstCols; j++)
            {
                try {
                    firstMatrix[i][j] = Double.parseDouble(firstInput[i][j].getText());
                } 
                catch(Exception e) {
                    firstMatrix[i][j] = 0.0;
                }
                currentFirstIndex = currentFirstIndex + 1;
            }
        }
        // Convert all second matrix inputs into a matrix
        for(int i = 0; i < secondRows; i++) {
            for(int j = 0; j < secondCols; j++)
            {
                try {
                    secondMatrix[i][j] = Double.parseDouble(secondInput[i][j].getText());
                } 
                catch(Exception e) {
                    secondMatrix[i][j] = 0.0;
                }
                currentSecondIndex = currentSecondIndex + 1;
            }
        }
    }

    public void transposeMatrix() {
        int oldFirstRows = firstRows;
        int oldFirstCols = firstCols;
        int oldSecondRows = secondRows;
        int oldSecondCols = secondCols;

        firstRows = oldFirstCols;
        firstCols = oldFirstRows;
        secondRows = oldSecondCols;
        secondCols = oldSecondRows;

        firstTransposed = new Double[firstRows][firstCols];
        secondTransposed = new Double[secondRows][secondCols];

        for (int i=0;i<firstMatrix.length;i++) {
            for (int j=0;j<firstMatrix[i].length;j++) {
                firstTransposed[j][i] = firstMatrix[i][j];
            }   
        }

        for (int i=0;i<secondMatrix.length;i++) {
            for (int j=0;j<secondMatrix[i].length;j++) {
                secondTransposed[j][i] = secondMatrix[i][j];
            }
        }

        firstMatrixWindow.dispose();
        secondMatrixWindow.dispose();
        displayFirstMatrixWindow(true, true, false);
        displaySecondMatrixWindow(true, true, false);
        // Show option chosen
        System.out.print("Transpose Clicked");
    }

    public void addMatrix() {
        // Check Dimensions
        if (firstRows != secondRows || firstCols != secondCols) {
            firstMatrixRowsInput.setText("Invalid Dimensions");
            firstMatrixColsInput.setText("Invalid Dimensions");
            secondMatrixRowsInput.setText("Invalid Dimensions");
            secondMatrixColsInput.setText("Invalid Dimensions");
        } else {

            resultMatrix = new Double[firstRows][firstCols];

            for (int i=0;i<firstMatrix.length;i++) {
                for (int j=0;j<firstMatrix[i].length;j++) {
                    resultMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
                }
            }

            resultRows = firstRows;
            resultCols = firstCols;
            if (!firstResult) {
                resultWindow.dispose();
            }
            displayResultWindow(true, false, false);
            firstResult = false;
        }
        // Show Button Clicked
        System.out.print("Addition Clicked");
    }

    public void subtractMatrix() {
        // Check Dimensions
        if (firstRows != secondRows || firstCols != secondCols) {
            firstMatrixRowsInput.setText("Invalid Dimensions");
            firstMatrixColsInput.setText("Invalid Dimensions");
            secondMatrixRowsInput.setText("Invalid Dimensions");
            secondMatrixColsInput.setText("Invalid Dimensions");
        } else {

            resultMatrix = new Double[firstRows][firstCols];

            for (int i=0;i<firstMatrix.length;i++) {
                for (int j=0;j<firstMatrix[i].length;j++) {
                    resultMatrix[i][j] = firstMatrix[i][j] - secondMatrix[i][j];
                }
            }

            resultRows = firstRows;
            resultCols = firstCols;
            if (!firstResult) {
                resultWindow.dispose();
            }
            displayResultWindow(true, false, false);
            firstResult = false;
        }
        // Show Button Clicked
        System.out.print("Subtraction Clicked");
    }

    public void multiplyMatrix() {
        // Check Dimensions
        if (firstCols != secondRows) {
            firstMatrixRowsInput.setText("Invalid Dimensions");
            firstMatrixColsInput.setText("Invalid Dimensions");
            secondMatrixRowsInput.setText("Invalid Dimensions");
            secondMatrixColsInput.setText("Invalid Dimensions");
        } else {
            resultMatrix = new Double[firstRows][secondCols];
            System.out.print(firstRows);
            System.out.print(firstRows);
            for (int i = 0; i < firstRows; i++) {
                for (int j = 0; j < secondCols; j++) {
                    resultMatrix[i][j] = 0.0;
                    for (int k = 0; k < firstCols; k++) {
                        resultMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                    }
                }
            }
            resultRows = firstRows;
            resultCols = secondCols;
            if (!firstResult) {
                resultWindow.dispose();
            }
            displayResultWindow(true, false, false);
            firstResult = false;
        }
        // Show Button Clicked
        System.out.print("Multiplication Clicked");
    }

    public void saveFile(String fileName, Double[][] savedMatrix) {
        try { 
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream object = new ObjectOutputStream(file);
            object.writeObject(savedMatrix);
            object.close();
            file.close();
            System.out.println("file " + fileName + " saved as object");
        }
        catch (FileNotFoundException e) {System.out.println("File not found");}
        catch (IOException e) {System.out.println("Error initializing stream");}
    }

    public void readFile(String fileName, Integer window) { 
        try { 
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream object = new ObjectInputStream(file);
            if (window == 0) {
                firstMatrix = (Double[][]) object.readObject();
                if (firstMatrix.length == firstRows && firstMatrix[0].length == firstCols) {
                    firstMatrixWindow.dispose();
                    displayFirstMatrixWindow(false, true, true);
                } else {
                    warning("The dimensions of the matrix you are trying to open does not " +
                    "match current dimensions.","Invalid Dimensions");
                }
            }
            if (window == 1) {
                secondMatrix = (Double[][]) object.readObject();
                if (secondMatrix.length == secondRows && secondMatrix[0].length == secondCols) {
                    secondMatrixWindow.dispose();
                    displaySecondMatrixWindow(false, true, true);
                } else {
                    warning("The dimensions of the matrix you are trying to open does not " +
                    "match current dimensions.","Invalid Dimensions");
                }
            }
            if (window == 2) {
                resultMatrix = (Double[][]) object.readObject();
                if (resultMatrix.length == resultRows && resultMatrix[0].length == resultCols) {
                    resultWindow.dispose();
                    displayResultWindow(true, true, true);
                } else {
                    warning("The dimensions of the matrix you are trying to open does not " +
                    "match current dimensions.","Invalid Dimensions");
                }
            }
            object.close();
            file.close();
            System.out.println("file " + file + " opened as an object");
        }
        catch (ClassNotFoundException e) {warning("Could Not Find Class.","Class Not Found");; }
        catch (FileNotFoundException e) {warning("No file exists with this name.", "File Not Found"); }
        catch (IOException e) {warning("Error initializing stream", "Error initializin stream."); }
        }

    // Secondary window actions
    public void clear(int window) {
        // Clears all current fields
        if (window == 0) {
            firstMatrixWindow.dispose();
            displayFirstMatrixWindow(false, true, false);
        }
        if (window == 1) {
            secondMatrixWindow.dispose();
            displaySecondMatrixWindow(false, true, false);
        }
        if (window == 2) {
            resultWindow.dispose();
            displayResultWindow(true, true, false);
        }
        System.out.print("Clear First Matrix");
    }

    public void save(int window) {
        if (window==0) {
            // Get first matrix layout and nums
            getMatrixValues();
            saveFile(fileInputFirstMatrix.getText(), firstMatrix);
        }
        if (window==1) {
            // Get second matrix layout and nums
            getMatrixValues();
            saveFile(fileInputSecondMatrix.getText(), secondMatrix);
        }
        if (window==2) {
            // Get result matrix layout and nums
            getMatrixValues();
            saveFile(fileInputResultMatrix.getText(), resultMatrix);
        }
    }

    public void open(int window) {
        if (window==0) {
            // Open file, arrange and set first matrix
            readFile(fileInputFirstMatrix.getText(), 0);
            System.out.print("OPEN FIRST");
        }
        if (window==1) {
            // Open file, arrange and set second matrix
            readFile(fileInputSecondMatrix.getText(), 1);
            System.out.print("OPEN SECOND");
        }
        if (window==2) {
            // Open file, arrange and set result matrix
            readFile(fileInputResultMatrix.getText(), 2);
            System.out.print("OPEN RESULT");
        }
    }
}
