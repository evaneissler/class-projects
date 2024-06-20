package Lab02;

import java.awt.event.*;

public class Lab02Control extends Lab02View implements ActionListener {

    /**
    * @author Evan Eissler
    * Converts view to model via ActionListener
    */

    /**
     * Main method of program
     * @param args
     */
    public static void main(String args[]) {
        new Lab02Control();
    }

    /**
     * Attaches ActionListener instance to buttons in the view
     */
    public Lab02Control() {
        // Attach Listeners
        decodeCommand.addActionListener(this);
        encodeBinary.addActionListener(this);
        encodeHex.addActionListener(this);
        reset.addActionListener(this);
    }
    
    /**
     * Calls corresponding methods when certain buttons are clicked
     */
    public void actionPerformed(ActionEvent e) {
        Object clickedButton = e.getSource();
        Lab02Model model = new Lab02Model();
        // Encoding the input in command
        if (clickedButton.equals(decodeCommand)) {
            binaryInput.setText(model.decodeCommandToBinary(commandInput.getText()));
            hexInput.setText(model.decodeCommandToHex(model.decodeCommandToBinary(commandInput.getText())));
        }
        // Decoding binary input
        if (clickedButton.equals(encodeBinary)) {
            commandInput.setText(model.encodeBinaryToCommand(binaryInput.getText()));
            hexInput.setText(model.encodeBinaryToHex(binaryInput.getText()));
        }
        // Decoding hex input
        if (clickedButton.equals(encodeHex)) {
            commandInput.setText(model.encodeHexToCommand(model.encodeHexToBinary(hexInput.getText())));
            binaryInput.setText(model.encodeHexToBinary(hexInput.getText()));
        }
        // Resetting Input
        if (clickedButton.equals(reset)) {
            commandInput.setText("");
            binaryInput.setText("");
            hexInput.setText("");
        }
    }
}
