package demo.demo.bo;

/*
 * 打卡数据
 */
public class RecordBO implements BusinessObject {

	private String date;
	private String status;
	
	public RecordBO() {}
	public RecordBO(String date, String status) {
		this.date = date;
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
