package Lab05;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Lab05ChatClient extends JFrame { 
    public  final boolean verbose = true;
    
    JButton sendButton;

    JTextArea incoming;
    JTextField outgoing, recipient;
    JCheckBox encrypted;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    String todayS, timeS, minS, secS, myName;
    Calendar now; int year,month,day,hour,min,sec;
    Boolean hasName = false;
  
    public static void main(String args[]) {
	    System.out.println("Chat Service");
        Lab05ChatClient client =  new Lab05ChatClient();
	    client.go();
    }

    /**
     * Creates a JFrame for each client and calls setUpNetworking() to join the server
     */
    public void go() {
        JFrame frame = new JFrame(" Chat Client");
        JPanel mainPanel = new JPanel();
        int sec = Integer.parseInt(processTime(3));
        if( sec > 50 ) frame.setBackground(Color.blue);
             else if( sec > 40 ) frame.setBackground(Color.magenta);
               else if( sec > 30 ) frame.setBackground(Color.yellow);
                 else if( sec > 20 ) frame.setBackground(Color.red);
                   else if( sec > 10 ) frame.setBackground(Color.black);
                                  else frame.setBackground(Color.green);
        frame.setBounds(400+5*sec,8*sec,300,300);
        mainPanel.setLayout(new GridLayout(3,1, 20, 20));
        mainPanel.setBackground(Color.yellow);
        incoming = new JTextArea(5, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        incoming.setText("Client logged on "+processTime(2) +"\n" + "Please Enter Your Name In The \"Your Message\" Text Field And Click \"Send\":\n");
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        outgoing = new JTextField(20);
        sendButton = new JButton("Send");
        SendButtonListener sendButtonListener = new SendButtonListener();
        sendButton.addActionListener(sendButtonListener);
        recipient = new JTextField(10);
        encrypted = new JCheckBox();
        mainPanel.add(qScroller);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setBackground(Color.yellow);
        fieldPanel.setLayout(new GridLayout(0,2));
        fieldPanel.add(new JLabel("        Recipient: "));
        fieldPanel.add(recipient);
        fieldPanel.add(new JLabel("        Your Message: "));
        fieldPanel.add(outgoing);
        fieldPanel.add(new JLabel("        Click to Encrypt Your Message"));
        fieldPanel.add(encrypted);

        mainPanel.add(fieldPanel);
        JPanel sendButtonPanel = new JPanel();
        sendButtonPanel.setBackground(Color.yellow);
        sendButtonPanel.setLayout(new GridLayout(3,3));
        sendButtonPanel.add(new JLabel(""));
        sendButtonPanel.add(new JLabel(""));
        sendButtonPanel.add(new JLabel(""));
        sendButtonPanel.add(new JLabel(""));
        sendButtonPanel.add(sendButton);
        sendButtonPanel.add(new JLabel(""));
        sendButtonPanel.add(new JLabel(""));
        sendButtonPanel.add(new JLabel(""));
        sendButtonPanel.add(new JLabel(""));
        mainPanel.add(sendButtonPanel);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        setUpNetworking();
       
        Thread readerThread = new Thread(new IncomingReader());  // thread to read messages
        readerThread.start();
        
        frame.setSize(650, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Gets time and formats in desired format
     * @param option is the desired format to display
     * @return 
     */
    public String processTime(int option) {    
        now = Calendar.getInstance();
	    year = now.get(Calendar.YEAR); 
        month = now.get(Calendar.MONTH)+1; 
        day = now.get(Calendar.DAY_OF_MONTH);
        hour = now.get(Calendar.HOUR);
        min =  now.get(Calendar.MINUTE);	  
        sec =  now.get(Calendar.SECOND);
        if (min < 10 )  minS =  "0" + min ;  else  minS = "" + min;
        if (sec < 10 )  secS =  "0" + sec ;  else  secS = "" + sec;
        todayS =  month + " / " + day + " / " + year;  
        timeS  = hour + " : " + minS + " : " + secS; 
        switch(option) {
        case (0):  return todayS  ; 
        case (1):  return timeS;
        case (2):  return todayS + " @ " + timeS ; 
        case (3): return secS;
        } 
        return null;  // should not get here
     }
    
    /*
     * Creates new socket and sets up the PrintWriter and BufferedReader to get and display messages from the server
     */
    private void setUpNetworking() {
        try {if (verbose) System.out.println("Openning socket at port 5000");
            sock = new Socket("127.0.0.1", 5001);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking protocol established");
        }
        catch(IOException ex)
        {
            System.out.println("Unable to connnect to network");
        }
    }
    
    /**
     * Internal class to use inherited components and handle user actions
     */
    public class SendButtonListener implements ActionListener {
        /*
         * Handles the Send button action
         * Tokenizes the string sent and puts corresponding information to server
         */
        public void actionPerformed(ActionEvent ev) {
            System.out.println("CLICKING");
            try {
                String message = outgoing.getText().isEmpty() ? "null" : outgoing.getText();
                String username = recipient.getText().isEmpty() ? "null" : recipient.getText();
                Boolean encryptMessage = encrypted.isSelected();
                if (hasName == false) {
                    if (verbose) System.out.println("Assigning Name => " + message);
                    myName = message;
                    writer.println(message);
                    writer.flush();
                    hasName = true;
                } else {
                    if (verbose) System.out.println("Sending message => " + message);
                    if (verbose) System.out.println("To recipient => " + username);

                    // Ecrypt Message Here
                    if (encryptMessage) message = "ENCRYPTED$" + encrypt(message);
                    else message = "NOTENCRYPTED$" + message;
                    writer.println(username + "." + myName + "." + message);
                    writer.flush();
                }
            }
            catch (Exception ex) {
                incoming.append("\n" + "Unable to Send Message" + "\n");
            }
            outgoing.setText("");
            recipient.setText("");
            encrypted.setSelected(false);
        }

        /*
         * Encrypts the string symmetrically using digits of Pi
         */
        public String encrypt(String message) {
            String encodedString = "";
            String encryptionDigits = "314159265358979323846264338327950288419716939937510582097494459230781640628620";
            for (int i = 0; i < message.length(); i++) {
                Integer index;
                if (i == 0 ) {
                    index = 0;
                } else {
                    index = 77 % i;
                }
                encodedString = encodedString + (char)((byte) message.charAt(i) - Integer.valueOf(encryptionDigits.substring(index, index+1)));
            }
            return encodedString;
        }
    }
    
    class IncomingReader implements Runnable {
    	/**
         * Gets the incoming messages from server
         */
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                	incoming.append(message + "\n");
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}


