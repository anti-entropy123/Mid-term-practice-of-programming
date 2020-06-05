package demo.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.demo.bo.ApplicationBO;
import demo.demo.bo.LeaderOpinionBO;
import demo.demo.bo.MOutApplicationBO;
import demo.demo.bo.MemberBO;
import demo.demo.bo.MinOutApplicationBO;
import demo.demo.dao.ApplicationDao;
import demo.demo.dao.MessageDao;
import demo.demo.entity.Application;
import demo.demo.entity.LeaderOpinion;
import demo.demo.entity.Member;
import demo.demo.entity.Message;
import demo.demo.entity.OutApplication;
import demo.demo.requestbody.ModifyOutInfo;
import demo.demo.requestbody.OutInfo;
import demo.demo.requestbody.ProcessInfo;
import demo.utils.ApplicationIdUtil;
import demo.utils.DateUtil;

@Service
public class OutService {
	
	@Autowired
	private ApplicationDao applicationDao;
//	@Autowired
//	private MemberDao memberDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserService userService;
	
	/*
	 * 添加外出申请
	 */
	public void addOutApplication(OutInfo outInfo) {
		ApplicationIdUtil applicationIdService = ApplicationIdUtil.getInstance();
		OutApplication application = new OutApplication();
		application.setUserId(outInfo.getId());
		application.setStartTime(new DateUtil().getDateFromLong(outInfo.getStartTime()));
		application.setEndTime(new DateUtil().getDateFromLong(outInfo.getEndTime()));
		application.setReason(outInfo.getReason());
		application.setApplicationId(applicationIdService.getId());
		applicationDao.insertApplication(application);
		addMessageToDepartmentManager(outInfo.getId());
	}
	
	

	/*
	 * 修改外出申请
	 * 加判断
	 */
	public void modifyOutApplication(ModifyOutInfo modifyOutInfo) {
		List<LeaderOpinion> opinion = applicationDao.qureyLeaderOpinionByAppId(modifyOutInfo.getOutId());
		if (opinion == null) {
			return;
		}
		OutApplication application = new OutApplication();
		application.setUserId(modifyOutInfo.getId());
		application.setStartTime(new DateUtil().getDateFromLong(modifyOutInfo.getStartTime()));
		application.setEndTime(new DateUtil().getDateFromLong(modifyOutInfo.getEndTime()));
		application.setReason(modifyOutInfo.getReason());
		application.setApplicationId(modifyOutInfo.getOutId());
		applicationDao.updateApplication(application);
	}

	/*
	 * 获得该id的迷你外出申请
	 */
	public List<MinOutApplicationBO> getMinOutApplicationById(int id) {
		List<Application> applications = applicationDao.qureyApplicationById(id);
		List<MinOutApplicationBO> minApplications = new ArrayList<MinOutApplicationBO>();
		for (Application application: applications) {
			if (application instanceof OutApplication) {
				MinOutApplicationBO minApplication = new MinOutApplicationBO();
				minApplication.setStartTime(application.getStartTime());
				minApplication.setEndTime(application.getEndTime());
				minApplications.add(minApplication);
			}
		}
		return minApplications;
	}

	/*
	 * 获得该id的重量级外出申请
	 */
	public List<ApplicationBO> getApplicationResultById(int id) {
		String name = userService.getNameById(id);
		List<Application> applications = applicationDao.qureyApplicationById(id);
		List<ApplicationBO> mApplications = new ArrayList<ApplicationBO>();
		for (Application application: applications) {
			if (application instanceof OutApplication) {
				MOutApplicationBO mApplication = new MOutApplicationBO();
				mApplication.setApplicationId(application.getApplicationId());
				mApplication.setMemberId(id);
				mApplication.setName(name);
				mApplication.setStartTime(application.getStartTime());
				mApplication.setEndTime(application.getEndTime());
				mApplication.setReason(application.getReason());
				getLeaderOpinion(mApplication);
				mApplications.add(mApplication);
			}
		}
		return mApplications;
	}

	/*
	 * 获得该id的重量级需要处理申请
	 */
	public List<ApplicationBO> getNeedToProcessById(int id) {
		String name = userService.getNameById(id);
		List<Message> messages = messageDao.qureyPersonMessage(id);
		List<ApplicationBO> mApplications = new ArrayList<ApplicationBO>();
		for (Message message: messages) {
			Application application = applicationDao.qureyApplicationByAppId(message.getApplicationId());
			if (application instanceof OutApplication) {
				MOutApplicationBO mApplication = new MOutApplicationBO();
				mApplication.setApplicationId(application.getApplicationId());
				mApplication.setMemberId(application.getUserId());
				mApplication.setName(userService.getNameById(application.getUserId()));
				mApplication.setStartTime(application.getStartTime());
				mApplication.setEndTime(application.getEndTime());
				mApplication.setReason(application.getReason());
				getLeaderOpinion(mApplication);
				mApplication.setAuthority();
				mApplications.add(mApplication);
			}
		}
		return mApplications;
	}

	/*
	 * 为外出申请添加处理意见
	 */
	public void addOpinion(ProcessInfo processInfo) {
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
	}
	
	/*
	 * 获取全部正在外出的员工信息
	 */
	public List<MemberBO> getOutMembers() {
		List<MemberBO> members = new ArrayList<MemberBO>();
		java.util.Date t = new java.util.Date();
		String date = new DateUtil().getDateFromDate(t);
		List<Application> applications = applicationDao.qureyAllApplication();
		for (Application application: applications) {
			if ((application instanceof OutApplication) &&
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
	
	private void getLeaderOpinion(MOutApplicationBO application) {
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
