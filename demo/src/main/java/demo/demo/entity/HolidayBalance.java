package demo.demo.entity;

public class HolidayBalance {
    private int id;
    private int balance;
    private String type;

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getBalance(){
        return this.balance;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
}