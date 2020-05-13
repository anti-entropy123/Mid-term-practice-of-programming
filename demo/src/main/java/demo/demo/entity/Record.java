package demo.demo.entity;

public class Record {
    private String id;
    private String date;
    private String status;

    public String getId(){
        return this.id;
    }
    public void setId(String id){
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