package gui;

import model.Account;
import model.Deposit;
import model.Loan;
import model.Transactions;
import org.example.LoanDB;
import org.example.TransactionsDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class PayLoanFrame extends JFrame {

    JLabel header;
    JLabel line;
    JLabel amountLabel;
    JTextField amountField;
    JButton continueButton;
    JButton returnToMainPageButton;
    String amount;
    JFrame payLoan;

    public PayLoanFrame(Account account, Loan loan) {

        payLoan = new Template();

        //Initializing elements;
        header = new JLabel("Pay Loan");
        line = new JLabel("_____________________________________________________________________________________");
        amountLabel = new JLabel("Enter amount");
        amountField = new JTextField();
        continueButton = new JButton("Continue");
        returnToMainPageButton = Utils.returnToMainPageButton(payLoan, account);

        //Placing the elements
        header.setBounds(500, 100, 1000, 100);
        header.setFont(new Font("Courier", Font.PLAIN, 40));

        line.setBounds(325, 150, 500, 100);

        amountLabel.setBounds(515, 300, 400, 50);
//        amountLabel.setFont(new Font("Courier", Font.PLAIN, 20));

        amountField.setBounds(500, 370, 150, 25);

        continueButton.setBounds(525, 550, 100, 30);

        returnToMainPageButton.setBounds(950, 700, 200, 35);



        //ActionListener
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount = amountField.getText();
                if (isCorrect(amount) && isAmountValid(parseInt(amount), loan.getLoanAmount())) {
                    account.setBalance(account.getBalance() - parseDouble(amountField.getText()));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
                    LocalDateTime now = LocalDateTime.now();
                    loan.setLoanAmount(loan.getLoanAmount() - parseInt(amount));
                    LoanDB.updateLoan(loan);
                    payLoan.dispose();
                    new LoanPaid(account, loan);
                }
            }
        });

        //Adding the elements
        payLoan.add(header);
        payLoan.add(line);
        payLoan.add(amountLabel);
        payLoan.add(amountField);
        payLoan.add(continueButton);
        payLoan.add(returnToMainPageButton);

        payLoan.setVisible(true);
        payLoan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public boolean isCorrect(String anAmount) {
        boolean flag = true;
        if (anAmount.isBlank()) {
            JOptionPane.showMessageDialog(payLoan, "Please enter an amount!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            flag = false;
            return flag;
        }
        for (int i = 0; i < anAmount.length(); i++) {
            if (!Character.isDigit(anAmount.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean isAmountValid(int amount, double loanAmount) {
        boolean flag = true;
        if (amount > loanAmount) {
            JOptionPane.showMessageDialog(payLoan, "You can't pay more than the loan amount!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            flag = false;
        }
        return flag;
    }
}

