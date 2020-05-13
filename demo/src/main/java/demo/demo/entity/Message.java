package demo.demo.entity;

public class Message {
    private String userId;
    private String applicationId;
    
    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getApplicationId(){
        return this.applicationId;
    }
    public void setApplicationId(String applicationId){
        this.applicationId = applicationId;
    }
}