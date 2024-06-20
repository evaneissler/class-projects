package Lab03;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;

public class Lab03Model {

    Hashtable<String, Double> variableHashtable = new Hashtable<String, Double>(100);
    ArrayList<Double> printValues = new ArrayList<Double>(100);

    /**
     * Runs the code in the TextArea, calculates and displays its output
     * @param inputCode is the string of the code input into the TextArea
     * @param startFromLine is the current line that the reading is starting from, used for goto statements
     * @return The resulting output as a string
     */
    public String runCode(String inputCode, Double startFromLine) {

        // Defin ArrayLists to hold each line and the keys for each line
        String result = "";
        Hashtable<Double, String> codeHashtable = new Hashtable<Double, String>(100);
        ArrayList<Double> keys = new ArrayList<Double>(100);
        StringTokenizer codeTokenizer = new StringTokenizer(inputCode,"\n");

        // Get code into hashtable and keys into arraylist
        while (codeTokenizer.hasMoreTokens()) { 
            StringTokenizer lineTokenizer = new StringTokenizer(codeTokenizer.nextToken()," ");
            try {
                Double key = Double.valueOf(lineTokenizer.nextToken());
                String codeString = "";
                while (lineTokenizer.hasMoreTokens()) {
                    codeString = codeString + lineTokenizer.nextToken() + " ";
                }
                codeHashtable.put(key, codeString);
                keys.add(key);
            } catch (Exception e) {
                System.out.println("Beginning or End of Code Detected");
            }
        } 
        
        // Add opening line to result 
        result = "Running from line " + keys.get(0) + "\n" + "______________ \n\n";

        // Find lines that are commented out and remove them
        ArrayList<Double> newKeys = new ArrayList<Double>(100);
        for(int i=0; i<keys.size();i++) {
            if (codeHashtable.get(keys.get(i)).substring(0,2).equals("//")) {
                codeHashtable.remove(keys.get(i));
            } else {
                newKeys.add(keys.get(i));
            }
        }

        // Iterate through each key and do proper operation
        for(int i=0; i<newKeys.size();i++) {
            if (newKeys.get(i) >= startFromLine) {
                // Get all tokens of each line in ArrayList form
                StringTokenizer thisLineTokens = new StringTokenizer(codeHashtable.get(newKeys.get(i)), " =+-*()/[]{}");
                ArrayList<String> tokens = new ArrayList<String>(100);
                while (thisLineTokens.hasMoreTokens()) {
                    tokens.add(thisLineTokens.nextToken().trim());
                }

                // Define ArrayLists to hold values and operations in parentheses
                ArrayList<String> operations = new ArrayList<String>(100);
                ArrayList<String> unchangedOperations = new ArrayList<String>(100);
                ArrayList<Integer> parentheses = new ArrayList<Integer>(100);

                // Add all operations to operations ArrayList
                for(int j=0;j<codeHashtable.get(newKeys.get(i)).length();j++) {
                        String character = codeHashtable.get(newKeys.get(i)).substring(j, j+1);
                        if (character.equals("=") || character.equals("+") || 
                            character.equals("-") || character.equals("*") || 
                            character.equals("/") || character.equals("(") ||
                            character.equals(")")) {
                                operations.add(character);
                                unchangedOperations.add(character);
                        }
                }

                // Find all parentheses and remove from math operations Arraylist, add to parentheses ArrayList
                for (int j=0;j<operations.size();j++) {
                    // Adds the index of token that is inside and outside the parentheses
                    if (operations.get(j).equals("(")) {
                        parentheses.add(j-1);
                        operations.remove(j);
                        unchangedOperations.remove(j);
                    } else if (operations.get(j).equals(")")) {
                        parentheses.add(j-1);
                        operations.remove(j);
                        unchangedOperations.remove(j);
                    }
                }

                // If parentheses don't have opening or closing, print invalid parentheses use
                if (parentheses.size() % 2 != 0) {
                    return "Invalid use of parentheses";
                }

                // Go through each line and do corresponding function
                if (!(tokens.get(0).equals("//"))) {
                    // If end is reached, return the result and print to console
                    if (tokens.get(0).equals("end")) {
                        for (int j=0;j<printValues.size();j++) {
                            result = result + String.valueOf(printValues.get(j)) + "\n";
                        }
                        result = result + "\n" + "______________ \n\n" + "Program terminated successfully at line " + newKeys.get(i) + "\n";
                        break;
                    } 
                    // If print is written, add that value to printValues to be printed when end is reached
                    else if (tokens.get(0).equals("print")){
                        printValues.add(variableHashtable.get(tokens.get(1)));
                    } 
                    // If the line conatins an if statement, evaluate the if statement and do goto
                    else if (tokens.get(0).equals("if")){
                        Double number1;
                        Double number2;
                        try {
                            number1 = Double.parseDouble(tokens.get(1));
                        } catch (Exception e) {
                            number1 = variableHashtable.get(tokens.get(1));
                        }
                        try {
                            number2 = Double.parseDouble(tokens.get(2));
                        } catch (Exception e) {
                            number2 = variableHashtable.get(tokens.get(2));
                        }
                        if (Double.compare(number1, number2) == 0) {
                            return runCode(inputCode, Double.parseDouble(tokens.get(4)));
                        }
                    }
                    // If goto is in the line, recursively call runCode starting from that line
                    else if (tokens.get(0).equals("goto")){
                        try {
                            return runCode(inputCode, Double.parseDouble(tokens.get(1)));
                        } catch (Exception e) {
                            return "Error encountered, loop evacuated";
                        }
                    } 
                    // If the line doesn't contain any of the previous keywords, then it is an expression line
                    else {
                        // Create ArrayList to hole values for expression in the line
                        ArrayList<Double> values = new ArrayList<Double>(100);
                        ArrayList<Double> unchangedValues = new ArrayList<Double>(100);
                        ArrayList<Double> orderedCalculations = new ArrayList<Double>(100);

                        // Get all numbers to doubles or get their variable values and store in values Arraylist
                        for (int k=tokens.size()-1;k>0;k--) {
                            if (k-1 > 0 && k-1 < operations.size()) {
                                if (operations.get(k-1).equals("=")) {
                                    break;
                                }
                            }
                            if (!(tokens.get(k).equals("/"))) {
                                try {
                                    Double num = Double.parseDouble(tokens.get(k));
                                    values.add(0, num);
                                    unchangedValues.add(0, num);
                                    orderedCalculations.add(0, num);
                                } catch (Exception e) {
                                    try {
                                        Double num = variableHashtable.get(tokens.get(k));
                                        values.add(0, num);
                                        unchangedValues.add(0, num);
                                        orderedCalculations.add(0, num);
                                    } catch (Exception er) {
                                        return "Variable not initialized error";
                                    }
                                }
                            }
                        }

                        Double total = 0.1;

                        // Go through parentheses and do operations, store resulting value for use in outside expression
                        for (int m=0;m<=parentheses.size() - 2;m+=2) {
                            ArrayList<Double> subValues = new ArrayList<Double>(100);
                            ArrayList<String> subOperations = new ArrayList<String>(100);

                            // Get and store first and second double value and operation 
                            Double subValue1 = unchangedValues.get(parentheses.get(m));
                            Double subValue2 = unchangedValues.get(parentheses.get(m+1));
                            String operation = unchangedOperations.get(parentheses.get(m+1));
                            
                            subValues.add(subValue1);
                            subValues.add(subValue2);
                            subOperations.add(operation);

                            values.remove(subValue1);
                            values.remove(subValue2);
                            operations.remove(operation);

                            Double resultTotal = 0.1; 

                            // Do calculations inside parentheses and store resulting value to line values for final calculations
                            for (int h=0;h<subValues.size();h++) {
                                if (h == 0) {
                                    resultTotal = subValues.get(h);
                                } else if (h == 1) {
                                    if(subOperations.get(0).equals("+")) {
                                        Double resultNum = resultTotal + subValues.get(h);
                                        values.add(m, resultNum);
                                    }
                                    else if(subOperations.get(0).equals("-")) {
                                        Double resultNum = resultTotal - subValues.get(h);
                                        values.add(m, resultNum);
                                    }
                                    else if(subOperations.get(0).equals("*")) {
                                        Double resultNum = resultTotal * subValues.get(h);
                                        values.add(m, resultNum);
                                    }
                                    else if(subOperations.get(0).equals("/")) {
                                        Double resultNum = (resultTotal / subValues.get(h));
                                        values.add(m, resultNum);
                                    }
                                }
                            }
                        }

                        // Create ArrayLists for storing the ordered operations and values based on PEMDAS for proper calculation
                        ArrayList<Double> orderedValues = new ArrayList<Double>(100);
                        ArrayList<String> orderedOperations = new ArrayList<String>(100);

                        // Go through operations and arrange/store operations and values using PEMDAS
                        for (int x=0;x<operations.size();x++) {
                            if (x == 0) {
                                orderedOperations.add(operations.get(x));
                            } else {
                                if (operations.get(x).equals("*") || operations.get(x).equals("/")) {
                                    orderedOperations.add(1, operations.get(x));
                                    orderedValues.add(0, values.get(x));
                                    orderedValues.add(0, values.get(x-1));
                                } else {
                                    orderedOperations.add(operations.get(x));
                                }
                            }
                        }

                        for (int y=0;y<values.size();y++) {
                            if (!(orderedValues.contains(values.get(y)))) {
                                orderedValues.add(values.get(y));
                            }
                        }

                        // Go through final ordered list of values and operations and perform calculations
                        for (int h=0;h<values.size();h++) {
                            if (total == 0.1) {
                                total = values.get(h);
                            }
                            if (h >= 0 && h < operations.size() - 1) {
                                if(operations.get(h+1).equals("+")) {
                                    Double resultNum = total + values.get(h+1);
                                    variableHashtable.put(tokens.get(0), resultNum);
                                    total = resultNum;
                                }
                                else if(operations.get(h+1).equals("-")) {
                                    Double resultNum = total - values.get(h+1);
                                    variableHashtable.put(tokens.get(0), resultNum);
                                    total = resultNum;
                                }
                                else if(operations.get(h+1).equals("*")) {
                                    Double resultNum = total * values.get(h+1);
                                    variableHashtable.put(tokens.get(0), resultNum);
                                    total = resultNum;
                                }
                                else if(operations.get(h+1).equals("/")) {
                                    Double resultNum = (total / values.get(h+1));
                                    variableHashtable.put(tokens.get(0), resultNum);
                                    total = resultNum;
                                }
                            } else if (operations.size() == 1) {
                                variableHashtable.put(tokens.get(0), total);
                            }
                        }
                    }
                }
            }
        } 
        return result;
    }

    /**
     * Allows user to select and read a file
     * @return A string containing the contents of the file
     */
    public String selectAndReadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select File");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            if (selectedFile != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    return content.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error reading the file.";
                }
            }
        }

        return "No file selected.";
    }

    /**
     * Allows user to save input code into a file
     * @param code Code in the TextArea
     */
    public void saveFile(String code) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        
        // Set the filter to restrict file types (e.g., .txt files)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write(code);
                writer.close();

                System.out.println("File saved successfully to: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error: Failed to save the file.");
            }
        } else if (userSelection == JFileChooser.CANCEL_OPTION) {
            System.out.println("File save operation was canceled by the user.");
        }
    }
}
