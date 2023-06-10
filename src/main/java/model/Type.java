package model;

import jakarta.persistence.Entity;
import java.util.ArrayList;

public class Type {
    private int currType; //curerent type
    private String curIban; //current iban
    private ArrayList<ArrayList<String>> mainArray = new ArrayList<>(); 

    public Type(){

    }

    public Type(int currType, String curIban) {
        this.currType = currType;
        this.curIban = curIban;
    }

    public Type(int currType, String curIban,ArrayList<ArrayList<String>> mainArray) {
        this.currType = currType;
        this.curIban = curIban;
        this.mainArray = mainArray;
    }

    public long checkIban(long curIban) throws Exception{
        
        if(countDigit(curIban)==16)
                return curIban;
        else {
                throw new Exception("Wrong IBAN\nPlease try again!");
        }
    }

    public long countDigit(long number){
        int count=0;
        while(number!=0){
            number=number/10;
            count++;
        }
        return count;
    }

    public ArrayList<ArrayList<String>> saveData(int curType,long curIban){
        ArrayList<String> type = new ArrayList<>();
        ArrayList<String> iban = new ArrayList<>();
        try{
            iban.add(Long.toString(checkIban(curIban))); //elegxo to iban, efoson einai sosto to apothikevo sto string list 
            type.add(Integer.toString(curType));
        } catch(Exception e){
            System.out.println(e.getMessage());
            return mainArray;
        }
        
        mainArray.add(iban);
        mainArray.add(type);
        return mainArray;
    }

    public String toString(){
        return "Result: "+mainArray;
    }

    public int getCurrType() {
        return currType;
    }

    public void setCurrType(int currType) {
        this.currType = currType;
    }

    public String getCurIban() {
        return curIban;
    }

    public void setCurIban(String curIban) {
        this.curIban = curIban;
    }

    public ArrayList<ArrayList<String>> getMainArray() {
        return mainArray;
    }

    public void setMainArray(ArrayList<ArrayList<String>> mainArray) {
        this.mainArray = mainArray;
    }
}
