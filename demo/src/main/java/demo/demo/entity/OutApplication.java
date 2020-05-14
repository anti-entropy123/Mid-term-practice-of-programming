package demo.demo.entity;

public class OutApplication extends Application{

    public OutApplication(){}

    public OutApplication(int applicationId, int userId, String startTime, String endTime, String reason){
        super(applicationId, userId, startTime, endTime, reason);
    }
}