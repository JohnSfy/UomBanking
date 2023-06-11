package gui;

import model.Account;
import model.Transactions;
import model.Transfer;
import model.Withdraw;
import org.example.AccountDB;
import org.example.TransactionsDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Double.parseDouble;

public class TransferFrame extends JFrame {
    private JFrame trFrame;
    private JLabel header;
    private JLabel ibanLabel;
    private JLabel amountLabel;
    private JTextField ibanField;
    private JTextField amountField;
    private JButton continueButton;
    private JButton returnToMainPageButton;
    private JLabel line;
    private String amountCheck;
    public TransferFrame(Account account){

        trFrame = new Template();
        this.setLayout(null);
        ibanField = new JTextField();
        amountField = new JTextField();
        continueButton = new JButton("Continue");
        returnToMainPageButton = new JButton("Return to the main page");

        header = Utils.setHeader("Transfer money to others");
        header.setBounds(400,100,1000,100);

        line = new JLabel("____________________________________________________________________________________________________________________________");
        line.setBounds(250,150,1000,100);

        ibanLabel = new JLabel("IBAN");
        ibanLabel.setBounds(500, 300,50,50);
        ibanField.setBounds(535,315,200,25);

        amountLabel = new JLabel("Amount");
        amountLabel.setBounds(485,355,50,50);
        amountField.setBounds(535,370,200,25);


        continueButton.setBounds(570,470,120,30);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String amount = amountField.getText();
                String IBAN = ibanField.getText();

                //Checking if characters after the 2 first Letters of IBAN are numbers
                boolean fl=true;
                for(int i=2;i<IBAN.length();i++){
                    if(Character.isLetter(IBAN.charAt(i))) fl=false;
                }

                if(IBAN.length()>=2 && IBAN.length()<=34 && IBAN.charAt(0)=='G' && Character.isLetter(IBAN.charAt(1))){
                    amountCheck = AccountDB.fetchAccount(account.getClient()).getCostPerTransaction();
                    if (Integer.parseInt(amountCheck) >= Integer.parseInt(amount)) {
                        if (isCorrect(amount) && checkAmount(amount, account.getBalance())) {
//                  Setting up amountField
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
                            LocalDateTime now = LocalDateTime.now();
                            TransactionsDB.saveTransaction(new Transfer("", dtf.format(now), parseDouble(amount), "Transfer to " + ibanField.getText() + ", ", account.getClient(), ibanField.getText()));
                            Transactions trans = new Transfer("", dtf.format(now), parseDouble(amount), "Transfer to " + ibanField.getText() + ", ", account.getClient(), ibanField.getText());
                            account.setBalance(account.getBalance() - parseDouble(amountField.getText()));
                            trFrame.dispose();
                            new PreviewTransferFrame(account, trans);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(continueButton, "Τhe money transfer cannot be carried out. Your limit per transaction is "+ amountCheck +"€.Please give a smaller amount",
                                "Amount Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
                else {
                    JOptionPane.showMessageDialog(continueButton, "Τhe IBAN is invalid",
                            "IBAN Error", JOptionPane.ERROR_MESSAGE);

                }


            }
        });

        returnToMainPageButton = Utils.returnToMainPageButton(trFrame, account);
        returnToMainPageButton.setBounds(970,710,200,30);

        trFrame.add(header);
        trFrame.add(line);
        trFrame.add(ibanLabel);
        trFrame.add(ibanField);
        trFrame.add(amountLabel);
        trFrame.add(amountField);
        trFrame.add(continueButton);
        trFrame.add(returnToMainPageButton);

        trFrame.setVisible(true);
        trFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        trFrame.setBackground(Color.LIGHT_GRAY);
    }

    public boolean isCorrect(String anAmount){
        boolean flag = true;
        if(anAmount.isBlank()) {
            JOptionPane.showMessageDialog(trFrame, "Please enter an amount!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            flag=false;
            return flag;
        }
        for(int i = 0; i < anAmount.length(); i++){
            if(!Character.isDigit(anAmount.charAt(i))){
                flag=false;
                JOptionPane.showMessageDialog(trFrame, "Please enter an amount!",
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
            JOptionPane.showMessageDialog(trFrame, "Please enter a smaller amount!",
                    "Warning", JOptionPane.ERROR_MESSAGE);
            flag=false;
        }
        return flag;
    }
}