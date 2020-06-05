package demo.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.demo.bo.ApplicationBO;
import demo.demo.bo.ApplicationForBossBO;
import demo.demo.bo.LeaderOpinionBO;
import demo.demo.bo.MLeaveApplicationBO;
import demo.demo.bo.MemberBO;
import demo.demo.bo.MinLeaveApplicationBO;
import demo.demo.dao.ApplicationDao;
import demo.demo.dao.MessageDao;
import demo.demo.entity.Application;
import demo.demo.entity.HolidayBalance;
import demo.demo.entity.LeaderOpinion;
import demo.demo.entity.LeaveApplication;
import demo.demo.entity.Member;
import demo.demo.entity.Message;
import demo.demo.requestbody.LeaveInfo;
import demo.demo.requestbody.ModifyLeaveInfo;
import demo.demo.requestbody.ProcessInfo;
import demo.utils.ApplicationIdUtil;
import demo.utils.DateUtil;

@Service
public class LeaveService {
	
	@Autowired
	private ApplicationDao applicationDao;
//	@Autowired
//	private MemberDao memberDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserService userService;

	/*
	 * 添加请假
	 */
	public void addLeaveApplication(LeaveInfo leaveInfo) {
		ApplicationIdUtil applicationIdService = ApplicationIdUtil.getInstance();
		LeaveApplication application = new LeaveApplication();
		application.setUserId(leaveInfo.getId());
		application.setStartTime(new DateUtil().getDateFromLong(leaveInfo.getStartTime()));
		application.setEndTime(new DateUtil().getDateFromLong(leaveInfo.getEndTime()));
		application.setType(leaveInfo.getType());
		application.setReason(leaveInfo.getReason());
		application.setApplicationId(applicationIdService.getId());
		
		int balance = getBalance(leaveInfo.getId(), leaveInfo.getType());
		balance -= new DateUtil().getDays(leaveInfo.getEndTime(), leaveInfo.getStartTime());
		HolidayBalance holiday = new HolidayBalance();
		holiday.setBalance(balance);
		holiday.setId(leaveInfo.getId());
		holiday.setType(leaveInfo.getType());
		applicationDao.updateHolidayBalance(holiday);
		
		applicationDao.insertApplication(application);
		addMessageToDepartmentManager(leaveInfo.getId());
	}

	/*
	 * 修改请假申请
	 */
	public void modifyLeaveApplication(ModifyLeaveInfo modifyLeaveInfo) {
		List<LeaderOpinion> opinion = applicationDao.qureyLeaderOpinionByAppId(modifyLeaveInfo.getLeaveId());
		if (opinion == null) {
			return;
		}
		LeaveApplication application = new LeaveApplication();
		application.setUserId(modifyLeaveInfo.getId());
		application.setStartTime(new DateUtil().getDateFromLong(modifyLeaveInfo.getStartTime()));
		application.setEndTime(new DateUtil().getDateFromLong(modifyLeaveInfo.getEndTime()));
		application.setType(modifyLeaveInfo.getType());
		application.setReason(modifyLeaveInfo.getReason());
		application.setApplicationId(modifyLeaveInfo.getLeaveId());
		
		Application oldApplication = applicationDao.qureyApplicationByAppId(modifyLeaveInfo.getLeaveId());
		int balance = getBalance(modifyLeaveInfo.getId(), modifyLeaveInfo.getType());
		System.out.println(balance);
		balance += new DateUtil().getDays(oldApplication.getEndTime(), oldApplication.getStartTime());
		System.out.println(balance);
		balance -= new DateUtil().getDays(modifyLeaveInfo.getEndTime(), modifyLeaveInfo.getStartTime());
		System.out.println(balance);
		HolidayBalance holiday = new HolidayBalance();
		holiday.setBalance(balance);
		holiday.setId(modifyLeaveInfo.getId());
		holiday.setType(modifyLeaveInfo.getType());
		applicationDao.updateHolidayBalance(holiday);
		
		applicationDao.updateApplication(application);
	}

