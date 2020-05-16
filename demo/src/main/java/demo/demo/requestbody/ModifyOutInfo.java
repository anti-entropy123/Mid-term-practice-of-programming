package demo.demo.requestbody;

public class ModifyOutInfo {

	private int id;
	private int outId;
	private String startTime;
	private String endTime;
	private String reason;
	
	public ModifyOutInfo() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getOutId() {
		return outId;
	}

	public void setOutId(int outId) {
		this.outId = outId;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
