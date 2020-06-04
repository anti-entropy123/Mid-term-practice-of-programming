package demo.demo.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.demo.bo.MemberBO;
import demo.demo.dao.MemberDao;
import demo.demo.dao.RecordDao;
import demo.demo.entity.Member;
import demo.demo.requestbody.LogInfo;

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
	 * 返回某用户的状态 
	 * signed unsigned leave out
	 */
	@SuppressWarnings("deprecation")
	public String getStatusById(int id) {
		java.util.Date t = new java.util.Date();
		java.sql.Date date = new java.sql.Date(t.getDate());
		return recordDao.qureyRecordByDate(id, date.toString()).getStatus();
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

}
