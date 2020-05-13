package demo.demo.entity;

public class HolidayBalance {
    private String id;
    private String date;
    private String type;

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

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
}