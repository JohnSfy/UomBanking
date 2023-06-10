package model;

import javax.persistence.*;

@Entity
@Table(name = "Withdraw")
@DiscriminatorValue(value = "Withdraw")
public class Withdraw extends Transactions {

    public Withdraw() {
    }

    public Withdraw(String ID, String date, double amount,String clientusername, String description) {
        super(ID, date, amount,clientusername, description);
    }
}
