/* Lab5 Control */
/* 10403-15 Spring 2023 */
/* Student name: <> */

import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Lab5Control extends Lab5View implements ListSelectionListener, ActionListener {
    private static final long serialVersionUID = 1L;

    public static void main(String args[]) {
        new Lab5Control();
    }
    
    public Lab5Control() {
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
                displayPlanet(planetListModel.getElementAt(planetList.getSelectedIndex()));
            }
        }
    }
    
    public void actionPerformed(ActionEvent e) 
    { 
        String whichButton = e.getActionCommand(); 
        System.out.println("Button for " + whichButton + " was pressed.");
        //Lab5Model.Planet myChosenPlanet = myModel.getPlanet(whichButton);
        displayPlanet(whichButton);
    }
 }
