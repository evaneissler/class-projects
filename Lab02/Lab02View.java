package Lab02;

import java.awt.*;
import javax.swing.*;

public class Lab02View extends JFrame {

    /**
    * @author Evan Eissler
    * Displays JFrame with JTextFields and JButtons
    */

    JTextField commandInput, binaryInput, hexInput, errorTextField;
    JButton decodeCommand, encodeBinary, encodeHex, reset;
    Image arm1, arm2, arm3;
    JPanel imagePanel, inputPanel;
    JLabel armInst, binInst, hexInst;

    /**
     * Builds and displays the view
     */
    public Lab02View() {
        imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        ImageIcon icon1 = new ImageIcon(new ImageIcon("Lab02/assets/arm1.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        ImageIcon icon2 = new ImageIcon(new ImageIcon("Lab02/assets/arm2.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        ImageIcon icon3 = new ImageIcon(new ImageIcon("Lab02/assets/arm3.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        imagePanel.add(new JLabel(icon1));
        imagePanel.add(new JLabel(icon2));
        imagePanel.add(new JLabel(icon3));
        imagePanel.setVisible(true);
        
        // Initialize Components
        setTitle("Encoding ARM Instructions");
        setLayout(new BorderLayout());
        setSize(600,400);
        setBounds(100, 100, 600, 400);

        commandInput = new JTextField(25);
        binaryInput = new JTextField(25);
        hexInput = new JTextField(25);
        errorTextField = new JTextField(50);

        decodeCommand = new JButton("Encode Command");
        encodeBinary = new JButton("Decode Binary");
        encodeHex = new JButton("Decode Hex");
        reset = new JButton("Reset");

        JLabel armInst = new JLabel("Input Arm Instructions");
        armInst.setForeground(Color.white);
        JLabel binInst = new JLabel("Binary Instructions");
        binInst.setForeground(Color.white);
        JLabel hexInst = new JLabel("Hex Instructions");
        hexInst.setForeground(Color.white);

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6,2));
        inputPanel.add(commandInput);
        inputPanel.add(decodeCommand);
        inputPanel.add(armInst);
        inputPanel.add(new JLabel(""));
        inputPanel.add(binaryInput);
        inputPanel.add(hexInput);
        inputPanel.add(binInst);
        inputPanel.add(hexInst);
        inputPanel.add(encodeBinary);
        inputPanel.add(encodeHex);
        inputPanel.add(reset);

        inputPanel.setBackground(Color.decode("#4D1979"));
        imagePanel.setBackground(Color.decode("#4D1979"));

        // Add Components to Frame
        add(imagePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        repaint();
    }
}