package demo.demo.entity;

public class LeaderOpinion {
    private int leaderId; 
    private int applicationId;
    private String result;
    private String opinion;
    
    public int getLeaderId(){
        return this.leaderId;
    }
    public void setLeaderId(int leaderId){
        this.leaderId = leaderId;
    }

    public int getApplicationId(){
        return this.applicationId;
    }
    public void setApplicationId(int applicationId){
        this.applicationId = applicationId;
    }

    public String getResult(){
        return this.result;
    }
    public void setResult(String result){
        this.result = result;
    }

    public String getOpinion(){
        return this.opinion;
    }
    public void setOpinion(String opinion){
        this.opinion = opinion;
    }
}