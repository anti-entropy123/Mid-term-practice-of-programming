package demo.demo.bo;

/*
 * 小含量的请假信息
 */
public class MinLeaveApplicationBO extends MinApplicationBO {
	
	private String leaveType;
	
	public MinLeaveApplicationBO() {
		super();
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

}
