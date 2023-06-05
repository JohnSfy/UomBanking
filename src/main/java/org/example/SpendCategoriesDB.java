package org.example;

import model.Account;
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

public class SpendCategoriesDB {

//  Fetch Spend Categories
    public static ArrayList<Transactions> fetchAllPayments(Account account){
//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database
//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Fetching the transactions
        tx = session.beginTransaction();

        String hql = "FROM Transactions WHERE TransactionType = 'Payment' AND ClientUsername = '" + account.getClient() + "'";
        Query query = session.createQuery(hql);

        List results = query.list();

        ArrayList<Transactions> transactions = new ArrayList<>();
        if(!results.isEmpty()){
            transactions.addAll(results);
        }
        else{
            transactions.add(new Transactions("", "", 0,"", ""));
        }

//      Saving and closing session
        session.getTransaction().commit();
        session.close();
        return transactions;
    }
}
