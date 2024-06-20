package Lab01;

/* Evan Eissler
 * Techniques In Programming
 * September 5, 2023
 * Lab01
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class Lab01View extends JFrame {

    JFrame startingWindow, firstMatrixWindow, secondMatrixWindow, resultWindow;
    JLabel firstMatrixLabel, secondMatrixLabel;
    JTextField firstMatrixRowsInput, firstMatrixColsInput, secondMatrixRowsInput, secondMatrixColsInput,
    fileInputFirstMatrix, fileInputSecondMatrix, fileInputResultMatrix;
    JTextField[][] firstInput, secondInput, resultFields;
    JButton createButton, executeButton, quitButton, resetButton, clearFirstMatrix, saveFirstMatrix, 
    openFirstMatrix, clearSecondMatrix, saveSecondMatrix, 
    openSecondMatrix, openResultMatrix, clearResultMatrix, saveResultMatrix;
    JComboBox<String> selectOperation;
    JPanel firstMatrixPanel, secondMatrixPanel, firstButtonPanel, secondButtonPanel, resultMatrixPanel, resultButtonPanel;
    Integer firstRows = 1, firstCols = 1, secondRows = 1, secondCols = 1, resultRows = 1, resultCols = 1;
    Double resultMatrix[][], firstMatrix[][], secondMatrix[][], firstTransposed[][], secondTransposed[][];
    Boolean firstCreate = true, firstResult = true;

    public Lab01View() {
        // Call display functions here
        displayMainWindow();
    }

    public void initializeComponents() {
        // First Matrix Window Buttons
        clearFirstMatrix = new JButton("Clear");
        saveFirstMatrix = new JButton("Save");
        openFirstMatrix = new JButton("Open");
        fileInputFirstMatrix = new JTextField(3);
        // Second Matrix Window Buttons
        clearSecondMatrix = new JButton("Clear");
        saveSecondMatrix = new JButton("Save");
        openSecondMatrix = new JButton("Open");
        fileInputSecondMatrix = new JTextField(3);
        // Result Window Buttons
        clearResultMatrix = new JButton("Clear");
        saveResultMatrix = new JButton("Save");
        openResultMatrix = new JButton("Open");
        fileInputResultMatrix = new JTextField(3);
    }

    // Display Starting Window
    public void displayMainWindow() {
        // Initialize Components
        initializeComponents();
        startingWindow = new JFrame("Matrix Math Program");
        // Setup Window
        startingWindow.setSize(400,200);
        startingWindow.setBounds(400, 200, 400, 300);
        startingWindow.setLayout(new GridLayout(6, 2));
        startingWindow.getContentPane().setBackground(Color.blue);
        startingWindow.setLocation(300,0);
        // First Label
        firstMatrixLabel = new JLabel("First Matrix Rows/Cols");
        firstMatrixLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        firstMatrixLabel.setForeground(Color.white);
        // Second Label
        secondMatrixLabel = new JLabel("Second Matrix Rows/Cols");
        secondMatrixLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        secondMatrixLabel.setForeground(Color.white);
        // First Matrix Text Fields
        firstMatrixRowsInput = new JTextField();
        firstMatrixColsInput = new JTextField();
        // Second Matrix Text Fields
        secondMatrixRowsInput = new JTextField();
        secondMatrixColsInput = new JTextField();
        // Buttons
        createButton = new JButton("Create");
        executeButton = new JButton("Execute");
        quitButton = new JButton("Quit");
        resetButton = new JButton("Reset");
        // Setup Checkbox
        selectOperation = new JComboBox<>();
        selectOperation.addItem("--Select--");
        selectOperation.addItem("Transpose");
        selectOperation.addItem("Addition");
        selectOperation.addItem("Subtraction");
        selectOperation.addItem("Multiplication");
        // Adding Components to Window
        startingWindow.add(firstMatrixLabel);
        startingWindow.add(secondMatrixLabel);
        startingWindow.add(firstMatrixRowsInput);
        startingWindow.add(secondMatrixRowsInput);
        startingWindow.add(firstMatrixColsInput);
        startingWindow.add(secondMatrixColsInput);
        startingWindow.add(new JLabel(""));
        startingWindow.add(createButton);
        startingWindow.add(selectOperation);
        startingWindow.add(executeButton);
        startingWindow.add(resetButton);
        startingWindow.add(quitButton);


        startingWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        startingWindow.setVisible(true);
        startingWindow.repaint();
    }

    public void displayFirstMatrixWindow(Boolean transpose, Boolean createClicked, Boolean openFile) {
        firstMatrixWindow = new JFrame("First Matrix");
        // Initialization
        firstMatrixPanel = new JPanel();
        firstButtonPanel = new JPanel();

        JLabel fileLabel = new JLabel("file");
        // Window Setup
        firstMatrixWindow.setSize(400,300);
        firstMatrixWindow.setBounds(400, 200, 400, 300);
        firstMatrixWindow.setLayout(new BorderLayout());
        firstMatrixWindow.setLocation(300,325);

        firstMatrixPanel.setBackground(Color.red);
        firstMatrixPanel.setLayout(new GridLayout(firstRows, firstCols));

        firstButtonPanel.setLayout(new FlowLayout());

        firstInput = new JTextField[firstRows][firstCols];
        // Add Corresponding Matrix Inputs
        if (openFile) {
            for (int i=0;i<firstMatrix.length;i++) {
                for (int j=0; j<firstMatrix[i].length; j++) {
                    firstInput[i][j] = new JTextField(String.valueOf(firstMatrix[i][j]));
                    firstMatrixPanel.add(firstInput[i][j]);
                }
            }
        } else if (!transpose) {
            for (int i=0;i<firstRows;i++) {
                for (int j=0; j<firstCols; j++) {
                    firstInput[i][j] = new JTextField();
                    firstMatrixPanel.add(firstInput[i][j]);
                }
            }
        } else {
            for (int i=0;i<firstRows;i++) {
                for (int j=0; j<firstCols; j++) {
                    firstInput[i][j] = new JTextField();
                    if (firstTransposed[i][j] == null) {
                        firstInput[i][j].setText("0.0");
                    } else {
                        firstInput[i][j].setText(String.valueOf(firstTransposed[i][j]));
                    }
                    firstMatrixPanel.add(firstInput[i][j]);
                }
            }
        }

        firstButtonPanel.add(clearFirstMatrix);
        firstButtonPanel.add(fileLabel);
        firstButtonPanel.add(fileInputFirstMatrix);
        firstButtonPanel.add(saveFirstMatrix);
        firstButtonPanel.add(openFirstMatrix);

        firstMatrixWindow.add(firstMatrixPanel, BorderLayout.CENTER);
        firstMatrixWindow.add(firstButtonPanel, BorderLayout.SOUTH);

        // Window Config
        firstMatrixWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (createClicked) {
            firstMatrixWindow.setVisible(true);
        }
        firstMatrixWindow.repaint();
    }

    public void displaySecondMatrixWindow(Boolean transpose, Boolean createClicked, Boolean openFile) {
        secondMatrixWindow = new JFrame("Second Matrix");
        // Initialization
        secondMatrixPanel = new JPanel();
        secondButtonPanel = new JPanel();

        JLabel fileLabel = new JLabel("file");

        // Window Setup
        secondMatrixWindow.setSize(400,300);
        secondMatrixWindow.setBounds(400, 200, 400, 300);
        secondMatrixWindow.setLayout(new BorderLayout());
        secondMatrixWindow.setLocation(700,325);

        secondMatrixPanel.setBackground(Color.orange);
        secondMatrixPanel.setLayout(new GridLayout(secondRows, secondCols));

        secondButtonPanel.setLayout(new FlowLayout());

        secondInput = new JTextField[secondRows][secondCols];
        // Add Corresponding Matrix Inputs
        if (openFile) {
            for (int i=0;i<secondMatrix.length;i++) {
                for (int j=0; j<secondMatrix[i].length; j++) {
                    secondInput[i][j] = new JTextField(String.valueOf(secondMatrix[i][j]));
                    secondMatrixPanel.add(secondInput[i][j]);
                }
            }
        } else if (!transpose) {
            for (int i=0;i<secondRows;i++) {
                for (int j=0; j<secondCols; j++) {
                    secondInput[i][j] = new JTextField();
                    secondMatrixPanel.add(secondInput[i][j]);
                }
            }
        } else {
            for (int i=0;i<secondRows;i++) {
                for (int j=0; j<secondCols; j++) {
                    secondInput[i][j] = new JTextField();
                    if (secondTransposed[i][j] == null) {
                        secondInput[i][j].setText("0.0");
                    } else {
                        secondInput[i][j].setText(String.valueOf(secondTransposed[i][j]));
                    }
                    secondMatrixPanel.add(secondInput[i][j]);
                }
            }
        }

        secondButtonPanel.add(clearSecondMatrix);
        secondButtonPanel.add(fileLabel);
        secondButtonPanel.add(fileInputSecondMatrix);
        secondButtonPanel.add(saveSecondMatrix);
        secondButtonPanel.add(openSecondMatrix);

        secondMatrixWindow.add(secondMatrixPanel, BorderLayout.CENTER);
        secondMatrixWindow.add(secondButtonPanel, BorderLayout.SOUTH);
        // Window Config
        secondMatrixWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (createClicked) {
            secondMatrixWindow.setVisible(true);
        }
        secondMatrixWindow.repaint();
    }

    public void displayResultWindow(Boolean resultClicked, Boolean clearClicked, Boolean openFile) {
        resultWindow = new JFrame("Matrix Math Program");
        // Setup Window
        resultWindow.setSize(400,200);
        resultWindow.setBounds(400, 200, 400, 300);
        resultWindow.setLayout(new BorderLayout());
        resultWindow.setBackground(Color.blue);
        resultWindow.setLocation(700,0);

        // Window Setup
        resultMatrixPanel = new JPanel();
        resultButtonPanel = new JPanel();
        resultMatrixPanel.setSize(400,200);
        resultMatrixPanel.setBounds(400, 200, 400, 300);
        resultMatrixPanel.setLayout(new BorderLayout());

        resultMatrixPanel.setBackground(Color.blue);
        resultMatrixPanel.setLayout(new GridLayout(resultRows, resultCols));

        JLabel fileLabel = new JLabel("file");

        resultButtonPanel.setLayout(new FlowLayout());

        // Add Components
        resultButtonPanel.add(clearResultMatrix);
        resultButtonPanel.add(fileLabel);
        resultButtonPanel.add(fileInputResultMatrix);
        resultButtonPanel.add(saveResultMatrix);
        resultButtonPanel.add(openResultMatrix);

        resultFields = new JTextField[resultRows][resultCols];

        if (resultClicked) {
            if (openFile) {
                for (int i=0;i<resultMatrix.length;i++) {
                    for (int j=0; j<resultMatrix[i].length; j++) {
                        resultFields[i][j] = new JTextField(String.valueOf(resultMatrix[i][j]));
                        resultMatrixPanel.add(resultFields[i][j]);
                    }
                }
            } else {
                resultFields = new JTextField[resultRows][resultCols]; // Create the array of JTextFields
            
                for (int i = 0; i < resultRows; i++) {
                    for (int j = 0; j < resultCols; j++) {
                        if (clearClicked) {
                            resultFields[i][j] = new JTextField("");
                        } else {
                            resultFields[i][j] = new JTextField(String.valueOf(resultMatrix[i][j])); // Initialize each JTextField
                        }
                        resultMatrixPanel.add(resultFields[i][j]); // Add to the panel
                    }
                }
            }
            
            resultWindow.add(resultMatrixPanel, BorderLayout.CENTER);
            resultWindow.setVisible(true);
        }
    
        resultWindow.add(resultButtonPanel, BorderLayout.SOUTH);

        // Window Config
        resultWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        resultWindow.repaint();
    }

    public static void warning(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, "InfoBox: " + title, JOptionPane.INFORMATION_MESSAGE);
    }
}
