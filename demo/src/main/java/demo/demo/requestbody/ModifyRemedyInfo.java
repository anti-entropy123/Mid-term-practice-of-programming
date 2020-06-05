package demo.demo.requestbody;

public class ModifyRemedyInfo {
	
	private int id;
	private int remedyId;
	private String date;
	private String reason;
	
	public ModifyRemedyInfo() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getRemedyId() {
		return remedyId;
	}

	public void setRemedyId(int remedyId) {
		this.remedyId = remedyId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
