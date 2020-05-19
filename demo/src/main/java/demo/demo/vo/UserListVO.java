package demo.demo.vo;

import java.util.ArrayList;
import java.util.List;

import demo.demo.bo.MemberBO;

/*
 * 全体员工列表
 */
public class UserListVO implements ViewObject {
	
	private List<MemberBO> members = new ArrayList<MemberBO>();

	public UserListVO() {}
	public UserListVO(List<MemberBO> members) {
		this.members = members;
	}
	
	public List<MemberBO> getMembers() {
		return this.members;
	}
	
	public void setMembers(List<MemberBO> members) {
		this.members = members;
	}
	
}
