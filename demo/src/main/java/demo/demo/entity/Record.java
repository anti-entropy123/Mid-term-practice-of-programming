package demo.demo.entity;

public class Record {
    private int id;
    private String date;
    private String status;

    public Record(){}
    public Record(int userId, String date, String status){
        this.id = userId;
        this.date = date;
        this.status = status;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getDate(){
        return this.date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
}