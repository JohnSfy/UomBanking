package org.example;

import model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;

public class AccountDB {

//  Save an Account
    public void saveAccount(Account account){
//      Setting up the transaction between the app and the database
        AnnotationConfiguration configuration = new AnnotationConfiguration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database
        
//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Saving and closing session
        session.save(account);
        session.getTransaction().commit();
        session.close();
        System.out.println("Account saved");
    }

//  Update an Account
    public void updateAccount(Account account){

//      Setting up the transaction between the app and the database
        AnnotationConfiguration configuration = new AnnotationConfiguration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database

//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Saving and closing session
        session.saveOrUpdate(account);
        session.getTransaction().commit();
        session.close();
        System.out.println("Account updated");
    }

//  Fetch an Account
    public Account fetchAccount(int ID){
//      Setting up the transaction between the app and the database
        AnnotationConfiguration configuration = new AnnotationConfiguration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database
//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Fetching the account
        Account account = new Account();
        account = (Account) session.get(Account.class, ID);

//      Saving and closing session
        session.getTransaction().commit();
        session.close();
        return account;
    }
}
