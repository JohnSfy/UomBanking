package gui;

import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpendCategoriesFrame extends JFrame {

    //defining variables
    private JLabel header;

    private JLabel message;

    private JButton ReturnToTheMainPage;

    private JTable SCTable;



    public SpendCategoriesFrame(Account account){

        //setting template
        JFrame SpendCategories = new Template();

        //make a new JTable for our purchases
        String[] Columns = new String[]{"Transaction", "Amount"};
        String[][] SCInfo = new String[][]{{"Supermarket","190.70$"},{"Pharmacy","380.76$"},{"Transport","100$"}};
        SCTable = new JTable(SCInfo,Columns);

        //setting scrollpane to table
        JScrollPane jp = new JScrollPane(SCTable);
        jp.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        SCTable.setForeground(Color.BLACK);
        SCTable.setEnabled(false);
        jp.setBounds(440,280,350,130);

        //settings for header
        header = Utils.setHeader("Spend Categories");
        header.setBounds(440, 100, 800, 100);
        header.setFont(new Font("Courier",Font.PLAIN,40));

        //settings for message
        message = new JLabel("See what you spend in each category");
        message.setBounds(480,140,600,80);
        message.setFont(new Font("Courier",Font.PLAIN,15));

        //settings for "return to main page" button
        ReturnToTheMainPage = new JButton("Return to the main page");
        ReturnToTheMainPage = Utils.returnToMainPageButton(SpendCategories, account);
        ReturnToTheMainPage.setBounds(530,440,180,30);


        //putting all into template
        SpendCategories.add(header);
        SpendCategories.add(message);
        SpendCategories.add(ReturnToTheMainPage);
        SpendCategories.add(jp);
        SpendCategories.setVisible(true);
        SpendCategories.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpendCategories.getContentPane().setBackground(Color.LIGHT_GRAY);
    }

}