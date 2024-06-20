package Lab04;

import java.awt.*;
import javax.swing.*;

public class Lab04View extends JFrame {

    JButton read, encode, decode, save, reset;
    JTextArea codeTextField, outputTextField;
    JFrame resultFrame;
    JPanel buttonPanel, codePanel;

    /**
     * Builds and displays the input JFrame and the output/console JFrame
     */
    public Lab04View() {

        // Configure Button Panel
        buttonPanel = new JPanel();
        read = new JButton("Read");
        encode = new JButton("Encode");
        decode = new JButton("Decode");
        save = new JButton("Save");
        reset = new JButton("Reset");
        buttonPanel.setLayout(new GridLayout(1,5));
        buttonPanel.add(read);
        buttonPanel.add(encode);
        buttonPanel.add(decode);
        buttonPanel.add(save);
        buttonPanel.add(reset);
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.decode("#6f55ab"));

        // Configure Code Panel
        codeTextField = new JTextArea(10,70);
        codeTextField.setLineWrap(true);
        codePanel = new JPanel();
        codeTextField.setBackground(Color.decode("#fffde0"));
        codePanel.add(codeTextField);
        codePanel.setVisible(true);

        // Configure JFrame
        setTitle("Encrypt/Decrypt Using Pi");
        setLayout(new BorderLayout());
        setSize(600,400);
        setBounds(250, 100, 900, 250);

        // Configure Result Frame
        resultFrame = new JFrame();
        resultFrame.setTitle("Result");
        resultFrame.setLayout(new FlowLayout());
        resultFrame.setSize(300,400);
        resultFrame.setBounds(250, 350, 900, 250);

        outputTextField = new JTextArea(10,70);
        outputTextField.setBackground(Color.decode("#fffde0"));
        outputTextField.setLineWrap(true);

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
