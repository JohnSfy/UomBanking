package org.example;

import model.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class CardDB {


    //  Save Card
    public static void saveCard(Card card){
//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database

//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Saving and closing session
        session.save(card);
        session.getTransaction().commit();
        session.close();
        System.out.println("Card saved");
    }

    //  Update Card
    public static void updateCard(Card card){

//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database

//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Saving and closing session
        session.saveOrUpdate(card);
        session.getTransaction().commit();
        session.close();
        System.out.println("Card updated");
    }

    //  Fetch Card
    public static Card fetchCard( String AccountID){
//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database
//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Fetching Card
        Card card = new Card();
        card = (Card) session.get(Card.class, AccountID);

//      Saving and closing session
        session.getTransaction().commit();
        session.close();
        return card;
    }

    //  Delete Card
    public static void deleteCard(String AccountID){

//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database

//      Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//      Deleting Card
        Card card = new Card();
        card = (Card) session.get(Card.class, AccountID);
        session.delete(card);

//      Saving and closing session
        session.getTransaction().commit();
        session.close();
        System.out.println("Card deleted");
    }
}
