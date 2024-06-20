package Lab02;

import java.util.*;
import java.lang.Exception;

public class Lab02Model {

    /**
    * @author Evan Eissler
    * Converts between ARM Commands, Binary, and Hex
    */
    
    /**
     * This method uses the StringTokenizer to access input commands and convert to the corresponding binary
     * @param input The commands entered into the text field
     * @return returns the binary output of the command
     */
    public String decodeCommandToBinary(String input) {
        try {
            // Get input in usable form
            StringTokenizer commandTokens = new StringTokenizer(input, ", ");
            String[] inputs = new String[4];
            String binaryOutput = "";
            String[] registerBinary = new String[3];

            int i = 0;
            while(commandTokens.hasMoreTokens()) {
                inputs[i] = commandTokens.nextToken();
                i++;
            }
            int j = 0;
            for(i=0;i<4;i++) {
                inputs[i] = inputs[i].toUpperCase();
                inputs[i] = inputs[i].replace(",","");
                if (inputs[i].length() == 2) {
                    registerBinary[j] = convertToFourBitBinary(Character.getNumericValue(inputs[i].charAt(1)));
                    j++;
                }
            }
            // Build binary output
            // Ignored
            binaryOutput = binaryOutput + "1110000";
            // Operation code
            binaryOutput = binaryOutput + getOperationCode(inputs[0]);
            // Fixed
            binaryOutput = binaryOutput + "0";
            // Op 1 register
            binaryOutput = binaryOutput + registerBinary[1];
            // Destination register
            binaryOutput = binaryOutput + registerBinary[0];
            // Shifts
            binaryOutput = binaryOutput + "00000000";
            // Op 2 register
            binaryOutput = binaryOutput + registerBinary[2];

            if (binaryOutput.contains("null")) {
                throw new CommandInputException("Invalid Command Entered");
            }

            return binaryOutput;

        } catch (Exception e) {
            return "ARM Command Entered Is Invalid";
        }
    }

    /**
     * This method converts the command input to the corresponding binary
     * @param binaryInput The command is first converted to binary and then passed as a parameter to this method
     * @return The corresponding hex output is returned
     */
    public String decodeCommandToHex(String binaryInput) {

        try {
            String hexOutput = "";
            
            int i = 0;
            int j = 4;

            while (j<=binaryInput.length()) {
                hexOutput = hexOutput + getHexDigit(binaryInput.substring(i, j));
                i = i + 4;
                j = j + 4;
            }
            return hexOutput;

        } catch (Exception e) {
            return "ARM Command Entered Is Invalid";
        }
    }

    /**
     * This method takes the binary input and converts it to the corresponding commands
     * @param input The binary string being converted
     * @return A string is returned containing the resulting command
     */
    public String encodeBinaryToCommand(String input) {
        try {
            String commandOutput = "";
            int i = 0;
            int j = 4;

            String saveDestReg = "";

            if (input.length() != 32) {
                throw new BinaryInputException("Binary Input Invalid");
            }

            while (j<=input.length()) {
                if (i==7) {
                    System.out.println(input.substring(i, j));
                    commandOutput = commandOutput + getOperandFromBinary(input.substring(i, j)) + " ";
                }
                if (i==12) {
                    saveDestReg = "R" + convertBinaryToNumber(input.substring(i, j)) + ", ";
                }
                if (i==16) {
                    commandOutput = commandOutput + "R" + convertBinaryToNumber(input.substring(i, j)) + ", " + saveDestReg;
                }
                if (i==28) {
                    commandOutput = commandOutput + "R" + convertBinaryToNumber(input.substring(i, j)) + " ";
                }
                i++;
                j++;
            }
            return commandOutput;

        } catch (Exception e) {
            return "Binary Entered Is Invalid";
        }
    }

    /**
     * This method takes the binary input and converts it to the corresponding hex 
     * @param input The binary string being converted
     * @return A String containing the resulting hex value
     */
    public String encodeBinaryToHex(String input) {

        try {
            String hexOutput = "";
            
            int i = 0;
            int j = 4;

            while (j<=input.length()) {
                hexOutput = hexOutput + getHexDigit(input.substring(i, j));
                i = i + 4;
                j = j + 4;
            }

            if (hexOutput.length() != 8) {
                throw new BinaryInputException("Binary Input Invalid");
            }

            return hexOutput;

        } catch (Exception e) {
            return "Binary Entered Is Invalid";
        }
    }

