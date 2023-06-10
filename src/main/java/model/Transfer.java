package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRANSFER")
public class Transfer extends Transactions {

    @Column(name = "IBAN", nullable = false, length = 50)
    private String IBAN;

//  Public no arg constructor is needed for hibernate to work properly!
    public Transfer() {
    }

    public Transfer(String ID, String date, double amount,String clientusername, String description, String IBAN) {
        super(ID, date, amount,clientusername, description);
        this.IBAN = IBAN;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}
