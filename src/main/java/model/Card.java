package model;
import  javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "Card")
public class Card{

    @Id
    @Column(name = "CardNumber", nullable = false, length = 20)
    private long cardNumber;

    @Column(name = "AccountID", nullable = false, length = 20)
    private String accountID;

    @Column(name = "ExpirationDate", nullable = false, length = 10)
    private String expirationDate;

    @Column(name = "CVV", nullable = false, length = 3)
    private int cvv;

    @Column(name = "Type", nullable = false, length = 15)
    private String type;

    @Column(name = "Color", nullable = false, length = 20)
    private String color;

    @Column(name = "CardName", nullable = false, length = 20)
    private String cardName;


    public Card(){

    }
    
    public Card(String accountID, long cardNumber, String dateExp, int cvv, String type, String color,String cardName) {
        this.accountID = accountID;
        this.cardNumber = genNum();
        this.expirationDate = genDateExp();
        this.cvv = genCVV();
        this.type = type;
        this.color = color;
        this.cardName = cardName;
    }

    

    public long genNum() {
        long smallest = 1000_0000_0000_0000L;
        long biggest =  9999_9999_9999_9999L;

        // return a long between smallest and biggest (+1 to include biggest as well with the upper bound)
         long random = ThreadLocalRandom.current().nextLong(smallest, biggest+1);
        return random;
    }

    public int genCVV(){
        int smallest = 100;
        int biggest =  999;

        // return a long between smallest and biggest (+1 to include biggest as well with the upper bound)
        return (int) ThreadLocalRandom.current().nextLong(smallest, biggest+1);
    }

    public String genDateExp(){
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");  
        Date date = new Date(); 
        int currentYear=Integer.parseInt(formatYear.format(date));  
        int expYear;
        expYear= currentYear+5;
        String expDate=Integer.toString(expYear);
        expDate=formatMonth.format(date)+"/"+expDate;
        return expDate;
    }


    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    
    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "This is the number of your Card: "+cardNumber+"\n"+"This is you CVV: "+cvv+"\n"+"Your Expiration Date is: "+genDateExp();
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
