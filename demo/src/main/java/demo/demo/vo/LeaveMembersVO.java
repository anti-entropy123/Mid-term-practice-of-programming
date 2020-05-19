package demo.demo.vo;

import java.util.ArrayList;
import java.util.List;

import demo.demo.bo.MemberBO;

/*
 * 全部的请假员工
 */
public class LeaveMembersVO implements ViewObject {
	
	private List<MemberBO> leaveMembers = new ArrayList<MemberBO>();
	
	public LeaveMembersVO() {}
	public LeaveMembersVO(List<MemberBO> list) {
		this.leaveMembers = list;
	}
	
	public List<MemberBO> getLeaveMembers() {
		return this.leaveMembers;
	}
	
	public void setLeaveMembers(List<MemberBO> leaveMembers) {
		this.leaveMembers = leaveMembers;
	}
	
}
