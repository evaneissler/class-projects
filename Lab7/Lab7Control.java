// Lab 7 Control // 
// Evan Eissler  //
// COSC 10403-15 //

import java.awt.event.*;


public class Lab7Control extends Lab7View implements ActionListener{
    
    public static void main(String args[]) {
        new Lab7Control();
    }

    public Lab7Control() {
        click.addActionListener(this);
        convert.addActionListener(this);
        convertLetters.addActionListener(this);
        reset.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object whichButton = e.getSource();
        if (whichButton.equals(click)) {
            getTime();
        } else if (whichButton.equals(convert)) {
            convert();
        }
        else if (whichButton.equals(convertLetters)) {
            convertLetters();
        }
        else if (whichButton.equals(reset)) {
            reset();
        }
        else {
            System.out.println("Button not recognized");
        }
    }

    public void getTime() {
        if (count % 2 != 0) {
            start = System.currentTimeMillis();
            System.out.println("First Click");
            System.out.println(start);
            count++;
        } else if (count % 2 == 0) {
            finish = System.currentTimeMillis();
            System.out.println("Second Click");
            System.out.println(finish);
            thisLetter = thisLetter + getDotOrDash(getDuration());
            morseCode.setText(thisLetter);
            count = 1;
        }
        System.out.println(thisLetter);
    }

    public void convert() {
        morseCode.setText(thisLetter);
        letterList = letterList + convertMorse(thisLetter);
        thisLetter = "";
        morseCode.setText("");
        letters.setText(letterList);
    }

    public void convertLetters() {
        String word = letters.getText();
        word = word.toUpperCase();
        String thisMorseCode = "";
        for (int i = 0; i <= word.length() - 1; i++) {
            thisMorseCode = thisMorseCode + convertLetter(word.substring(i, i+1)) + " ";
        }
        System.out.println(thisMorseCode);
        morseCode.setText(thisMorseCode);
        letters.setText("");
    }

    public void reset() {
        letterList = "";
        thisLetter = "";
        morseCode.setText("");
        letters.setText("");
    }

    public long getDuration() {
        System.out.print("Difference:");
        System.out.println(finish - start);
        long difference = (finish - start);

        count = 1;
        finish = 0;
        start = 0;
        return difference;
    }

    public String getDotOrDash(long diff) {
        if (diff >= 350) {
            return "-";
        } 
        return ".";
    }

    public String convertMorse(String morse) {
        String thisLetter = "";
        System.out.println("GOT HERE");
        System.out.println(morse);
        switch (morse) {
            case ".-":
                thisLetter = "A";
                break;
            case "-...":
                thisLetter = "B";
                break;
            case "-.-.":
                thisLetter = "C";
                break;
            case "-..":
                thisLetter = "D";
                break;
            case ".":
                thisLetter = "E";
                break;
            case "..-.":
                thisLetter = "F";
                break;
            case "--.":
                thisLetter = "G";
                break;
            case "....":
                thisLetter = "H";
                break;
            case "..":
                thisLetter = "I";
                break;
            case ".---":
                thisLetter = "J";
                break;
            case "-.-":
                thisLetter = "K";
                break;
            case ".-..":
                thisLetter = "L";
                break;
            case "--":
                thisLetter = "M";
                break;
            case "-.":
                thisLetter = "N";
                break;
            case "---":
                thisLetter = "O";
                break;
            case ".--.":
                thisLetter = "P";
                break;
            case "--.-":
                thisLetter = "Q";
                break;
            case ".-.":
                thisLetter = "R";
                break;
            case "...":
                thisLetter = "S";
                break;
            case "-":
                thisLetter = "T";
                break;
            case "..-":
                thisLetter = "U";
                break;
            case "...-":
                thisLetter = "V";
                break;
            case ".--":
                thisLetter = "W";
                break;
            case "-..-":
                thisLetter = "X";
                break;
            case "-.--":
                thisLetter = "Y";
                break;
            case "--..":
                thisLetter = "Z";
                break;
            case ".----":
                thisLetter = "1";
                break;
            case "..---":
                thisLetter = "2";
                break;
            case "...--":
                thisLetter = "3";
                break;
            case "....-":
                thisLetter = "4";
                break;
            case ".....":
                thisLetter = "5";
                break;
            case "-....":
                thisLetter = "6";
                break;
            case "--...":
                thisLetter = "7";
                break;
            case "---..":
                thisLetter = "8";
                break;
            case "----.":
                thisLetter = "9";
                break;
            case "-----":
                thisLetter = "0";
                break;
        }
        return thisLetter;
    }

    public String convertLetter(String letter) {
        System.out.println("GOT HERE");
        String thisMorse = "";
        switch (letter) {
            case "A":
                thisMorse = ".-";
                break;
            case "B":
                thisMorse = "-...";
                break;
            case "C":
                thisMorse = "-.-.";
                break;
            case "D":
                thisMorse = "-..";
                break;
            case "E":
                thisMorse = ".";
                break;
            case "F":
                thisMorse = "..-.";
                break;
            case "G":
                thisMorse = "--.";
                break;
            case "H":
                thisMorse = "...";
                break;
            case "I":
                thisMorse = "..";
                break;
            case "J":
                thisMorse = ".---";
                break;
            case "K":
                thisMorse = "-.-";
                break;
            case "L":
                thisMorse = ".-..";
                break;
            case "M":
                thisMorse = "--";
                break;
            case "N":
                thisMorse = "-.";
                break;
            case "O":
                thisMorse = "---";
                break;
            case "P":
                thisMorse = ".--.";
                break;
            case "Q":
                thisMorse = "--.-";
                break;
            case "R":
                thisMorse = ".-.";
                break;
            case "S":
                thisMorse = "...";
                break;
            case "T":
                thisMorse = "-";
                break;
            case "U":
                thisMorse = "..-";
                break;
            case "V":
                thisMorse = "...-";
                break;
            case "W":
                thisMorse = ".--";
                break;
            case "X":
                thisMorse = "-..-";
                break;
            case "Y":
                thisMorse = "-.--";
                break;
            case "Z":
                thisMorse = "--..";
                break;
            case "1":
                thisMorse = ".----";
                break;
            case "2":
                thisMorse = "..---";
                break;
            case "3":
                thisMorse = "...--";
                break;
            case "4":
                thisMorse = "....-";
                break;
            case "5":
                thisMorse = ".....";
                break;
            case "6":
                thisMorse = "-....";
                break;
            case "7":
                thisMorse = "--...";
                break;
            case "8":
                thisMorse = "---..";
                break;
            case "9":
                thisMorse = "----.";
                break;
            case "0":
                thisMorse = "-----";
                break;
            case " ":
                thisMorse = " ";
                break;
        }
        return thisMorse;
    }

}
