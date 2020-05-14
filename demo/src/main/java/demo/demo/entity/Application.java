package demo.demo.entity;

public abstract class Application {
    private int applicationId;
    private int userId;
    private String startTime;
    private String endTime;
    private String reason;

    Application(){}
    Application(int applicationId, int userId, String startTime, String endTime, String reason){
        this.applicationId = applicationId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
    }

    public int getApplicationId(){
        return this.applicationId;
    }
    public void setApplicationId(int applicationId){
        this.applicationId = applicationId;
    }

    public int getUserId(){
        return this.userId;
    }
    public void setUserId(int userId){
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