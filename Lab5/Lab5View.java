/* Lab5 View */
/* 10403-15 Spring 2023 */
/* Student name: <> */

import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class Lab5View extends JFrame {
    private static final long serialVersionUID = 1L;
    public Lab5Model myModel = new Lab5Model();
    
    JPanel startPanel, topPanel, sidePanel, centerPanel;
    // for top panel
    JLabel planetSelectLabel; 
    JLabel headerLabel, frogLabel;
    ImageIcon tcuImage, frogImage;
    // for side panel
    JButton[] planetButtons; 
    // for center panel
    public JList<String> planetList;
    DefaultListModel<String> planetListModel;
    //JScrollPane planetScrollPane;
    
    // popUpFrame and other widgets to display planet info
    JFrame popUpFrame;
    JPanel pDisplay;
    JLabel planetImage, pGravLabel, pSurfLabel;
    JTextField pGrav, pSurfArea;

    Color TCUColors = new Color(77,25,121);
    Color tanColor = new Color(152, 142, 200);
    Color darkColor = new Color(77, 25, 123);
    Font fontDisplayS = new Font("Helvetica", Font.BOLD, 14);
    Font fontDisplayM = new Font("Helvetica", Font.BOLD, 18);
    
    // main is not needed since we won't run the view independently
    //public static void main(String args[]) {
    //    new Lab5View();
    //}
    

    public Lab5View() {
        displayMain();
    }

     public void displayMain() {
        // set up and displays the main window
        setTitle("Our solar system");
        //setSize(200, 200);
        setForeground(TCUColors);
        setLayout(new BorderLayout());
        // set up Top panel
        tcuImage = new ImageIcon("images/tcu.png");
        frogImage = new ImageIcon("images/tcu.gif");
        headerLabel = new JLabel(tcuImage);
        frogLabel = new JLabel(frogImage);
        topPanel = new JPanel(new FlowLayout());
        topPanel.add(headerLabel);
        topPanel.add(frogLabel);
        topPanel.setBackground(tanColor);
        add(topPanel, BorderLayout.NORTH);
        // set up side panel
        sidePanel = new JPanel(new GridLayout(4, 1));
        sidePanel.setBackground(darkColor);
        planetButtons = new JButton[Lab5Model.totalPlanets];
        int i = 0;
        for (Lab5Model.Planet p : myModel.planets) {
            planetButtons[i] = new JButton(p.getName());
            planetButtons[i].setFocusPainted(false);
            planetButtons[i].setForeground(tanColor);
            sidePanel.add(planetButtons[i]);
            i++;
        }
         add(sidePanel, BorderLayout.WEST);

        planetListModel = new DefaultListModel<String>();
        // we get the list of planet names from myModel.planets
        for (Lab5Model.Planet p : myModel.planets)
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

        //setSize(200, 200);
        setBounds(50, 50, 200, 200);
        startPanel.setBounds(0, 0, 200,200);
        startPanel.setBackground(TCUColors);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

   public void displayPlanet(String pName) 
    {   
        Lab5Model.Planet planet;
        if (popUpFrame != null) 
        {
            System.out.println("Disposing popUpFrame in View:deletePopup");
            popUpFrame.dispose();
        }

        popUpFrame = new JFrame(pName);
        popUpFrame.setSize(400, 400);
        // let's display it so as not to cover the startPanel
        popUpFrame.setBounds(350, 150, 400, 400);
        // get the planet associated with pName
        planet = myModel.getPlanet(pName);
        // panel inside popupFrame used to display the planet
        pDisplay = new JPanel(new GridLayout(3,1,5,5));
        pDisplay.setForeground(TCUColors);
        pDisplay.setBackground(Color.white);
        pDisplay.setOpaque(true);
        // we pull data off planet and display it in the view
        planetImage = new JLabel(pName);
        planetImage.setIcon(planet.getImage());
        // if hasMoon, we get and display the moon also

        pGravLabel = new JLabel("Planet Gravity: " + getDoubleNumber(planet.getGravity()));
        pSurfLabel = new JLabel("Planet Surface Area: " + getDoubleNumber(planet.getSurfaceArea()));
        
        pDisplay.add(planetImage);
        pDisplay.add(pGravLabel);
        pDisplay.add(pSurfLabel);
        popUpFrame.add(pDisplay);
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
