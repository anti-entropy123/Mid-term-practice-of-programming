package demo.demo.entity;

public class Message {
    private int userId;
    private int applicationId;
    
    public Message(){}
    public Message(int userId, int applicationId){
        this.userId = userId;
        this.applicationId = applicationId;
    }

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