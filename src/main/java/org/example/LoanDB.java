package org.example;

import model.Account;
import model.Loan;
import model.Transactions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class LoanDB {
//    Save a Loan
    public static void saveLoan(Loan loan){
//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database

//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Saving and closing session
        session.save(loan);
        session.getTransaction().commit();
        session.close();
        System.out.println("Loan saved");
    }

    //  Update a Loan
    public static void updateLoan(Loan loan){

//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database

//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Saving and closing session
        session.saveOrUpdate(loan);
        session.getTransaction().commit();
        session.close();
        System.out.println("Loan updated");
    }

    //  Fetch an Account
    public static Loan fetchLoan(String ID){
//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database
//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Fetching the account
        Loan loan = new Loan();
        loan = (Loan) session.get(Loan.class, ID);

//      Saving and closing session
        session.getTransaction().commit();
        session.close();
        return loan;
    }


    //  Fetch expenses
    public static ArrayList<Loan> fetchAllLoans(Account account){
//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database
//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Fetching the transactions
        String sql = "FROM Loan WHERE AccountID = '" + account.getID() + "'";
        Query query = session.createQuery(sql);
        List results = query.list();

        ArrayList<Loan> transactions = new ArrayList<>();
        if(!results.isEmpty()){
            transactions.addAll(results);
        }
        else{
            transactions.add(new Loan("",0, "", "","",0,""));
        }

//      Saving and closing session
        session.getTransaction().commit();
        session.close();
        return transactions;
    }
}