	/*
	 * 获取年假剩余天数
	 */
	public int[] getHolidayBalanceById(int id) {
		List<HolidayBalance> list = applicationDao.qureyHolidayBalance(id);
		int[] balances = new int[] { 0, 0, 0 };
		for (HolidayBalance balance: list) {
			if (balance.getType().equals("annualLeave")) {
				balances[0] = balance.getBalance();
			} else if (balance.getType().equals("maternityLeave")) {
				balances[1] = balance.getBalance();
			} else if (balance.getType().equals("homeLeave")) {
				balances[2] = balance.getBalance(); 
			}
		}
		return balances;
	}

	/*
	 * 获取迷你版请假申请
	 *  "startTime":"2020-4-12",
		"endTime":"2020-4-13",
		"leaveType":"chanjia" 
	 */
	public List<MinLeaveApplicationBO> getMinLeaveApplicationById(int id) {
		List<Application> applications = applicationDao.qureyApplicationById(id);
		List<MinLeaveApplicationBO> minApplications = new ArrayList<MinLeaveApplicationBO>();
		for (Application application: applications) {
			if (application instanceof LeaveApplication) {
				MinLeaveApplicationBO minApplication = new MinLeaveApplicationBO();
				minApplication.setStartTime(application.getStartTime());
				minApplication.setEndTime(application.getEndTime());
				minApplication.setLeaveType(((LeaveApplication)application).getType());
				minApplications.add(minApplication);
			}
		}
		return minApplications;
	}

	/*
	 * 获取重量级请假申请
	 * MLeaveApplicationBO
	 */
	public List<ApplicationBO> getApplicationResultById(int id) {
		String name = userService.getNameById(id);  
		List<Application> applications = applicationDao.qureyApplicationById(id);
		List<ApplicationBO> mApplications = new ArrayList<ApplicationBO>();
		for (Application application: applications) {
			if (application instanceof LeaveApplication) {
				MLeaveApplicationBO mApplication = new MLeaveApplicationBO();
				mApplication.setApplicationId(application.getApplicationId());
				mApplication.setMemberId(id);
				mApplication.setName(name);
				mApplication.setStartTime(application.getStartTime());
				mApplication.setEndTime(application.getEndTime());
				mApplication.setType(((LeaveApplication)application).getType());	
				mApplication.setReason(application.getReason());
				getLeaderOpinion(mApplication);
				mApplications.add(mApplication);
			}
		}
		return mApplications;
	}

	/*
	 * 获取重量级需要被该id处理的申请
	 *  MLeaveApplicationBO
	 *  doing
	 */
	public List<ApplicationBO> getNeedToProcessById(int id) { 
		List<Message> messages = messageDao.qureyPersonMessage(id);
		List<ApplicationBO> mApplications = new ArrayList<ApplicationBO>();
		for (Message message: messages) {
			Application application = applicationDao.qureyApplicationByAppId(message.getApplicationId());
			if (application instanceof LeaveApplication) {
				MLeaveApplicationBO mApplication = new MLeaveApplicationBO();
				mApplication.setApplicationId(application.getApplicationId());
				mApplication.setMemberId(application.getUserId());
				mApplication.setName(userService.getNameById(application.getUserId()));
				mApplication.setStartTime(application.getStartTime());
				mApplication.setEndTime(application.getEndTime());
				mApplication.setType(((LeaveApplication) application).getType());
				mApplication.setReason(application.getReason());
				getLeaderOpinion(mApplication);
				mApplication.setAuthority();
				mApplications.add(mApplication);
			}
		}
		return mApplications;
	}

