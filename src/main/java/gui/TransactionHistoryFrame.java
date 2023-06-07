package gui;
import files.TransHistoryPDF;
import model.Account;
import model.Transactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TransactionHistoryFrame extends JFrame {
    private JButton returnToMainPageButton;
    private JLabel header;
    private JLabel subheader;
    private JFrame frame;
    private JButton downloadTranButton;


    public TransactionHistoryFrame(Account account, ArrayList<Transactions> transactions){

        frame = new Template();
        downloadTranButton= new JButton("Download Transaction History");
        downloadTranButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        header = Utils.setHeader("Transaction history");
        subheader = new JLabel("See what you spend in detail");
        subheader.setFont(new Font("Tahoma", Font.PLAIN, 15));

        String[][] trans = new String[transactions.size()][5];
        String[] column={"Transaction","Amount","Date","ID","DESCRIPTION"};

        JTable jt = new JTable(trans,column);
        JScrollPane sc = new JScrollPane(jt);
        jt.setEnabled(false);
        sc.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        jt.setForeground(Color.BLACK);
        jt.setFont(new Font("Tahoma", Font.PLAIN, 13));

//      Appending the transaction history to the table
        int i = 0;
        for (Transactions tr : transactions) {
            trans[i][0] = tr.getDescription();
            trans[i][1] = String.valueOf(tr.getAmount());
            trans[i][2] = tr.getDate();
            trans[i][3] = tr.getID();
            trans[i][4] = tr.getDescription();
            i++;
        }

        downloadTranButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                new TransHistoryPDF(transactions);
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
