package demo.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import demo.demo.bo.MemberBO;
import demo.demo.dao.MemberDao;
import demo.demo.dao.RecordDao;
import demo.demo.entity.Member;
import demo.demo.requestbody.LogInfo;
import demo.utils.DateUtil;

@Service
public class UserService {
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private RecordDao recordDao;

	/*
	 * 判断用户用户密码是否正确
	 */
	public String logIn(LogInfo logInfo) {
		String pwd = memberDao.qureyUserPassword(logInfo.getId());
		if (pwd.equals(logInfo.getPasswd())) {
			return "token";
		} else if (pwd.equals("null")) {
			return "NoUser";
		} else {
			return "ErrorPwd";
		}
	}
	/*
	 * 登陆后返回某用户的个人信息
	 * 6.5 add by yjn 
	 */
	public Member getLoginSatus(int userId){
		Member m = memberDao.qureyUser(userId);
		return m;
	}
	/*
	 * 返回某用户的状态 
	 * signed unsigned leave out
	 */
	public String getStatusById(int id) {
		java.util.Date t = new java.util.Date();
		String date = new DateUtil().getDateFromDate(t);
		try {
			String status = recordDao.qureyRecordByDate(id, date).getStatus();
			return status;
		} catch(EmptyResultDataAccessException e) {
			return "no_record";
		}
	}
	
	public Member getMembersById(int id) {
		return memberDao.qureyUser(id);
	}

	/*
	 * 返回全体成员信息
	 */
	public List<MemberBO> getMembers() {
		List<Member> members = memberDao.qureyMembersList();
		List<MemberBO> minMembers = new ArrayList<MemberBO>();
		
		for (Member member: members) {
			MemberBO tMember = new MemberBO();
			tMember.setId(member.getId());
			tMember.setName(member.getName());
			tMember.setTitle(member.getTitle());
			minMembers.add(tMember);
		}
		
		return minMembers;
	}

	/*
	 * 返回对应id的名字
	 */
	public String getNameById(int id) {
		return memberDao.qureyUser(id).getName();
	}
	
	/*
	 * 返回对应id的职务
	 */
	public String getTitleById(int id) {
		return memberDao.qureyUser(id).getTitle();
	}
	
	/*
	 * 返回总经理id
	 */
	public List<Integer> getGeneralManagerId() {
		return getTitleId("总经理");
	}
	
	/*
	 * 返回副总经理id
	 */
	public List<Integer> getDeputyGeneralManagerId() {
		return getTitleId("副总经理");
	}
	
	/*
	 * 返回部门经理id
	 */
	public List<Integer> getDepartmentManagerId() {
		return getTitleId("部门经理");
	}
	
	private List<Integer> getTitleId(String title) {
		List<MemberBO> members = getMembers();
		List<Integer> ids = new ArrayList<Integer>();
		for (MemberBO member: members) {
			if (member.getTitle().equals(title)) {
				ids.add(member.getId());
			}
		}
		return ids;
	}

}
