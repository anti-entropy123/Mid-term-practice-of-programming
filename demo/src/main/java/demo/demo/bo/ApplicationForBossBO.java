package demo.demo.bo;

public class ApplicationForBossBO implements BusinessObject {

	private int id;
	private String startTime;
	private String endTime;
	private String type;
	private String reason;
	private LeaderOpinionBO leadersOpinion;
	
	public ApplicationForBossBO() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public LeaderOpinionBO getLeadersOpinion() {
		return leadersOpinion;
	}
	
	public void setLeadersOpinion(LeaderOpinionBO leadersOpinion) {
		this.leadersOpinion = leadersOpinion;
	}
	
}
