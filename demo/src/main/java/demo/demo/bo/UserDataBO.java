package demo.demo.bo;

import java.util.ArrayList;
import java.util.List;

public class UserDataBO implements BusinessObject {
	
	private String userName;
	private List<MinLeaveApplicationBO> leaves = new ArrayList<MinLeaveApplicationBO>();
	private List<MinOutApplicationBO> outs = new ArrayList<MinOutApplicationBO>();
	private int unsigned;
	private int overTime;
	
	public UserDataBO() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<MinLeaveApplicationBO> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<MinLeaveApplicationBO> leaves) {
		this.leaves = leaves;
	}

	public List<MinOutApplicationBO> getOuts() {
		return outs;
	}

	public void setOuts(List<MinOutApplicationBO> outs) {
		this.outs = outs;
	}

	public int getUnsigned() {
		return unsigned;
	}

	public void setUnsigned(int unsigned) {
		this.unsigned = unsigned;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}
	
}
