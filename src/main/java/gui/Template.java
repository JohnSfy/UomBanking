package gui;

import javax.swing.*;
import java.awt.*;

public class Template extends JFrame {

//  Properties
    private JLabel logo;
    private JLabel name;
    private JLabel accountIcon;
    private JLabel clientName;

    public Template() {
//      Setting layout manager to null for absolute positioning
        setLayout(null);

//      Initializing components
        logo = new JLabel("Logo");
        name = new JLabel("UOMSystemX");
        accountIcon = new JLabel("Account");
        clientName = new JLabel();

//      Setting up JLabel logo
        logo.setBounds(0, 0, 150, 30);
        logo.setSize(100, 100);

        logo.setIcon(Utils.setLabelIcon("src/main/java/images/UOM_LOGO_3.png", logo));

//      Setting up JLabel accountIcon
        accountIcon.setBounds(1130, 10, 150, 30);
        accountIcon.setSize(50, 50);

        accountIcon.setIcon(Utils.setLabelIcon("src/main/java/images/UserIcon.png", accountIcon));

//      Setting up JLabel name
        name.setBounds(10, 730, 250, 30);
        name.setFont(new Font("Courier", Font.PLAIN, 25));

//      Setting up JLabel clientName
        clientName.setBounds(1115, 65, 150, 30);
        clientName.setFont(new Font("Courier", Font.PLAIN, 15));



//      Adding components to the frame
        add(logo);
        add(name);
        add(accountIcon);
        add(clientName);

//      Basic setup for the frame
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(1200, 800));
        setSize(1200, 800);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        validate(); //validates the images


    }
}
