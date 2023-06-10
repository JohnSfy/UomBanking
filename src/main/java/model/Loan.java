package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Random;

@Entity
@Table(name = "Loan")
public class Loan {

    @Column(name = "LoanAmount", nullable = false, length = 20)
    private double loanAmount;

    @Column(name = "DateOfApply", nullable = false, length = 10)
    private String date;

    @Column(name = "Description", nullable = false, length = 50)
    private String description;

    @Column(name = "ExpirationDate", nullable = false, length = 20)
    private String dateExp; //δεν χρησιμοποιήθηκε κάπου με βάση το word

    @Column(name = "Doses", nullable = false, length = 20)
    private int doses;

    @Id
    @Column(name = "ID", nullable = false, length = 20)
    private String id;

    @Column(name = "AccountID", nullable = false, length = 50)
    private String accountID; // χρησιμοποιείται για να ξέρουμε ποιανού το loan είναι

    public Loan() {
    }

    public Loan(String accountID, double loanAmount, String description, String currDate, String dateExp, int doses, String id) {
        this.accountID = accountID;
        this.loanAmount = loanAmount;
        this.date = currDate;
        this.dateExp = dateExp;
        this.doses = doses;
        this.id = genID();
        this.description = description;
    }

    public double loanDose(double loanAmount, int doses) {
        double payableDose = 0;
        payableDose = loanAmount / doses;


        return payableDose;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void calculateNewLoanAmount(double loanAmaount, int doses, double balance, Account a1) {
        double pDose = loanDose(loanAmount, doses);
        double newLoanAmount;
        a1.setBalance(calculateNewBalanace(balance, pDose));
        newLoanAmount = loanAmaount - pDose;
        setLoanAmount(newLoanAmount);
        doses--;
        setDoses(doses);
    }

    public double calculateNewBalanace(double balance, double payableDose) {
        balance = balance - payableDose;  //ipologizo to kainourgio balance
        return balance;
    }

    public String genID() {
        //Δημιουργία ενός τυχαίου ID με 8 χαρακτήρες π.χ. kHFujh%4
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        int length = 8;
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            builder.append(characters.charAt(index));
        }
        return builder.toString();
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getDateExp() {
        return dateExp;
    }

    public void setDateExp(String dateExp) {
        this.dateExp = dateExp;
    }

    public int getDoses() {
        return doses;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public String toString() {
        return "To poso twn dosewn pou apomenei: " + doses;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}



