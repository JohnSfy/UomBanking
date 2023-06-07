package gui;

import files.LoanHistory;
import model.Account;
import model.Loan;
import org.example.LoanDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoanFrame extends JFrame {

    //defining variables
    private JLabel header;

    private JLabel message;

    private JLabel message1;

    private JButton NewLoan;

    private JButton ReturnToTheMainPage;

    private JTable LoanTable;

    private JButton loanReceipt;



    public LoanFrame(Account account){

        //Setting template
        JFrame Loans = new Template();

        ArrayList<Loan> aLoan = LoanDB.fetchAllLoans(account);


        //creating new JTable to save our loans
        String[] LoansColumns = new String[]{"Amount", "Expiration Date"};
        String[][] LoansInfo = new String[aLoan.size()][2];

            int i=0;
            for(Loan loans:aLoan){
                LoansInfo[i][0] = String.valueOf(loans.getLoanAmount() +"€");
                LoansInfo[i][1] = String.valueOf(loans.getDateExp());
                i++;
            }
        LoanTable = new JTable(LoansInfo,LoansColumns);
        LoanTable.setForeground(Color.BLACK);
        LoanTable.setEnabled(false);

        //putting scrollpane in table
        JScrollPane jp = new JScrollPane(LoanTable);
        jp.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        jp.setBounds(440,360,300,130);

        //settings for header
        header = Utils.setHeader("Take loans with our bank system!");
        header.setBounds(320, 100, 800, 100);
        header.setFont(new Font("Courier",Font.PLAIN,35));

        //settings for message
        message = new JLabel("Manage and pay your loans fast and easy");
        message.setBounds(400,140,600,80);
        message.setFont(new Font("Courier",Font.PLAIN,20));

        //settings for message1
        message1 = new JLabel("Your loans");
        message1.setBounds(520,300,500,60);
        message1.setFont(new Font("Courier", Font.PLAIN,30));

        //settings for "return to main page" button
        ReturnToTheMainPage = new JButton("Return to the main page");
        ReturnToTheMainPage = Utils.returnToMainPageButton(Loans,account);
        ReturnToTheMainPage.setBounds(1000,710,180,30);

        //settings for loanReceipt button
        loanReceipt = new JButton("Download loan receipt");
        loanReceipt.setBounds(480,680,200,30);

        //settings for new loan button
        NewLoan = new JButton("New Loan");
        NewLoan.setBounds(500,580,150,30);

        loanReceipt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoanHistory(account,aLoan);
            }
        });


        //add actionlistener to new loan button
        NewLoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loans.dispose();
                new CreateLoanFrame(account);
            }
        });

        //putting all into template
        Loans.add(header);
        Loans.add(message);
        Loans.add(message1);
        Loans.add(ReturnToTheMainPage);
        Loans.add(NewLoan);
        Loans.add(loanReceipt);
        Loans.add(jp);
        Loans.setVisible(true);
        Loans.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Loans.getContentPane().setBackground(Color.LIGHT_GRAY);
    }
}
