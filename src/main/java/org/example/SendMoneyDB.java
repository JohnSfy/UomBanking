package org.example;

import model.Account;
import model.Transactions;
import model.Transfer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class SendMoneyDB {

//  Fetch an Account
    public static boolean findAccountAndUpdateBalance(String IBAN, double amount){
//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database

//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Finding the recipient account
        String hql = "FROM Account WHERE IBAN = '" + IBAN + "'";
        Query query = session.createQuery(hql);

        List results = query.list();

        ArrayList<Account> account = new ArrayList<>();
        Account recipientAccount = new Account();
        boolean found = false;

        if(!results.isEmpty()){
            account.addAll(results);
//          Saving and closing session
            session.getTransaction().commit();
            session.close();
            for(Account acc: account){
                recipientAccount = acc;
                found = true;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
                LocalDateTime now = LocalDateTime.now();
                TransactionsDB.saveTransaction(new Transfer("", dtf.format(now), amount,  "Received " + amount + " from " +
                        recipientAccount.getClient(),recipientAccount.getClient(), recipientAccount.getIBAN()));
//              Updating recipient's balance
                recipientAccount.setBalance(recipientAccount.getBalance() + amount);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "IBAN not found",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }



        return found;
    }
}
