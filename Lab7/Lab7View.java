// Lab 7 View    // 
// Evan Eissler  //
// COSC 10403-15 //

import java.awt.*;
import javax.swing.*;

public class Lab7View extends JFrame {
    
    JButton click, convert, convertLetters, reset;
    JFrame resultWindow, instructionWindow;
    JLabel instructions, morse, words, help;
    JTextField morseCode, letters;
    long start, finish;
    int count = 1;
    String thisLetter = "";
    String letterList = "";


    public Lab7View() {
        displayMainWindow();
        displayResultWindow();
        displayInstructionWindow();
    }

    public void displayMainWindow() {
        dispose();
        setSize(350,300);
        setBounds(350, 250, 350, 300);
        setLayout(new BorderLayout());

        click = new JButton("Click");
        add(click, BorderLayout.CENTER);

        instructions = new JLabel("Double Click Quick For '.', Double Click Slow For '-'");
        add(instructions, BorderLayout.NORTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        repaint();
    }

    public void displayResultWindow() {
        resultWindow = new JFrame();
        resultWindow.setSize(300,300);
        resultWindow.setBounds(750, 250, 300, 300);
        resultWindow.setLayout(new GridLayout(7, 1));
        resultWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);

        morse = new JLabel("Morse Code");
        words = new JLabel("Text");
        morseCode = new JTextField();
        letters = new JTextField();
        convert = new JButton("Convert Morse Code To Text");
        convertLetters = new JButton("Convert Text To Morse Code");
        reset = new JButton("Reset");

        resultWindow.add(morse);
        resultWindow.add(morseCode);
        resultWindow.add(convert);
        resultWindow.add(convertLetters);
        resultWindow.add(reset);
        resultWindow.add(words);
        resultWindow.add(letters);

        resultWindow.setVisible(true);
        resultWindow.repaint();
    }

    public void displayInstructionWindow() {
        instructionWindow = new JFrame();
        instructionWindow.setLayout(new FlowLayout());
        instructionWindow.setSize(800,75);
        instructionWindow.setBounds(350, 150, 800, 75);
        help = new JLabel("Click the button on the left to record your morse code (one letter at a time), or enter your text to convert to morse code.");
        instructionWindow.add(help);
        instructionWindow.setVisible(true);
        instructionWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}


