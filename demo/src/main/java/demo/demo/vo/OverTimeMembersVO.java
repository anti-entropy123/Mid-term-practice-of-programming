package demo.demo.vo;

import java.util.ArrayList;
import java.util.List;

import demo.demo.bo.MemberBO;

public class OverTimeMembersVO implements ViewObject {
	
	private List<MemberBO> overTimeMembers = new ArrayList<MemberBO>();
	
	public OverTimeMembersVO(List<MemberBO> list) {
		this.overTimeMembers = list;
	}
	
	public List<MemberBO> getLeaveMembers() {
		return this.overTimeMembers;
	}
	
}
