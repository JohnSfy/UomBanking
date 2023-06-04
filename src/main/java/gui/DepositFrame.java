package gui;

import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Double.parseDouble;

public class  DepositFrame extends JFrame {
    JLabel header;
    JLabel line;
    JLabel amountLabel;
    JTextField amountField;
    JButton continueButton;
    JButton returnToMainPageButton;
    String amount;
    JFrame deposit;
    public DepositFrame(Account account){

        deposit = new Template();

        //Initializing elements;
        header = new JLabel("Deposit");
        line = new JLabel("_____________________________________________________________________________________");
        amountLabel = new JLabel("Enter amount");
        amountField = new JTextField();
        continueButton = new JButton("Continue");
        returnToMainPageButton = Utils.returnToMainPageButton(deposit);

        //Placing the elements
        header.setBounds(500, 100, 1000, 100);
        header.setFont(new Font("Courier", Font.PLAIN, 40));

        line.setBounds(325,150,500,100);

        amountLabel.setBounds(515,300,400,50);
        amountLabel.setFont(new Font("Courier", Font.PLAIN, 20));

        amountField.setBounds(500,370,150,25);

//      Setting up amountField
        account.setBalance(account.getBalance() + parseDouble(amountField.getText()));

        continueButton.setBounds(525,550,100,30);

        returnToMainPageButton.setBounds(950,700,200,35);

        //ActionListener
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount = amountField.getText();
                if(isCorrect(amount)){
                    deposit.dispose();
                    new PreviewDepositFrame(account);

                }
            }
        });

        //Adding the elements
        deposit.add(header);
        deposit.add(line);
        deposit.add(amountLabel);
        deposit.add(amountField);
        deposit.add(continueButton);
        deposit.add(returnToMainPageButton);


        deposit.setVisible(true);
        deposit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public boolean isCorrect(String anAmount){
        boolean flag = true;
        if(anAmount.isBlank()) {
            JOptionPane.showMessageDialog(deposit, "Please enter an amount!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            flag=false;
            return flag;
        }
        for(int i=0;i<anAmount.length();i++){
            if(!Character.isDigit(anAmount.charAt(i))){
                flag=false;
                break;
            }
        }
        return flag;
    }


}
