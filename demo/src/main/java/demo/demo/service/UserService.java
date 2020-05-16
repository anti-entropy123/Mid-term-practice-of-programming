package demo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.demo.bo.MemberBO;
import demo.demo.dao.MemberDao;
import demo.demo.requestbody.LogInfo;

@Service
public class UserService {
	
	@Autowired
	private MemberDao memberDao;

	/*
	 * 判断用户用户密码是否正确
	 */
	public void logIn(LogInfo logInfo) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * 返回某用户的状态 
	 * signed unsigned leave out
	 */
	public String getStatusById(int id) {
		return "hello world";
	}

	/*
	 * 返回全体成员信息
	 */
	public List<MemberBO> getMembers() {
		return null;
	}

	/*
	 * 返回对应id的名字
	 */
	public String getNameById(int id) {
		return null;
	}

}
