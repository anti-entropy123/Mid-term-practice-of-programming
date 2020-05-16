package demo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import demo.demo.bo.ApplicationBO;
import demo.demo.bo.ApplicationForBossBO;
import demo.demo.bo.MemberBO;
import demo.demo.bo.MinOutApplicationBO;
import demo.demo.dao.ApplicationDao;
import demo.demo.requestbody.ModifyOutInfo;
import demo.demo.requestbody.OutInfo;
import demo.demo.requestbody.ProcessInfo;

public class OutService {
	
	@Autowired
	private ApplicationDao applicationDao;

	/*
	 * 添加外出申请
	 */
	public void addOutApplication(OutInfo outInfo) {
		
	}

	/*
	 * 修改外出申请
	 * 加判断
	 */
	public void modifyOutApplication(ModifyOutInfo modifyOutInfo) {
		
	}

	/*
	 * 获得该id的迷你外出申请
	 */
	public List<MinOutApplicationBO> getMinOutApplicationById(int id) {
		return null;
	}

	/*
	 * 获得该id的重量级外出申请
	 */
	public List<ApplicationBO> getApplicationResultById(int id) {
		return null;
	}

	/*
	 * 获得该id的重量级需要处理申请
	 */
	public List<ApplicationBO> getNeedToProcessById(int id) {
		return null;
	}

	/*
	 * 为外出申请添加处理意见
	 */
	public void addOpinion(ProcessInfo processInfo) {

	}
	
	/*
	 * 获取其他管理者审批
	 */
	public List<ApplicationForBossBO> getProcessApplicationResultsById(int id) {
		return null;
	}

	/*
	 * 获取全部正在外出的员工信息
	 */
	public List<MemberBO> getOutMembers() {
		return null;
	}
	
}
