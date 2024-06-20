package Lab04;

import java.awt.event.*;
import java.lang.Exception;

public class Lab04Control extends Lab04View implements ActionListener{

    public static void main(String args[])  {
        new Lab04Control();
    }

    public Lab04Control() {
        read.addActionListener(this);
        encode.addActionListener(this);
        decode.addActionListener(this);
        save.addActionListener(this);
        reset.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object clickedButton = e.getSource();
        Lab04Model model = new Lab04Model();
        if(clickedButton.equals(read)) {
            codeTextField.setText(model.selectAndReadFile());
        }
        if(clickedButton.equals(encode)) {
            try {
                outputTextField.setText("Here is your encoded message: \n" + model.encode(codeTextField.getText()));
            } catch (Exception error) {
                outputTextField.setText("Unable to encode message. Please ensure you have entered a valid message.");
            }
            resultFrame.setVisible(true);
        }
        if(clickedButton.equals(decode)) {
            try {
                outputTextField.setText("Here is your decoded message: \n" + model.decode(codeTextField.getText()));
            } catch (Exception error) {
                outputTextField.setText("Unable to decode message. Please ensure you have entered a valid encoded message.");
            }
            resultFrame.setVisible(true);
        }
        if(clickedButton.equals(save)) {
            model.saveFile(codeTextField.getText());
        }
        if(clickedButton.equals(reset)) {
            codeTextField.setText("");
        }
    }
}
