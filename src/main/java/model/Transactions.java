package model;

import jakarta.persistence.*;
import java.util.Random;

@Entity
@Table(name = "Transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TransactionType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "Transactions")
public class Transactions {

    @Id
    @Column(name = "ID", nullable = false, length = 50)
    private String ID;

    @Column(name = "Date", nullable = false, length = 50)
    private String date;

    @Column(name = "Amount", nullable = false, length = 50)
    private double amount;

    @Column(name = "Description", nullable = false, length = 50)
    private String description;

    @Column(name = "ClientUsername", nullable = false, length = 50)
    private String clientusername;                                     // χρησιμοποιείται για να ξέρουμε ποιανού το transaction είναι

    public Transactions() {

    }

    public Transactions(String ID, String date, double amount, String description, String clientusername) {
        this.ID = genID();
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.clientusername = clientusername;
    }

    public double calculateNewBalance(double balance, double amount){

        balance += amount;

        return balance ;
    }

    public String genID(){
        //Δημιουργία ενός τυχαίου ID με 8 χαρακτήρες π.χ. kHFujh%4
        String characters ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        int length = 8;
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            builder.append(characters.charAt(index));
        }
        return builder.toString();

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientusername() {
        return clientusername;
    }

    public void setClientusername(String clientusername) {
        this.clientusername = clientusername;
    }
}