    /**
     * This method takes the hex input and converts it to the corresponding command 
     * @param input The hex string being converted
     * @return A String containing the resulting command
     */
    public String encodeHexToCommand(String input) {

        try {

            if (input.length() != 8) {
                throw new HexInputException("Hex Input Invalid");
            }

            String commandOutput = "";
            int i = 0;
            int j = 4;

            String saveDestReg = "";

            while (j<=input.length()) {
                if (i==7) {
                    System.out.println(input.substring(i, j));
                    commandOutput = commandOutput + getOperandFromBinary(input.substring(i, j)) + " ";
                }
                if (i==12) {
                    saveDestReg = "R" + convertBinaryToNumber(input.substring(i, j)) + ", ";
                }
                if (i==16) {
                    commandOutput = commandOutput + "R" + convertBinaryToNumber(input.substring(i, j)) + ", " + saveDestReg;
                }
                if (i==28) {
                    commandOutput = commandOutput + "R" + convertBinaryToNumber(input.substring(i, j)) + " ";
                }
                i++;
                j++;
            }
            return commandOutput;

        } catch (Exception e) {
            return "Invalid Hex Entered";
        }
    }

    /**
     * This method takes the hex input and converts it to the corresponding binary 
     * @param input The hex string being converted
     * @return A String containing the resulting binary
     */
    public String encodeHexToBinary(String input) {

        try {

            if (input.length() != 8) {
                throw new HexInputException("Hex Input Invalid");
            }

            String binaryOutput = "11100000";

            for (int i=0;i<input.length();i++) {
                if (i==2) {
                    binaryOutput = binaryOutput + getBinaryFromHex(input.substring(i, i+1));
                } 
                if (i==3) {
                    binaryOutput = binaryOutput + convertToFourBitBinary(Integer.parseInt(input.substring(i, i+1)));
                }
                if (i==4) {
                    binaryOutput = binaryOutput + convertToFourBitBinary(Integer.parseInt(input.substring(i, i+1)));
                }
                if (i==7) {
                    binaryOutput = binaryOutput + "00000000" + convertToFourBitBinary(Integer.parseInt(input.substring(i, i+1)));
                }
            }
            return binaryOutput;
        } catch (Exception e) {
            return "Invalid Hex Entered";
        }
    }

    /**
     * This method converts the input integer to four bit binary
     * @param number The integer being converted
     * @return The String containining the resulting binary
     */
    public String convertToFourBitBinary(int number) {

        String binary = "0000";
        String twoToFirst = "0";
        String twoToSecond = "0";
        String twoToThird = "0";
        String twoToFourth = "0";

        if (number <= 15) {
            while (number > 0) {
                if (number / 8 == 1) {
                    twoToFourth = "1";
                    number = number - 8;
                }
                if (number / 4 == 1) {
                    twoToThird = "1";
                    number = number - 4;
                }
                if (number / 2 == 1) {
                    twoToSecond = "1";
                    number = number - 2;
                }
                if (number / 1 == 1) {
                    twoToFirst = "1";
                    number = number - 1;
                }
            }
        } 
        binary = twoToFourth + twoToThird + twoToSecond + twoToFirst;
        return binary;
    }

    /**
     * This method converts the input binary string to an integer
     * @param binaryInput The binary string that is being converted
     * @return The integer representation of the binary string
     */
    public Integer convertBinaryToNumber(String binaryInput) {

        return Integer.parseInt(binaryInput, 2);
    }

