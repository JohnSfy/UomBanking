package gui;

import model.Account;
import org.example.AccountDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                if(isCorrect(amount) && checkAmount(amount, account.getBalance())){
//                  Setting up amountField
                    account.setBalance(account.getBalance() - parseDouble(amountField.getText()));
                    trFrame.dispose();
                    new PreviewTransferFrame(account);
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