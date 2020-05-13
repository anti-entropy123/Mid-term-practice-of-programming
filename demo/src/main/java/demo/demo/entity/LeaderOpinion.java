package demo.demo.entity;

public class LeaderOpinion {
    private String leaderId; 
    private String applicationId;
    private String result;
    private String opinion;
    
    public String getLeaderId(){
        return this.leaderId;
    }
    public void setLeaderId(String leaderId){
        this.leaderId = leaderId;
    }

    public String getApplicationId(){
        return this.applicationId;
    }
    public void setApplicationId(String applicationId){
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