package gui;
import model.Account;
import model.Transactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionHistoryFrame extends JFrame {
    private JButton returnToMainPageButton;
    private JLabel header;
    private JLabel subheader;
    private JFrame frame;
    private JButton downloadTranButton;


    public TransactionHistoryFrame(Account account){

        frame = new Template();
        downloadTranButton= new JButton("Download Transaction History");
        header = Utils.setHeader("Transaction history");
        subheader = new JLabel("See what you spend in detail");

        Transactions[][] trans = new Transactions[5][5];
        String[] column={"Transaction","Amount","Date","ID","DESCRIPTION"};

        JTable jt = new JTable(trans,column);
        JScrollPane sc = new JScrollPane(jt);
        jt.setEnabled(false);
        sc.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        jt.setForeground(Color.BLACK);

        downloadTranButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        returnToMainPageButton = Utils.returnToMainPageButton(frame, account);
        returnToMainPageButton.setBounds(485,480,200,30);


        header.setBounds(400,100,1000,100);
        subheader.setBounds(500,140,1500,100);
        sc.setBounds(320,250,500,100);
        downloadTranButton.setBounds(470,400,230,30);

        frame.add(header);
        frame.add(subheader);
        frame.add(sc);
        frame.add(downloadTranButton);
        frame.add(returnToMainPageButton);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBackground(Color.LIGHT_GRAY);

    }

}
