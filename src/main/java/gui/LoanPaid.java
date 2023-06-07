package gui;

import files.LoanHistory;
import files.TransReceipt;
import model.Account;
import model.Deposit;
import model.Loan;
import org.example.AccountDB;
import org.example.LoanDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanPaid {

    private JPanel innerPanel = new JPanel();
    private JLabel header;
    private JLabel message;
    private JLabel message2;
    private JLabel balance;
    private JButton returnToMainPageButton;
    JFrame loanPaid;
    private JButton receiptButton;
    public LoanPaid(Account account, Loan loan){

        loanPaid = new Template();

        //Initializing elements
        header = Utils.setHeader("Your loan has been successfully paid!");
        message = new JLabel("You have 10 days to deliver the money to the bank");
        message2= new JLabel("Your Loans");
        balance = new JLabel(String.valueOf(loan.getLoanAmount())+"€");
        innerPanel = new LoanPanel(account);
        returnToMainPageButton = Utils.returnToMainPageButton(loanPaid, account);
        receiptButton = new JButton("Download Loan History");


        //Placing the elements
        header.setBounds(380, 100, 1000, 100);
        header.setFont(new Font("Tahoma", Font.PLAIN, 30));

        message.setBounds(400,150,800,80);
        message.setFont(new Font("Tahoma", Font.PLAIN, 20));

        innerPanel.setBounds(450, 275, 250, 250);
        innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        message2.setFont(new Font("Tahoma", Font.PLAIN, 25));

        balance.setFont(new Font("Tahoma", Font.PLAIN, 50));
        receiptButton.setBounds(380,550,200,40);

        receiptButton.setFont(new Font("Tahoma", Font.PLAIN, 15));


        receiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                new LoanHistory(account, LoanDB.fetchAllLoans(account));
            }
        });

        returnToMainPageButton.setBounds(630,550,200,40);

        //Adding the elements
        loanPaid.add(header);
        loanPaid.add(message);
        loanPaid.add(innerPanel);
        loanPaid.add(receiptButton);
        loanPaid.add(returnToMainPageButton);

        loanPaid.setVisible(true);
        loanPaid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
