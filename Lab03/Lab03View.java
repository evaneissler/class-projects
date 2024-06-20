package Lab03;

import java.awt.*;
import javax.swing.*;

public class Lab03View extends JFrame {

    JButton read, run, save, reset;
    JTextArea codeTextField, outputTextField;
    JFrame resultFrame;
    JPanel buttonPanel, codePanel;

    /**
     * Builds and displays the input JFrame and the output/console JFrame
     */
    public Lab03View() {
        
        // Configure Button Panel
        buttonPanel = new JPanel();
        read = new JButton("Read");
        run = new JButton("Run");
        save = new JButton("Save");
        reset = new JButton("Reset");
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.add(read);
        buttonPanel.add(run);
        buttonPanel.add(save);
        buttonPanel.add(reset);
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.decode("#6f55ab"));

        // Configure Code Panel
        codeTextField = new JTextArea(30,50);
        codePanel = new JPanel();
        codeTextField.setBackground(Color.decode("#fffde0"));
        codePanel.add(codeTextField);
        codePanel.setVisible(true);

        // Configure JFrame
        setTitle("Basic Interpreter");
        setLayout(new BorderLayout());
        setSize(600,400);
        setBounds(100, 100, 600, 500);

        // Configure Result Frame
        resultFrame = new JFrame();
        resultFrame.setTitle("Console Output");
        resultFrame.setLayout(new FlowLayout());
        resultFrame.setSize(300,400);
        resultFrame.setBounds(700, 150, 375, 450);

        outputTextField = new JTextArea(25,30);
        outputTextField.setBackground(Color.decode("#fffde0"));

        resultFrame.add(outputTextField);
        resultFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        resultFrame.repaint();

        // Add Components to Frame
        add(buttonPanel, BorderLayout.NORTH);
        add(codePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        repaint();
    }

}