//////////////////////////////////////////////////

// Program Name: Lab4

// Evan Eissler

// Class-Section: CoSc10403-015 
// Semester:     Spring 2023

// Program Overview:
// Displays total cost of selected food items

// Input:
// Desired ingredients and sizes

// Output:
// Total cost

/////////////////////////////////////////////////


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Lab4 extends JFrame implements ActionListener{ 

    // Declaring variables 
    public JPanel titlePanel, sizePanel, ingredientsPanel, actionButtonsPanel;
    public JLabel myTitle;
    public JRadioButton small, medium, large;
    public ButtonGroup myButtonGroup;
    public JCheckBox lettuce, kale, tomato, onion, bacon, egg, cheese, candied;
    public JButton buildSalad, reset;
    public JTextField totals;

    public Lab4(){
        // Editing frame
        setTitle("My Salad Store");
        setBounds(10,10,400,400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Editing title panel
        myTitle = new JLabel("Delicious Fresh Salad - Order Here!");
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(myTitle);
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setVisible(true);
        // Editing size panel
        small = new JRadioButton("Small");
        small.setActionCommand("small");
        medium = new JRadioButton("Medium");
        medium.setActionCommand("medium");
        large = new JRadioButton("Large");
        large.setActionCommand("large");
        

        myButtonGroup = new ButtonGroup();

        myButtonGroup.add(small);
        myButtonGroup.add(medium);
        myButtonGroup.add(large);

        sizePanel = new JPanel(new GridLayout(3,1));
        sizePanel.add(small);
        sizePanel.add(medium);
        sizePanel.add(large);
        sizePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size"));
        // Editing ingredients panel
        lettuce = new JCheckBox("Lettuce");
        kale = new JCheckBox("Kale");
        tomato = new JCheckBox("Tomato");
        onion = new JCheckBox("Onion");
        bacon = new JCheckBox("Bacon");
        egg = new JCheckBox("Egg");
        cheese = new JCheckBox("Cheese");
        candied = new JCheckBox("Candied");

        ingredientsPanel = new JPanel(new GridLayout(3,3));
        ingredientsPanel.add(lettuce);
        ingredientsPanel.add(kale);
        ingredientsPanel.add(tomato);
        ingredientsPanel.add(onion);
        ingredientsPanel.add(bacon);
        ingredientsPanel.add(egg);
        ingredientsPanel.add(cheese);
        ingredientsPanel.add(candied);
        ingredientsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        ingredientsPanel.setBorder(BorderFactory.createTitledBorder("Ingredients"));
        // Editing action buttons panel
        buildSalad = new JButton("Build my Salad!");
        buildSalad.addActionListener(this);
        reset = new JButton("Reset");
        reset.addActionListener(this);
        totals = new JTextField("totals");

        reset.setForeground(Color.red);
        buildSalad.setForeground(Color.blue);
        totals.setColumns(10);

        actionButtonsPanel = new JPanel(new FlowLayout());
        actionButtonsPanel.add(buildSalad);
        actionButtonsPanel.add(totals);
        actionButtonsPanel.add(reset);
        // Adding all panels to frame
        add(titlePanel, BorderLayout.NORTH);
        add(sizePanel, BorderLayout.WEST);
        add(ingredientsPanel, BorderLayout.CENTER);
        add(actionButtonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    public static void main(String[] args) {
        new Lab4();
    }

    public void actionPerformed(ActionEvent e){
        String whichButton = e.getActionCommand(); // determines which button generated the event
        // Calls the desired method based on which button was clicked
        if (whichButton.equals("Build my Salad!"))
            processBuildButton();
        if (whichButton.equals("Reset"))
            processResetButton();
        validate();
        repaint();
    }

    public void processBuildButton() {
        // Holds total value as each ingredient and size is checked 
        Double totalCost = 0d;
        // If there is no size selected, prompt user to select size
        if (myButtonGroup.getSelection() == null){
            totals.setText("Select size!");
        }
        // If a size is selected, then procedd to add size and ingredients to total.
        else {
            // Small option selected
            if (myButtonGroup.getSelection().getActionCommand() == "small") {
                totalCost = totalCost + 4.00;
            }
            // Medium option selected
            else if (myButtonGroup.getSelection().getActionCommand() == "medium") {
                totalCost = totalCost + 6.00;
            }
            // Large option selected
            else if (myButtonGroup.getSelection().getActionCommand() == "large") {
                totalCost = totalCost + 8.00;
            }
            // Looks at each ingredient to see if selected and adds 50c to total
            if (lettuce.isSelected() == true) {
                totalCost = totalCost + 0.50;
            }if (kale.isSelected() == true) {
                totalCost = totalCost + 0.50;
            }if (tomato.isSelected() == true) {
                totalCost = totalCost + 0.50;
            }if (onion.isSelected() == true) {
                totalCost = totalCost + 0.50;
            }if (bacon.isSelected() == true) {
                totalCost = totalCost + 0.50;
            }if (egg.isSelected() == true) {
                totalCost = totalCost + 0.50;
            }if (cheese.isSelected() == true) {
                totalCost = totalCost + 0.50;
            }if (candied.isSelected() == true) {
                totalCost = totalCost + 0.50;
            }
            // Displays total in the JTextField
            // Dollar sign is added and a 0 is added to end because printing only shows to one decimal place
            totals.setText("$" + String.valueOf(totalCost) + "0");
        }
    }

    public void processResetButton() {
        // Clears all selected and button group
        myButtonGroup.clearSelection();;
        lettuce.setSelected(false);
        kale.setSelected(false);
        tomato.setSelected(false);
        onion.setSelected(false);
        bacon.setSelected(false);
        egg.setSelected(false);
        cheese.setSelected(false);
        candied.setSelected(false);
        // Sets totals text back to "totals"
        totals.setText("totals");
    }
}

