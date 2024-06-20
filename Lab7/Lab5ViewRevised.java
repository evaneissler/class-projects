/* Lab5 View */
/* 10403-15 Spring 2023 */
/* Student name: Evan Eissler */
// Displays the windows and the pop-up when called in Control

import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;
import javax.swing.border.*;
import javax.sound.sampled.*;
import java.io.*;


public class Lab5ViewRevised extends JFrame {
    private static final long serialVersionUID = 1L;
    public Lab5ModelRevised myModel = new Lab5ModelRevised();
    
    JPanel startPanel, topPanel, sidePanel, centerPanel, bottomPanel;
    // for top panel
    JLabel planetSelectLabel; 
    JLabel headerLabel, frogLabel, footerLabel;
    ImageIcon tcuImage, frogImage;
    // for side panel
    JButton[] planetButtons; 
    // for center panel
    public JList<String> planetList;
    DefaultListModel<String> planetListModel;
    //JScrollPane planetScrollPane;
    
    // popUpFrame and other widgets to display planet info
    JFrame popUpFrame;
    JPanel imagePanel, infoPanel;
    JLabel planetImage, moonImage, pGravLabel, pSurfLabel, pAtmosphere, pRotationSpeed;
    JTextField pGrav, pSurfArea, pEnterWeight;
    JTextArea factsList;

    Color TCUColors = new Color(77,25,121);
    Color tanColor = new Color(152, 142, 200);
    Color darkColor = new Color(77, 25, 123);
    Font fontDisplayS = new Font("Helvetica", Font.BOLD, 14);
    Font fontDisplayM = new Font("Helvetica", Font.BOLD, 18);
    
    // main is not needed since we won't run the view independently
    //public static void main(String args[]) {
    //    new Lab5View();
    //}
    

    public Lab5ViewRevised() {
        displayMain();
    }

