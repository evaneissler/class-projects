
/*************************************************************/
/*   Program Name:     Lab6ExperimentControl                 */
/*   Courtesy of Dr. Antonio Aguilar-Sanchez                 */
/*   10403-15 Spring 2023                                    */
/*   Student name: Evan Eissler                              */
/*************************************************************/

import java.awt.event.*;
import javax.swing.*;

public class Lab6ExperimentControl extends Lab6ExperimentView implements ActionListener, ItemListener {
    public int enteredNum;
    final public int maxEnter = 30;
    private double[] data;
    private String[] dataString;
    private double sum = 0, average = 0;
    protected final boolean verbose = true;

    public static void main(String args[]) 
    { // Construct the frame
        new Lab6ExperimentControl();
    }

    public Lab6ExperimentControl() 
    { // change init method to a constructor
        if (verbose) System.out.println("In control:constructor");
        avgButton.addActionListener(this); // Register the avg button
        maxButton.addActionListener(this);
        minButton.addActionListener(this); // Register the max button
        cbOptions.addItemListener(this); // Register the ComboBox
        resetButton.addActionListener(this); // Register the button
        dataButton.addActionListener(this); // register
    }

    public void actionPerformed(ActionEvent e) {
        if (verbose) System.out.println("In control:actionPerformed");
        String whichWidget = e.getActionCommand();
        if (verbose)
            System.out.println("calling action perform " + whichWidget);

        if (whichWidget.equals("submit"))
            if (validateInt(numberEntered.getText()) && Integer.parseInt(numberEntered.getText()) <= maxEnter) {
                enteredNum = Integer.parseInt(numberEntered.getText());
                procReset();
                addNumberPanel();
            }
            else {
                numberEntered.setText("submit integer > 0 and <= 30");
            }
        if (whichWidget.equals("reset"))
            procReset();
        if (whichWidget.equals("avg"))
            procAvg();
        if (whichWidget.equals("max"))
            procMax();
        if (whichWidget.equals("min"))
            procMin();

        validate();
        repaint();
    }