	/*
	 * 为请假申请添加处理意见
	 */
	public boolean addOpinion(ProcessInfo processInfo) {
		Application application = applicationDao.qureyApplicationByAppId(processInfo.getApplicationId());
		if (application instanceof LeaveApplication) {
			applicationDao.updateApplicationResult(
						new LeaderOpinion(processInfo.getId(), processInfo.getApplicationId(),
										  processInfo.getResult(), processInfo.getOpinion())
					);
			String title = userService.getTitleById(processInfo.getId());
			if (title.equals("部门经理")) {
				addMessageToDeputyGeneralManager(processInfo.getApplicationId());
			} else if (title.equals("副总经理")) {
				addMessageToGeneralManager(processInfo.getApplicationId());
			}
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 查看其他管理者的审批
	 */
	public List<ApplicationForBossBO> getProcessApplicationResultsById(int id) {
		List<Message> messages = messageDao.qureyPersonMessage(id);
		List<ApplicationForBossBO> applicationFBs = new ArrayList<ApplicationForBossBO>();
		for (Message message: messages) {
			Application application = applicationDao.qureyApplicationByAppId(message.getApplicationId());
			ApplicationForBossBO applicationFB = new ApplicationForBossBO();
			applicationFB.setId(application.getUserId());
			applicationFB.setStartTime(application.getStartTime());
			applicationFB.setEndTime(application.getEndTime());
			String type = null;
			if (application instanceof LeaveApplication) {
				type = ((LeaveApplication) application).getType();
			} else {
				type = "out";
			}
			applicationFB.setType(type);
			applicationFB.setReason(application.getReason());
			getLeaderOpinion(applicationFB, application.getApplicationId(), id);	
			applicationFBs.add(applicationFB);
		}
		return applicationFBs;
	}
	
	/*
	 * 获取全体请假中员工信息
	 */
	public List<MemberBO> getLeaveMembers() {
		List<MemberBO> members = new ArrayList<MemberBO>();
		java.util.Date t = new java.util.Date();
		String date = new DateUtil().getDateFromDate(t);
		List<Application> applications = applicationDao.qureyAllApplication();
		for (Application application: applications) {
			if ((application instanceof LeaveApplication) &&
				(application.getStartTime().compareTo(date) <= 0) &&
				(application.getEndTime().compareTo(date) >= 0)) {
				int userId = application.getUserId();
				Member member = userService.getMembersById(userId);
				MemberBO memberBO = new MemberBO();
				memberBO.setId(userId);
				memberBO.setName(member.getName());
				memberBO.setTitle(member.getTitle());
				members.add(memberBO);
			}
		}
		return members;
	}
	
	private void getLeaderOpinion(MLeaveApplicationBO application) {
		List<LeaderOpinion> opinions = applicationDao.qureyLeaderOpinionByAppId(application.getApplicationId());
		List<LeaderOpinionBO> results = new ArrayList<LeaderOpinionBO>();
		for (LeaderOpinion opinion: opinions) {
			LeaderOpinionBO lob = new LeaderOpinionBO();
			lob.setResult(opinion.getResult());
			lob.setOpinion(opinion.getOpinion());
			int leaderId = opinion.getLeaderId();
			Member member = userService.getMembersById(leaderId);
			lob.setTitle(member.getTitle());
			lob.setName(member.getName());
			results.add(lob);
		}
		application.setLeadersOpinion(results);
	}
	
	private void getLeaderOpinion(ApplicationForBossBO applicationFB, int appId, int leaderId) {
		List<LeaderOpinion> opinions = applicationDao.qureyLeaderOpinionByAppId(appId);
		for (LeaderOpinion opinion: opinions) {
			if (opinion.getLeaderId() == leaderId) {
				LeaderOpinionBO lob = new LeaderOpinionBO();
				lob.setResult(opinion.getResult());
				lob.setOpinion(opinion.getOpinion());
				Member member = userService.getMembersById(leaderId);
				lob.setTitle(member.getTitle());
				lob.setName(member.getName());
				applicationFB.setLeadersOpinion(lob);
			}
		}
	}
	
	private int getBalance(int id, String type) {
		List<HolidayBalance> holidays = applicationDao.qureyHolidayBalance(id);
		for (HolidayBalance holiday: holidays) {
			if (holiday.getType().equals(type)) {
				return holiday.getBalance();
			}
		}
		return -1;
	}
	
	private void addMessageToDepartmentManager(int applicationId) {
		List<Integer> ids = userService.getDepartmentManagerId();
		addMessage(ids, applicationId);
	}
	
	private void addMessageToGeneralManager(int applicationId) {
		List<Integer> ids = userService.getGeneralManagerId();
		addMessage(ids, applicationId);
	}
	
	private void addMessageToDeputyGeneralManager(int applicationId) {
		List<Integer> ids = userService.getDeputyGeneralManagerId();
		addMessage(ids, applicationId);
	}
	
	private void addMessage(List<Integer> ids, int applicationId) {
		for (Integer id: ids) {
			Message m = new Message();
			m.setApplicationId(applicationId);
			m.setUserId(id);
			messageDao.insertMessage(m);
		}
	}

}
