package demo.demo.bo;

/*
 * 小含量申请信息
 */
public class MinApplicationBO implements BusinessObject {
	
	protected String startTime;
	protected String endTime;
	
	public MinApplicationBO() {
		
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
	
}
