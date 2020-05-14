package demo.demo.entity;

public class LeaveApplication extends Application{
    private String type;

    public LeaveApplication(){}

    public LeaveApplication(int applicationId, int userId, String startTime, String endTime, String reason, String type){
        super(applicationId, userId, startTime, endTime, reason);
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
}