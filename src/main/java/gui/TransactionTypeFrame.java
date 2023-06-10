package gui;

import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionTypeFrame extends JFrame {
    private JFrame frame;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton paymentButton;
    private JButton returnToMainPageButton;
    private JLabel header;
    private JLabel line;

    public TransactionTypeFrame(Account account) {

        frame = new Template();

        header = Utils.setHeader("Transaction Type");
        header.setBounds(400, 100, 1000, 100);

        line = new JLabel("________________________________________________________________________________________________________");
        line.setBounds(250, 150, 1000, 100);

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        paymentButton = new JButton("Payment");

        depositButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        withdrawButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        transferButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        paymentButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

        depositButton.setBounds(520, 250, 120, 30);
        withdrawButton.setBounds(520, 320, 120, 30);
        transferButton.setBounds(520, 390, 120, 30);
        paymentButton.setBounds(520, 460, 120, 30);

        returnToMainPageButton = Utils.returnToMainPageButton(frame, account);
        returnToMainPageButton.setBounds(480, 560, 200, 30);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new DepositFrame(account);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new WithdrawFrame(account);
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new TransferFrame(account);
            }
        });

        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new PaymentFrame(account);
            }
        });

        frame.add(header);
        frame.add(line);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(transferButton);
        frame.add(paymentButton);
        frame.add(returnToMainPageButton);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBackground(Color.LIGHT_GRAY);

    }
}

