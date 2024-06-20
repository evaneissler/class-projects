package Lab03;

import java.awt.event.*;
import java.lang.Exception;

public class Lab03Control extends Lab03View implements ActionListener{

    public static void main(String args[])  {
        new Lab03Control();
    }

    public Lab03Control() {
        read.addActionListener(this);
        run.addActionListener(this);
        save.addActionListener(this);
        reset.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object clickedButton = e.getSource();
        Lab03Model model = new Lab03Model();
        if(clickedButton.equals(read)) {
            codeTextField.setText(model.selectAndReadFile());
        }
        if(clickedButton.equals(run)) {
            try {
                outputTextField.setText(model.runCode(codeTextField.getText(), 0.0));
            } catch (Exception error) {
                outputTextField.setText("Error running code. Please ensure your syntax is correct.\n" + error);
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
