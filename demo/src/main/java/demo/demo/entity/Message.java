package demo.demo.entity;

public class Message {
    private int userId;
    private int applicationId;
    
    public int getUserId(){
        return this.userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getApplicationId(){
        return this.applicationId;
    }
    public void setApplicationId(int applicationId){
        this.applicationId = applicationId;
    }
}