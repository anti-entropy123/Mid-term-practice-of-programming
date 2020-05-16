package demo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import demo.demo.bo.ApplicationBO;
import demo.demo.bo.ApplicationForBossBO;
import demo.demo.bo.MemberBO;
import demo.demo.bo.MinLeaveApplicationBO;
import demo.demo.dao.ApplicationDao;
import demo.demo.requestbody.LeaveInfo;
import demo.demo.requestbody.ModifyLeaveInfo;
import demo.demo.requestbody.ProcessInfo;

public class LeaveService {
	
	@Autowired
	private ApplicationDao applicationDao;

	/*
	 * 添加请假
	 */
	public void addLeaveApplication(LeaveInfo leaveInfo) {
		
	}

	/*
	 * 修改请假申请
	 */
	public void modifyLeaveApplication(ModifyLeaveInfo modifyLeaveInfo) {
		
	}

	/*
	 * 获取年假剩余天数
	 */
	public int getHolidayBalanceById(int id) {
		return 0;
	}

	/*
	 * 获取迷你版请假申请
	 *  "startTime":"2020-4-12",
		"endTime":"2020-4-13",
		"leaveType":"chanjia" 
	 */
	public List<MinLeaveApplicationBO> getMinLeaveApplicationById(int id) {
		return null;
	}

	/*
	 * 获取重量级请假申请
	 * MLeaveApplicationBO
	 */
	public List<ApplicationBO> getApplicationResultById(int id) {
		return null;
	}

	/*
	 * 获取重量级需要被该id处理的申请
	 *  MLeaveApplicationBO
	 */
	public List<ApplicationBO> getNeedToProcessById(int id) {
		return null;
	}

	/*
	 * 获取全体请假中员工信息
	 */
	public List<MemberBO> getLeaveMembers() {
		return null;
	}

	/*
	 * 为请假申请添加处理意见
	 */
	public void addOpinion(ProcessInfo processInfo) {

	}

	/*
	 * 查看其他管理者的审批
	 */
	public List<ApplicationForBossBO> getProcessApplicationResultsById(int id) {
		return null;
	}

}
