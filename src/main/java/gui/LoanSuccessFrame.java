package gui;

import model.Account;
import model.Loan;
import org.example.AccountDB;
import org.example.LoanDB;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoanSuccessFrame extends JFrame{
    private JFrame frame = new Template();
    private JLabel header;
    private JLabel label1;
    private JLabel label2;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton returnToTheMainPageButton;


    public LoanSuccessFrame(Account account, double loanAmount, String des, String currentDate, int doses) {

        header = Utils.setHeader("Your loan was made successfully!");
        header.setBounds(300,100,900,50);

        String expirationDate = calcDate(currentDate, doses);
        label1 = new JLabel("Make sure to pay it off by "+expirationDate);
        label1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label1.setBounds(500, 110, 700, 100 );



        Loan aLoan = new Loan(account.getID(),loanAmount,des,currentDate,expirationDate,doses,"");
        LoanDB.saveLoan(aLoan);
        
        account.setBalance(account.getBalance() + loanAmount);
        AccountDB.updateAccount(account);

        label2 = new JLabel("Your loans");
        label2.setBounds(500, 250, 1000, 100 );
        label2.setFont(new Font("Tahoma", Font.PLAIN, 30));

        returnToTheMainPageButton = Utils.returnToMainPageButton(frame,account);
        returnToTheMainPageButton.setBounds(480,650,200,35);




        ArrayList<Loan> loan = LoanDB.fetchAllLoans(account);
        //πίνακας δανείων μαζί με ημερομηνία λήξης
        String rowData[][] = new String[loan.size()][2];
        String columnNames[] = { "Amount", "Expiration Date"};

        int i = 0;
        for(Loan loans: loan){
            rowData[i][0] = String.valueOf(loans.getLoanAmount() +"€");
            rowData[i][1] = String.valueOf(loans.getDateExp());
            i++;
        }


        table= new JTable(rowData, columnNames);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(270,350,600,100);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        table.setEnabled(false);


        frame.add(header);
        frame.add(label1);
        frame.add(label2);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(returnToTheMainPageButton);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //μέθοδος υπολογισμού ημερομηνίας τελευταίας δόσης δανείου
    public  String calcDate(String currentDate, int doses){
        int currentDay, currentMonth, currentYear;
        int expirationDay = 0;
        int expirationMonth = 0;
        int expirationYear = 0;

        currentDay = Integer.parseInt(currentDate.substring(0,2));
        currentMonth = Integer.parseInt(currentDate.substring(3,5));
        currentYear = Integer.parseInt(currentDate.substring(6,8));

        expirationMonth = currentMonth + doses;
        //υπολογισμός αν το έτος δεν αλλάζει
        if(expirationMonth>= 1 && expirationMonth<=12){
            expirationYear = currentYear;
            //expirationMonth όλοι οι μήνες με 31 μέρες για υπολογισμό expirationDay
            if((expirationMonth == 1 || expirationMonth == 3 || expirationMonth == 5 || expirationMonth == 7 || expirationMonth == 8 || expirationMonth == 10 || expirationMonth == 12) && (currentDay == 31)){
                expirationDay = 31;
            }
            //expirationMonth όλοι οι μήνες με 30 μέρες για υπολογισμό expirationDay
            else if((expirationMonth == 4 || expirationMonth == 6 || expirationMonth == 9 || expirationMonth == 11) && (currentDay == 31)){
                expirationDay = 1;
                expirationMonth++;
            }
            //υπολογισμός αν ο expirationMonth ειναι ο Φεβρουάριος και αν το έτος είναι δίσεκτο ή οχι για υπολογισμό expirationDay
            else if(expirationMonth == 2 && (currentDay == 29 || currentDay == 30 || currentDay ==31)){
                int year = currentYear + 2000;
                boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
                if(isLeapYear){
                    expirationDay = currentDay +(currentDay - 29);
                }
                else{
                    expirationDay = currentDay +(currentDay - 28);
                }
                expirationDay %= currentDay;
                expirationMonth++;
            }
            else{
                expirationDay = currentDay;
            }
        }

        //υπολογισμός αν το έτος αλλάζει
        else{
            expirationMonth = expirationMonth % 12;
            if((expirationMonth != 0) || (expirationMonth+12==12) ){
                expirationYear = currentYear+1;
                if((expirationMonth == 1 || expirationMonth == 3 || expirationMonth == 5 || expirationMonth == 7 || expirationMonth == 8 || expirationMonth == 10 || expirationMonth == 12) && (currentDay == 31)){
                    expirationDay = 31;
                }
                else if((expirationMonth == 4 || expirationMonth == 6 || expirationMonth == 9 || expirationMonth == 11) && (currentDay == 31)){
                    expirationDay = 1;
                    expirationMonth++;
                }
                else if(expirationMonth == 2 && (currentDay == 30 || currentDay ==31)){
                    int year = currentYear + 2000;
                    boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
                    if(isLeapYear){
                        expirationDay = currentDay +(currentDay - 29);
                    }
                    else{
                        expirationDay = currentDay +(currentDay - 29);
                    }
                    expirationDay %= currentDay;
                    expirationMonth++;
                }
                else{
                    expirationDay = currentDay;
                }
            }
        }

        //φορμάς expirationDate DD/MM/YY
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(Integer.toString(expirationDay)+"/"+Integer.toString(expirationMonth)+"/"+Integer.toString(expirationYear));
        String expirationDate = formatter.format(date);
        return expirationDate;
    }
}


