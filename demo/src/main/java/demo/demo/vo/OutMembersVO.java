package demo.demo.vo;

import java.util.ArrayList;
import java.util.List;

import demo.demo.bo.MemberBO;

/*
 * 全部外出员工
 */
public class OutMembersVO implements ViewObject {
	
	private List<MemberBO> outMembers = new ArrayList<MemberBO>();
	
	public OutMembersVO(List<MemberBO> list) {
		this.outMembers = list;
	}
	
	public List<MemberBO> getLeaveMembers() {
		return this.outMembers;
	}
	
}

