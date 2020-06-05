package demo.demo.bo;

/*
 * 中等含量信息的请假申请
 */
public class LeaveApplicationBO extends ApplicationBO {
	
	private String type;
	
	public LeaveApplicationBO() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
