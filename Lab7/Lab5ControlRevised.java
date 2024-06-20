/* Lab5 Control */
/* 10403-15 Spring 2023 */
/* Student name: Evan Eissler */
// Holds all the action listeners for the objects and the buttons, calls the methods required

import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Lab5ControlRevised extends Lab5ViewRevised implements ListSelectionListener, ActionListener {
    private static final long serialVersionUID = 1L;

    public static void main(String args[]) {
        new Lab5ControlRevised();
    }
    
    public Lab5ControlRevised() {
        // register a listener for the list
        planetList.addListSelectionListener(this);
        // register listener to button array
        int j;
        for (j=0; j < planetButtons.length; j++) {
            planetButtons[j].addActionListener(this);
        }

    }
    
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (planetList.getSelectedIndex() == -1) {
                System.out.println("nothing was selected. ");
            } else {
                // Selected something, print value
                System.out.println(planetListModel.getElementAt(planetList.getSelectedIndex()) + " was selected. ");
                displayPlanet(planetListModel.getElementAt(planetList.getSelectedIndex()), 0);
            }
        }
    }
    
    public void actionPerformed(ActionEvent e) 
    { 
        Lab5ModelRevised.Planet planet;
        String whichButton = e.getActionCommand(); 
        System.out.println("Button for " + whichButton + " was pressed.");
        
        try {
            final Double earthWeight = Double.valueOf(pEnterWeight.getText());
            planet = myModel.getPlanet(whichButton);
            final Double planetWeight = (earthWeight * Double.valueOf(getDoubleNumber(planet.getGravity())));
            System.out.print("Your weight would be: " + planetWeight);
            displayPlanet(whichButton, planetWeight);
        } catch(Exception error) {
            System.out.print("Number entered null or invalid");
            displayPlanet(whichButton, 0);
        };
        //Lab5Model.Planet myChosenPlanet = myModel.getPlanet(whichButton);
    }
 }