     public void displayMain() {
        // set up and displays the main window
        setTitle("Our solar system");
        //setSize(200, 200);
        setForeground(TCUColors);
        setLayout(new BorderLayout());
        // set up Top panel
        tcuImage = new ImageIcon("Labs/Lab7/images/tcu.png");
        frogImage = new ImageIcon("Labs/Lab7/images/tcu.gif");
        headerLabel = new JLabel(tcuImage);
        frogLabel = new JLabel(frogImage);
        topPanel = new JPanel(new FlowLayout());
        topPanel.add(headerLabel);
        topPanel.add(frogLabel);
        topPanel.setBackground(tanColor);
        add(topPanel, BorderLayout.NORTH);
        // set up bottom panel
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(tanColor);
        footerLabel = new JLabel("Enter your weight on Earth in pounds");
        pEnterWeight = new JTextField(10);
        bottomPanel.add(footerLabel);
        bottomPanel.add(pEnterWeight);
        // set up side panel
        sidePanel = new JPanel(new GridLayout(4, 2));
        sidePanel.setBackground(darkColor);
        planetButtons = new JButton[Lab5ModelRevised.totalPlanets];
        int i = 0;
        for (Lab5ModelRevised.Planet p : myModel.planets) {
            planetButtons[i] = new JButton(p.getName());
            planetButtons[i].setFocusPainted(false);
            planetButtons[i].setForeground(tanColor);
            sidePanel.add(planetButtons[i]);
            i++;
        }
        add(sidePanel, BorderLayout.WEST);

        planetListModel = new DefaultListModel<String>();
        // we get the list of planet names from myModel.planets
        for (Lab5ModelRevised.Planet p : myModel.planets)
        {
            System.out.println("Adding planet " + p.getName() + " to the planetListModel. ");
            planetListModel.addElement(p.getName());
        }
       
        planetList = new JList<String>(planetListModel);
        // show only 5 rows of the list
        planetList.setVisibleRowCount(5);
        // make user select only one option at a time.
        planetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        startPanel = new JPanel(new BorderLayout());
        planetSelectLabel = new JLabel("Select a planet", JLabel.CENTER);
        planetSelectLabel.setFont(fontDisplayM);
        startPanel.add(planetSelectLabel, BorderLayout.NORTH);
        startPanel.add(planetList, BorderLayout.CENTER);
        add(startPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //setSize(200, 200);
        setBounds(50, 50, 200, 200);
        startPanel.setBounds(0, 0, 200,200);
        startPanel.setBackground(TCUColors);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

   public void displayPlanet(String pName, double weight) 
    {   
        Lab5ModelRevised.Planet planet;
        if (popUpFrame != null) 
        {
            System.out.println("Disposing popUpFrame in View:deletePopup");
            popUpFrame.dispose();
        }

        popUpFrame = new JFrame(pName);
        popUpFrame.setLayout(new GridLayout(2, 1));
        popUpFrame.setSize(550, 430);
        // let's display it so as not to cover the startPanel
        popUpFrame.setBounds(350, 150, 550, 430);
        // get the planet associated with pName
        planet = myModel.getPlanet(pName);
        // panel inside popupFrame used to display the planet
        imagePanel = new JPanel(new FlowLayout());
        imagePanel.setForeground(TCUColors);
        imagePanel.setBackground(Color.white);
        imagePanel.setOpaque(true);

        // Set up text area

        factsList = new JTextArea();
        factsList.setSize(200, 200);
        factsList.setBounds(50, 50, 200, 200);
        factsList.setName(planet.name);

        // Play sounds
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(planet.getSound()).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        
        // factsList.setSize(100, 100);
        factsList.append("Planet Gravity: " + getDoubleNumber(planet.getGravity()) + "\n");
        factsList.append("Planet Surface Area: " + getDoubleNumber(planet.getSurfaceArea()) + "\n");
        factsList.append("Atmosphere Constitutents: " + planet.getAtmosphere() + "\n");
        factsList.append("Planet Rotation Speed: " + getDoubleNumber(planet.getRotationSpeed()) + "\n");
        factsList.append("Planet Discovered: " + planet.getDiscovered() + "\n");
        factsList.append("Planet Orbit in KM: " + getDoubleNumber(planet.getOrbit()) + "\n");
        factsList.append("Planet Orbit velocity in Km/hour: " + getDoubleNumber(planet.getOrbitVelocity()) + "\n");
        factsList.append("Planet Density in g/cm^3: " + getDoubleNumber(planet.getDensity()) + "\n");
        factsList.append("Planet Effective Temp Centigrade: " + planet.getTemp() + "\n");
        if (weight != 0) {
            factsList.append("You would weight " + String.format("%.2f",weight) + " pounds on " + planet.name);
        } 

        // info panel to display facts
        infoPanel = new JPanel(new FlowLayout());
        TitledBorder border = BorderFactory.createTitledBorder("Planet " + planet.name);
        border.setTitleJustification(TitledBorder.CENTER);
        infoPanel.setBorder(border);
        infoPanel.add(factsList);
        infoPanel.setAlignmentX(CENTER_ALIGNMENT);
        
        
        // we pull data off planet and display it in the view
        planetImage = new JLabel();
        planetImage.setIcon(planet.getImage());
        moonImage = new JLabel();
        moonImage.setIcon(planet.getMoonImage());
        // if hasMoon, we get and display the moon also

        pGravLabel = new JLabel(planet.name + " Gravity: " + getDoubleNumber(planet.getGravity()) + " of Earth's Gravity");
        pSurfLabel = new JLabel(planet.name + " Surface Area: " + getDoubleNumber(planet.getSurfaceArea()) + " Kilometers");
        pAtmosphere = new JLabel(planet.name + " Atmosphere Constituents: " + planet.getAtmosphere());
        pRotationSpeed = new JLabel(planet.name + " Rotation Speed Relative to Earth: " + getDoubleNumber(planet.getRotationSpeed()) + " Earth Days");

        
        // If the planet doesn't have a moon, the planet is displayed in the center, so it looks better
        // If it does have a planet, they are displayed side by side in WEST and EAST 
        if (planet.hasMoon) {
            imagePanel.add(planetImage);
            imagePanel.add(moonImage);
        }
        else {
            imagePanel.add(planetImage);
        }
        popUpFrame.add(imagePanel);
        popUpFrame.add(infoPanel);
        popUpFrame.setForeground(Color.BLUE);
        //popUpFrame.setBackground(Color.white);
        popUpFrame.setVisible(true);
    }

    public String getDoubleNumber(double num) {
        // converts double to string with correct decimal points
        // String.format("%.3f", planet.getGravity())
        DecimalFormat df = new DecimalFormat("###,###.###");
        DecimalFormat df2 = new DecimalFormat("###,###,###.###");
        if (num < 100) 
            return String.format("%.3f", num);
        if (num < 100000)
            return df.format(num) + "";
        else
            return df2.format(num) + "";
    }
}
