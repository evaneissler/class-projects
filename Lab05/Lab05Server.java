package Lab05;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Lab05Server extends JFrame {

    public final boolean verbose = true;
    BufferedReader reader;
    Socket sock;
    public  TextArea  result = new TextArea(10,40);
    Label timeLabel = new Label("Date and Time ", Label.RIGHT);
    TextField timeField = new TextField("");
    Panel  displayTime = new Panel(new GridLayout(1,2));
    int year,month,day,hour,min,sec;
    String todayS, timeS, minS, secS;
    Calendar now;
    ArrayList<String> clientNames = new ArrayList<String>(100);
    ArrayList<PrintWriter> clientOutputStreams = new ArrayList<PrintWriter>(100);
    
    public static void main(String args[]) {
		System.out.println("Chat Service");
	    
	    Lab05Server server = new Lab05Server();
	    server.go();
	}
    
    /**
     * Builds server JFrame and configures window
     */
    public Lab05Server() {
        setLayout(new BorderLayout());
		setSize(600, 150);
        result.setBackground(Color.green);
        setBackground(Color.green);
		add(result);
    	setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        } 
        return null;  // should not get here
    }
     
    /**
     * Starts local server on port 5001 and IP 127.0.0.1
     * Starts and continuous loop to handle incoming socket requests
     * Prompts user for Name and displays
     */
    public void go() {
        try {
            ServerSocket serverSock = new ServerSocket(5001);
            if (verbose) System.out.println(" Server IP 127.0.0.1. ready at port 5000 " );
                if (verbose) System.out.println(" ___________________"
                    +"________________________________________________"  ); 
            result.append("Server started on " + processTime(2)+"\n");
            if (verbose) { System.out.println ("\nStart Server on " + processTime(2)); 
                timeField.setText(processTime(2));
            }   
            while(true) {
                Socket clientSocket = serverSock.accept();
                // Get the client's desired name
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String clientName = reader.readLine();
                clientNames.add(clientName);

                result.append(clientName + " just joined the server.\n");

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                writer.println("Welcome to the Chat Room " + clientName);
                writer.flush();
                Thread t = new Thread(new ClientHandler(clientSocket));
                clientOutputStreams.add(writer);
                t.start();  // to execute the run() method of ClientHandler
                if(verbose) System.out.println("got a connection");
            }
        } catch (Exception ex) { 
            System.out.println(ex);
            result.append("Unable to connect. Please try again.\n");
        }
    }
    
    /**
     * Takes string from recipient and sends corresponding message to recipient(s) with or without encryption
     * @param recipient String of who is going to recieve message
     * @param sender String name of sender of message
     * @param encrypted Boolean showing whether or not message has been encrypted
     * @param message String containing message itself
     */
    public void broadcast(String recipient, String sender, Boolean encrypted, String message) {
        Boolean decrypt = false;
        if (encrypted == true) decrypt = true;
        if (recipient.equals("null")) {
            result.append(message + " broadcasted on " + processTime(2)+ "\n");
            for (PrintWriter client : clientOutputStreams) {
                try {
                    PrintWriter writer = (PrintWriter) client;
                    if (decrypt) {
                        writer.println(sender + " said: \n" + decrypt(message));
                    } else {
                        writer.println(sender + " said: \n" + message);
                    }
                    writer.flush();
                } catch (Exception ex) { 
                    result.append("Unable to send message. \n"); 
                }
            }
        } else {
            try {
                PrintWriter receiver = clientOutputStreams.get(clientNames.indexOf(recipient));
                PrintWriter writer = (PrintWriter) receiver;
                if (decrypt) {
                    writer.println(sender + " said: \n" + decrypt(message));
                } else {
                    writer.println(sender + " said: \n" + message);
                }
                writer.flush();
            } catch (Exception e) {
                result.append("No client found with that name. Please try again.\n");
            }
        }
    }

    /**
     * Takes server output and writes to log file
     * @param result is recent server activity
     */
    public void buildServerLog(String result) {
        String filePath = "Lab05/ServerLog.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(result);
            bufferedWriter.close();
            System.out.println("Server Log Updated.");
        } catch (IOException e) {
            System.out.println("Unable to update server log");
        }
    }

    /**
     * When message is encrypted, this method is run to decrypt when broadcasting to the recipient
     * @param message The encrypted message shwoing on the sevrer
     * @return The decrypted message for the recipient to view
     */
    public String decrypt(String message) {
        String inputBytes = "";

        for (int i=0;i<message.length();i++) {
            inputBytes = inputBytes + (byte)message.charAt(i);
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
    
    /** Internal class to handle the Client Handler
    The benefit of using an internal class is that we have access to the various objects of the 
    external class
    
    */
     public class ClientHandler  implements Runnable { 
      /**
       * Constructor of the inner class
       * @param clientSocket
       */
       public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                if (verbose) System.out.println("new client " );
                if (verbose) System.out.println(" new client at address "
                  +  sock.getLocalAddress() + " at port " +  sock.getLocalPort()); 
                if (verbose) System.out.println(" Client at address " 
                    +  sock.getInetAddress() + " at port " +  sock.getPort()  );
                if (verbose) System.out.println(" ___________________"
                    +"________________________________________________"  ); 
            } catch (Exception ex) { 
                result.append("Unable to connect. Please try again.\n");
            }
        }
        /**
         * Handles incoming messages and requests from clients
         */
        public void run() {
            String input;
            try {
                while ((input = reader.readLine()) != null) {
                    StringTokenizer token = new StringTokenizer(input, ".$");
                    String recipient = token.nextToken();
                    String sender = token.nextToken();
                    String encryptToken = token.nextToken();
                    Boolean encrypted = false;
                    if (encryptToken.equals("ENCRYPTED")) encrypted = true;
                    String message = token.nextToken();
                    result.append("message received " + message + " on " + processTime(2)+"\n");
                    broadcast(recipient, sender, encrypted, message);
                    // Stores server logs
                    buildServerLog(result.getText());
                }
            } catch (Exception ex) { 
                System.out.println(ex);
                result.append("Unable to send message. Please try again.\n");
            }
        }
    }  
    
    
}

