package gui;

import model.Account;

import javax.swing.*;
import java.awt.*;

public class PreviewTransferFrame {
    private JLabel header;
    private JLabel message;
    private JLabel message2;
    private JLabel balance;
    private JPanel innerPanel;
    private JButton returnToMainPageButton;
    private JButton receiptButton;
    JFrame successTransfer;

    public PreviewTransferFrame(Account account){
        successTransfer = new Template();

        //Initializing elements
        header = Utils.setHeader("Your money has been successfully transferred!");
        message = new JLabel("The recipient will receive the money soon");
        message2= new JLabel("Your new account balance");
        balance = new JLabel("1500$");
        innerPanel = new JPanel();
        receiptButton = new JButton("Download receipt");
        returnToMainPageButton = Utils.returnToMainPageButton(successTransfer);

        //Placing the elements
        header.setBounds(300, 100, 1000, 100);
        header.setFont(new Font("Courier", Font.PLAIN, 30));

        message.setBounds(400,150,800,80);
        message.setFont(new Font("Courier", Font.PLAIN, 20));

        innerPanel.setBounds(450, 275, 300, 200);
        innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        message2.setFont(new Font("Courier", Font.PLAIN, 25));

        balance.setFont(new Font("Courier", Font.PLAIN, 50));

        receiptButton.setBounds(380,550,200,40);

        returnToMainPageButton.setBounds(630,550,200,40);


        //Adding the elements
        innerPanel.add(message2);
        innerPanel.add(balance);
        successTransfer.add(header);
        successTransfer.add(message);
        successTransfer.add(innerPanel);
        successTransfer.add(receiptButton);
        successTransfer.add(returnToMainPageButton);


        successTransfer.setVisible(true);
        successTransfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        successTransfer.getContentPane().setBackground(Color.LIGHT_GRAY);
    }
}
