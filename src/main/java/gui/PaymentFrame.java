package gui;

import model.Account;
import model.Deposit;
import model.Payment;
import org.example.TransactionsDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class PaymentFrame extends JFrame {
    private JLabel header;
    private JLabel message;
    private JLabel amountLabel;
    private JTextField amountField;
    private JLabel expensesLabel;
    private JButton submitButton;
    private JButton returnToMainPageButton;
    private String[] expenses = {"Groceries","Health","Transport","Other"};
    JFrame payment;
    private String anAmount;
    public PaymentFrame(Account account){
        payment = new Template();
        JComboBox<String> cb = new JComboBox<String>(expenses);
        cb.setEditable(true);

        //Initializing elements
        header = Utils.setHeader("Make your own expenses with our app");
        message = new JLabel("Shops, Pharmacies and more!");
        amountLabel = new JLabel("Amount");
        amountField = new JTextField();
        expensesLabel = new JLabel("Type of expense");
        submitButton = new JButton("Submit");
        returnToMainPageButton = Utils.returnToMainPageButton(payment, account);

        //Placing the elements
        header.setBounds(325, 100, 1000, 100);
        header.setFont(new Font("Courier", Font.PLAIN, 30));

        message.setBounds(450,150,800,80);
        message.setFont(new Font("Courier", Font.PLAIN, 20));


        amountLabel.setBounds(455,350,50,50);
        amountField.setBounds(500,360,200,35);

        expensesLabel.setBounds(400,420,100,50);
        cb.setBounds(500,430,150,35);

        submitButton.setBounds(570,600,80,35);
        returnToMainPageButton.setBounds(950,700,200,35);

        //Action Listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               anAmount=amountField.getText();
               if(isCorrect(anAmount) && checkAmount(anAmount,account.getBalance())){
//                 Setting up amountField
                   account.setBalance(account.getBalance() - parseDouble(amountField.getText()));
                   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
                   LocalDateTime now = LocalDateTime.now();
                   TransactionsDB.saveTransaction(new Payment("", dtf.format(now), parseDouble(anAmount), "Paid for " + cb.getSelectedItem().toString(), account.getClient(), cb.getSelectedItem().toString()));
                   Payment pay = new Payment("", dtf.format(now), parseDouble(anAmount), "Paid for " + cb.getSelectedItem().toString(), account.getClient(),  cb.getSelectedItem().toString());
                   payment.dispose();
                   new PreviewPaymentFrame(account,pay);
               }

            }
        });

        //Adding the elements
        payment.add(header);
        payment.add(message);
        payment.add(amountLabel);
        payment.add(amountField);
        payment.add(expensesLabel);
        payment.add(cb);
        payment.add(submitButton);
        payment.add(returnToMainPageButton);

        payment.setVisible(true);
        payment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public boolean isCorrect(String amount){
        boolean flag = true;
        if(amount.isBlank()) {
            JOptionPane.showMessageDialog(payment, "Please enter an amount!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            flag=false;
            return flag;
        }
        for(int i=0;i<anAmount.length();i++){
            if(!Character.isDigit(anAmount.charAt(i))){
                flag=false;
                JOptionPane.showMessageDialog(payment, "Please enter an amount!",
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
            JOptionPane.showMessageDialog(payment, "Please enter a smaller amount!",
                    "Warning", JOptionPane.ERROR_MESSAGE);
            flag=false;
        }

        return flag;

    }

}

