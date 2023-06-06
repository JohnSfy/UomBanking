package gui;

import model.Account;
import org.example.AccountDB;

import javax.swing.*;
import java.awt.*;

public class PreviewWithdrawFrame extends JFrame{

    private JLabel header;
    private JLabel message;
    private JLabel message2;
    private JLabel balance;
    private JPanel innerPanel;
    private JButton receiptButton;
    private JButton returnToMainPageButton;
    JFrame successWithdraw;
    public PreviewWithdrawFrame(Account account){
        successWithdraw = new Template();

        //Initializing components
        header = Utils.setHeader("Your money has been successfully withdrawn!");
        message = new JLabel("You have 10 days to receive the money from the bank");
        message2= new JLabel("Your new account balance");
        balance = new JLabel(String.valueOf(account.getBalance()) +"€");
        innerPanel = new JPanel();
        receiptButton = new JButton("Download receipt");
        returnToMainPageButton = Utils.returnToMainPageButton(successWithdraw, account);

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

//      Saving account's new Balance
        AccountDB.updateAccount(account);

        //Adding the elements
        innerPanel.add(message2);
        innerPanel.add(balance);
        successWithdraw.add(header);
        successWithdraw.add(message);
        successWithdraw.add(innerPanel);
        successWithdraw.add(receiptButton);
        successWithdraw.add(returnToMainPageButton);

        successWithdraw.setVisible(true);
        successWithdraw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
