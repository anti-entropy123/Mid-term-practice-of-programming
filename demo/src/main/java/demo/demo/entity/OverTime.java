package demo.demo.entity;

public class OverTime {
    private int userId;
    private String date;

    public OverTime(){}
    public OverTime(int userId, String date){
        this.userId = userId;
        this.date = date;
    }

    public int getUserId(){
        return this.userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getDate(){
        return this.date;
    }
    public void setDate(String date){
        this.date = date;
    }
}