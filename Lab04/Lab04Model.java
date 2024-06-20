package Lab04;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;

public class Lab04Model {

    /**
     * Goes through each character in the input message and subtracts the corresponding pi value from the byte value
     * @param input The message in the input text area
     * @return The encoded message in character form
     */
    public String encode(String input) {

        String encodedString = "";
        String encryptionDigits = "314159265358979323846264338327950288419716939937510582097494459230781640628620";

        for (int i = 0; i < input.length(); i++) {
            Integer index;
            if (i == 0 ) {
                index = 0;
            } else {
                index = 77 % i;
            }
            encodedString = encodedString + (char)((byte) input.charAt(i) - Integer.valueOf(encryptionDigits.substring(index, index+1)));
        }

        return encodedString;
    }

    /**
     * Goes through each character and adds the corresponding pi digit to the byte value to get original character before encoding
     * @param input The string message being decoded
     * @return The resulting string after decoding
     */
    public String decode(String input) {

        String inputBytes = "";

        for (int i=0;i<input.length();i++) {
            inputBytes = inputBytes + (byte)input.charAt(i);
        }

        String decryptionDigits = "314159265358979323846264338327950288419716939937510582097494459230781640628620";
        ArrayList<String> encodedBytes = new ArrayList<String>(1000);
        String decodedString = "";

        Integer count = 0;

        while (count < inputBytes.length()) {
            if (inputBytes.substring(count, count + 1).equals("1")) {
                encodedBytes.add(inputBytes.substring(count, count + 3));
                count = count + 3;
            } else {
                encodedBytes.add(inputBytes.substring(count, count + 2));
                count = count + 2;
            }
        }

        for (int i=0;i<encodedBytes.size();i++) {
            Integer index;
                if (i == 0) {
                    index = 0;
                } else {
                    index = 77 % i;
                }
                char decodedByte = (char)(Byte.parseByte(String.valueOf(Integer.parseInt(encodedBytes.get(i)) + Integer.valueOf(decryptionDigits.substring(index, index+1)))));
                decodedString = decodedString + decodedByte;
        }

        return decodedString;
    }

    /**
     * Allows user to select and read a file
     * @return A string containing the contents of the file
     */
    public String selectAndReadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select File");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            if (selectedFile != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    return content.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error reading the file.";
                }
            }
        }

        return "No file selected.";
    }

    /**
     * Allows user to save input code into a file
     * @param code Code in the TextArea
     */
    public void saveFile(String code) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        
        // Set the filter to restrict file types (e.g., .txt files)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write(code);
                writer.close();

                System.out.println("File saved successfully to: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error: Failed to save the file.");
            }
        } else if (userSelection == JFileChooser.CANCEL_OPTION) {
            System.out.println("File save operation was canceled by the user.");
        }
    }
}
