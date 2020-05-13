package demo.demo.entity;

public class Application {
    private String applicationId;
    private String userId;
    private String startTime;
    private String endTime;
    private String reason;

    public String getApplicationId(){
        return this.applicationId;
    }
    public void setApplicationId(String applicationId){
        this.applicationId = applicationId;
    }

    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getStartTime(){
        return this.startTime;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public String getReason(){
        return this.reason;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
}