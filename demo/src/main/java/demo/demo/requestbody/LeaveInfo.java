package demo.demo.requestbody;

public class LeaveInfo {
	
	private int id;
	private long startTime;
	private long endTime;
	private String type;
	private String reason;

	public LeaveInfo() {
		
	}
	public LeaveInfo(int id, long startTime, long endTime, String type, String reason){
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
		this.reason = reason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
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
	
}
