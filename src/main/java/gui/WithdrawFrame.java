package gui;

import jdk.jshell.execution.Util;
import model.Account;
import model.Deposit;
import model.Withdraw;
import org.example.TransactionsDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Double.parseDouble;

public class WithdrawFrame extends JFrame {
    JLabel header;
    JLabel line;
    JLabel amountLabel;
    JTextField amountField;
    JButton continueButton;
    JButton returnToMainPageButton;
    String amount;
    JFrame withdraw;
    public WithdrawFrame(Account account){
        withdraw = new Template();

        //Initializing elements;
        header = new JLabel("Withdraw");
        line = new JLabel("_____________________________________________________________________________________");
        amountLabel = new JLabel("Enter amount");
        amountField = new JTextField();
        continueButton = new JButton("Continue");
        returnToMainPageButton = Utils.returnToMainPageButton(withdraw, account);

        //Placing the elements
        header.setBounds(500, 100, 1000, 100);
        header.setFont(new Font("Tahoma", Font.PLAIN, 40));

        line.setBounds(325,150,500,100);

        amountLabel.setBounds(515,300,400,50);
        amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

        amountField.setBounds(500,370,150,25);

        continueButton.setFont(new Font("Tahoma",Font.PLAIN,15));
        continueButton.setBounds(525,550,100,30);

        returnToMainPageButton.setBounds(950,700,200,35);



        //ActionListener
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount = amountField.getText();
                if(isCorrect(amount) && checkAmount(amount, account.getBalance())){
//                  Setting up amountField
                    account.setBalance(account.getBalance() - parseDouble(amountField.getText()));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
                    LocalDateTime now = LocalDateTime.now();
                    TransactionsDB.saveTransaction(new Withdraw("", dtf.format(now), parseDouble(amount), "Withdraw", account.getClient()));

                    Withdraw withd = new Withdraw("", dtf.format(now), parseDouble(amount), "Withdraw", account.getClient());
                    withdraw.dispose();
                    new PreviewWithdrawFrame(account,withd);
                }
            }
        });

        //Adding the elements
        withdraw.add(header);
        withdraw.add(line);
        withdraw.add(amountLabel);
        withdraw.add(amountField);
        withdraw.add(continueButton);
        withdraw.add(returnToMainPageButton);

        withdraw.setVisible(true);
        withdraw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean isCorrect(String anAmount){
        boolean flag = true;
        if(anAmount.isBlank()) {
            JOptionPane.showMessageDialog(withdraw, "Please enter an amount!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            flag=false;
            return flag;
        }
        for(int i = 0; i < anAmount.length(); i++){
            if(!Character.isDigit(anAmount.charAt(i))){
                flag=false;
                JOptionPane.showMessageDialog(withdraw, "Please enter an amount!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        return flag;
    }

    public boolean checkAmount(String anAmount, double balance){
        double amount = Double.parseDouble(anAmount);
        boolean flag=true;
        if(amount>balance)
        {
            JOptionPane.showMessageDialog(withdraw, "Please enter a smaller amount!",
                    "Warning", JOptionPane.ERROR_MESSAGE);
            flag=false;
        }
        return flag;
    }
}
