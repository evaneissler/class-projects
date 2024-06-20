/*************************************************************/
/*   Program Name: Lab6ExperimentView                        */
/*   Courtesy of Dr. Antonio Aguilar-Sanchez                 */
/*   10403-15 Spring 2023                                    */
/*   Student name: Evan Eissler                              */
/*                                                           */
/*************************************************************/

import java.awt.*;
import javax.swing.*;

public class Lab6ExperimentView extends JFrame 
{
    protected JTextField numberEntered = new JTextField(3);
    protected JButton dataButton = new JButton("submit");
    protected JLabel dataLabel = new JLabel("Num of dataPoints: ");
    protected JTextField[] displayFields; // to hold numbers user will enter
    protected JButton avgButton = new JButton("avg");
    protected JButton maxButton = new JButton("max");
    protected JButton minButton = new JButton("min");
    protected JComboBox<String> cbOptions = new JComboBox<String>();
    protected JButton resetButton = new JButton("reset");
    protected JTextField resultField = new JTextField(15);
    protected int numData = 0;
    protected int prevNumData = 0;
    protected final boolean verbose = true;

    protected JPanel dataPanel = new JPanel();
    protected JPanel numberPanel = new JPanel();
    protected JPanel resultPanel = new JPanel(new GridLayout(1, 5));

    public Lab6ExperimentView() 
    { // constructor
        if (verbose) System.out.println("In constructor Lab6ExperimentView");
        numberEntered.setText("0");
        setFont(new Font("TimesRoman", Font.BOLD, 14));

        setBounds(200, 400, 700, 200);
        setVisible(true);
        setLayout(new FlowLayout());
        addOptions();
        dataPanel.add(dataLabel);
        dataPanel.add(numberEntered);
        dataPanel.add(dataButton);
        numberEntered.requestFocus();
        dataPanel.setBackground(Color.GREEN);
        setBounds(200, 400, 700, 400);
        setLayout(new BorderLayout());
        add(dataPanel, BorderLayout.NORTH);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addOptions() {
        if (verbose) System.out.println("In view:addOptions");
        cbOptions.addItem("Select Operation");
        cbOptions.addItem("std sample");
        cbOptions.addItem("std population");
        cbOptions.addItem("Sort with doubles");
        cbOptions.addItem("Sort with Strings");
        cbOptions.addItem("Search unsorted");
        cbOptions.addItem("Search sorted");
    }

    protected void displayNumberPanel(int numData) {
        if (verbose)
            System.out.println("In view:displayNumberPanel with " + numData);
        int dataR = (int) numData / 5 + 1;
        displayFields = new JTextField[numData];
        numberPanel.setLayout(new GridLayout(dataR, 5, 2, 2));
        numberPanel.setBackground(Color.MAGENTA);
        add(numberPanel, BorderLayout.CENTER);
        for (int i = 0; i < numData - prevNumData; i++) {
            displayFields[i] = new JTextField(10);
            numberPanel.add(displayFields[i]);
        }
    }
    
    protected void displayResultPanel() {
        if (verbose)
            System.out.println("In view:displayResultPanel");
        resultPanel.setBackground(Color.BLUE);
        resultPanel.add(avgButton);
        resultPanel.add(maxButton);
        resultPanel.add(minButton);
        resultPanel.add(cbOptions);
        resultPanel.add(resetButton);
        resultPanel.add(resultField);
        add(resultPanel, BorderLayout.SOUTH);
       
    }

} // end Class