    /**
     * This method returns the operation code based on the input command
     * @param operationInput The string containing the operation 
     * @return The string containing the binary representation of the operation
     */
    public String getOperationCode(String operationInput) {

        String binaryOperation = "";

        switch (operationInput) {
            case"AND":
                binaryOperation = "0000";
                break;
            case"EOR":
                binaryOperation = "0001";
                break;
            case"SUB":
                binaryOperation = "0010";
                break;
            case"RSB":
                binaryOperation = "0011";
                break;
            case"ADD":
                binaryOperation = "0100";
                break;
            case"ADC":
                binaryOperation = "0101";
                break;
            case"SBC":
                binaryOperation = "0110";
                break;
            case"RSC":
                binaryOperation = "0111";
                break;
            case"TST":
                binaryOperation = "1000";
                break;
            case"TEQ":
                binaryOperation = "1001";
                break;
            case"CMP":
                binaryOperation = "1010";
                break;
            case"CMN":
                binaryOperation = "1011";
                break;
            case"ORR":
                binaryOperation = "1100";
                break;
            case"MOV":
                binaryOperation = "1101";
                break;
            case"BIC":
                binaryOperation = "1110";
                break;
            case"MVN":
                binaryOperation = "1111";
                break;
            default:
                binaryOperation = "0000";
                break;
        }
        return binaryOperation;
    }

    /**
     * This method returns the operation based on the input binary
     * @param binary The string containing the operation binary
     * @return The string containing the resulting operand of the binary
     */
    public String getOperandFromBinary(String binary) {

        System.out.print(binary);

        String operand;

        switch (binary) {
            case"0000":
                operand = "AND";
                break;
            case"0001":
                operand = "EOR";
                break;
            case"0010":
                operand = "SUB";
                break;
            case"0011":
                operand = "RSB";
                break;
            case"0100":
                operand = "ADD";
                break;
            case"0101":
                operand = "ADC";
                break;
            case"0110":
                operand = "SBC";
                break;
            case"0111":
                operand = "RSC";
                break;
            case"1000":
                operand = "TST";
                break;
            case"1001":
                operand = "TEQ";
                break;
            case"1010":
                operand = "CMP";
                break;
            case"1011":
                operand = "CMN";
                break;
            case"1100":
                operand = "ORR";
                break;
            case"1101":
                operand = "MOV";
                break;
            case"1110":
                operand = "BIC";
                break;
            case"1111":
                operand = "MVN";
                break;
            default:
                operand = "AND";
                break;
        }
        System.out.print(operand);
        return operand;
    }

    /**
     * This method gets the resulting hex digit of the input binary string
     * @param binaryInput A string containing the binary being converted to hex
     * @return A string containing the resulting hex digit
     */
    public String getHexDigit(String binaryInput) {

        int number = convertBinaryToNumber(binaryInput);

        if (number < 10) {
            return String.valueOf(number);
        } else {
            if (number == 10) {
                return "A";
            }
            if (number == 11) {
                return "B";
            }
            if (number == 12) {
                return "C";
            }
            if (number == 13) {
                return "D";
            }
            if (number == 14) {
                return "E";
            }
            if (number == 15) {
                return "F";
            }
        }
        return "E";
    }

    /**
     * This method returns the resulting binary from the input hex digit
     * @param hex A string containing a hex digit
     * @return The resulting binary string 
     */
    public String getBinaryFromHex(String hex) {
        String binary = "";

        switch (hex) {
            case "0":
                binary = "0000";
                break;
            case "1":
                binary = "0001";
                break;
            case "2":
                binary = "0010";
                break;
            case "3":
                binary = "0011";
                break;
            case "4":
                binary = "0100";
                break;
            case "5":
                binary = "0101";
                break;
            case "6":
                binary = "0110";
                break;
            case "7":
                binary = "0111";
                break;
            case "8":
                binary = "1000";
                break;
            case "9":
                binary = "1001";
                break;
            case "A":
                binary = "1010";
                break;
            case "B":
                binary = "1011";
                break;
            case "C":
                binary = "1100";
                break;
            case "D":
                binary = "1101";
                break;
            case "E":
                binary = "1110";
                break;
            case "F":
                binary = "1111";
                break;
            default:
                binary = "0000";
        }
        return binary;
    }

    public class BinaryInputException extends Exception {
        public BinaryInputException(String errorMessage) {
            System.out.println("Binary Input Invalid");
        }
    }

    public class HexInputException extends Exception {
        public HexInputException(String errorMessage) {
            System.out.println("Hex Input Invalid");
        }
    }

    public class CommandInputException extends Exception {
        public CommandInputException(String errorMessage) {
            System.out.println("Command Input Invalid");
        }
    }
}