    public void itemStateChanged(ItemEvent e) {
        if (verbose) System.out.println("in control:itemStateChanged");
        JComboBox<String> object = (JComboBox<String>) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED)
            switch (object.getSelectedIndex()) {
            case 0:
                System.out.println("Select Option");
                break;
            case 1:
                procStd(true);
                break;
            case 2:
                procStd(false);
                break;
            case 3:
                procSort();
                break;
            case 4:
                procSortString();
                break;
            case 5:
                procSearch(resultField.getText());
                break;
            case 6:
                procSearchSorted(resultField.getText());
                break;
            }
    }

    private void procReset() {
        if (verbose)
            System.out.println("in control:procReset ");

        remove(numberPanel);
        remove(resultPanel);
        numberPanel = new JPanel();
        resultField.setText("");
        numberEntered.setText("");
    }

    protected void addNumberPanel() {
        if (verbose)
            System.out.println("In view:addNumberPanel");
        validateInt(numberEntered.getText());
        numData = enteredNum;
        displayNumberPanel(numData);
        displayResultPanel();
    }

    public void procAvg() {
        if (verbose)
            System.out.println("in control:procAvg ");
        parseAndPopulateData();
        sum = 0;
        int count = data.length;
        for (int i = 0; i < count; i++) {
            sum = sum + data[i];
        }
        average = sum / count;
        // display to 3 decimal places
        resultField.setText("Avg=" + String.format("%.3f", average));
    }

    public void procMax() {
        if (verbose)
            System.out.println("in control:procMax ");
        parseAndPopulateData();
        sum = 0;
        // we are sure we have at least length of 1 number
        double maxValue = data[0];
        int maxIndex = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > maxValue && i != 0) {
                maxValue = data[i];
                maxIndex = i;
            }
        }
        resultField.setText("Max=" + String.format("%.3f", maxValue));
        System.out.println("MaxIndex i = " + maxIndex);
    }

    public void procMin() {
        if (verbose)
            System.out.println("in control:procMin ");
        parseAndPopulateData();
        sum = 0;
        // we are sure we have at least length of 1 number
        double minValue = data[0];
        int minIndex = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] < minValue && i != 0) {
                minValue = data[i];
                minIndex = i;
            }
        }
        resultField.setText("Max=" + String.format("%.3f", minValue));
        System.out.println("MaxIndex i = " + minIndex);
    }

    public void procStd(boolean sample) {
        // calculation: 
        // square root of the average of the squared deviations subtracted from their average value
        // standard deviation sample (n-1)  population (n)
        if (verbose)
            System.out.println("in control:procStd boolean " + sample);
        parseAndPopulateData();
        sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum = sum + data[i];
        }
        average = sum / data.length;
        // now we do std calc 
        sum = 0;
        for (int i = 0; i < data.length; i++)
            sum = (data[i] - average) * (data[i] - average);
        if (sample)
            sum = Math.sqrt(sum / (numData - 1));
        else
            sum = Math.sqrt(sum / numData);
        // display to 3 decimal places
        resultField.setText("Std=" + String.format("%.3f", sum));
    }


    public void procSort() {
        // bubble sort on data values
        if (verbose)
            System.out.println("in control:procSort ");
        parseAndPopulateData();
        double temp;
        // bubble sort
        for (int i = 0; i < data.length; i++)
            for (int j = 1; j < data.length - i; j++) { // comparing data values with >
                if (data[j - 1] > data[j]) {
                    temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        // output result to display
        for (int i = 0; i < data.length; i++)
            displayFields[i].setText("" + data[i]);
    }

    public void procSortString() {
        if (verbose)
            System.out.println("In control:procSortString ");

        dataString = new String[displayFields.length];
        // using the string value of the TextField populate dataString
        for (int i = 0; i < displayFields.length; i++)
            dataString[i] = displayFields[i].getText();

        // bubble sort on String values
        String temp;
        for (int i = 0; i < dataString.length; i++)
            for (int j = 1; j < dataString.length - i; j++)
                // comparing using String you need to use
                if (dataString[j - 1].compareTo(dataString[j]) > 0) {
                    temp = dataString[j - 1];
                    dataString[j - 1] = dataString[j];
                    dataString[j] = temp;
                }
        // output result to display
        for (int i = 0; i < dataString.length; i++)
            displayFields[i].setText(dataString[i]);
    }

    public void procSearch(String searchString) {
        // search using while
        if (verbose)
            System.out.println("in control:procSearch for " + searchString);
        int numData = displayFields.length;
        // String temp;
        dataString = new String[numData];
        // using the string value of the TextField populate dataString
        int i = 0;
        while (i < numData) {
            dataString[i] = displayFields[i].getText();
            i++;
        }
        // search for searchString in dataString
        i = 0;
        boolean flag = false;
        while (i < numData)
            if (dataString[i].equals(searchString)) {
                flag = true;
                break;
            } else
                i++;
        if (verbose)
            System.out.println("@ Search " + i + " FLAG " + flag);
        if (flag)
            resultField.setText("Search " + searchString + " found @loc " + i);
        else
            resultField.setText("Search " + searchString + " NOT found ");
    }

    public void procSearchSorted(String searchString) {
        if (verbose)
            System.out.println("in control:procSearchSorted for " + searchString);

        int numData = displayFields.length;
        dataString = new String[displayFields.length];
        // using the string value of the TextField populate dataString
        int i = 0;
        while (i < numData) {
            dataString[i] = displayFields[i].getText();
            i++;
        }
        // search for searchString assuming dataString is sorted
        i = 0;
        boolean flag = false;  // indicate found searchString
        while (i < numData)
            if (dataString[i].compareTo(searchString) >= 0) {
                System.out.println("@ SearchSorted  " + dataString[i]);
                if (dataString[i].compareTo(searchString) == 0)
                    flag = true;
                break;
            } // if value is larger break with flag false
            else
                i++;
        if (flag)
            resultField.setText("SearchSorted " + searchString + " found @loc " + i);
        else
            resultField.setText("SearchSorted " + searchString + " NOT found ");
    }

    // helper methods
    private boolean validateInt(String s) {
        try {
            int v = Integer.parseInt(s);
            System.out.println("number is " + v);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("please type a number");
            return false;
        }
    }
    
    // not used
    public boolean validateInteger(JTextField datum) {
        try {
            int d = Integer.parseInt(datum.getText());
            if (verbose)
                System.out.println("validated Integer " + d);
            return true;
        } catch (NumberFormatException e) {
            if (verbose)
                System.out.println("invalid Integer " + datum.getText() + " please retype ");
            datum.setText("invalid retry");
            return false;
        }
    }
    public boolean validateDouble(JTextField datum) {
        try {
            double d = Double.parseDouble(datum.getText());
            if (verbose)
                System.out.println("validated Double " + d);
            return true;
        } catch (NumberFormatException e) {
            if (verbose)
                System.out.println("invalid double " + datum.getText() + " please retype ");
            datum.setText("invalid retry");
            return false;
        }
    }
    
    public boolean parseAndPopulateData() {
        // validates displayFields values
        if (verbose)
            System.out.println("in Control:parseAndValidateData ");
        int numData = displayFields.length;
        data = new double[numData];
        // oldData = new double[data];
        boolean result = true;
        for (int i = 0; i < numData; i++) {
            if (!validateDouble(displayFields[i])) {
                result = false;
                break;
            } else {
                data[i] = Double.parseDouble(displayFields[i].getText());
            }
        }
        // if we get here, all the numbers checked out
        return result;
    }

} // end Class
