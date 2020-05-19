package demo.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.demo.bo.ApplicationBO;
import demo.demo.bo.ApplicationForBossBO;
import demo.demo.bo.LeaderOpinionBO;
import demo.demo.bo.MOutApplicationBO;
import demo.demo.bo.MemberBO;
import demo.demo.bo.MinOutApplicationBO;
import demo.demo.dao.ApplicationDao;
import demo.demo.dao.MemberDao;
import demo.demo.dao.MessageDao;
import demo.demo.entity.Application;
import demo.demo.entity.LeaderOpinion;
import demo.demo.entity.LeaveApplication;
import demo.demo.entity.Member;
import demo.demo.entity.Message;
import demo.demo.entity.OutApplication;
import demo.demo.requestbody.ModifyOutInfo;
import demo.demo.requestbody.OutInfo;
import demo.demo.requestbody.ProcessInfo;

@Service
public class OutService {
	
	@Autowired
	private ApplicationDao applicationDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MessageDao messageDao;
	
	/*
	 * 添加外出申请
	 */
	public void addOutApplication(OutInfo outInfo) {
		ApplicationIdService applicationIdService = ApplicationIdService.getInstance();
		OutApplication application = new OutApplication();
		application.setUserId(outInfo.getId());
		application.setStartTime(outInfo.getStartTime());
		application.setEndTime(outInfo.getEndTime());
		application.setReason(outInfo.getReason());
		application.setApplicationId(applicationIdService.getId());
		applicationDao.insertApplication(application);
	}

	/*
	 * 修改外出申请
	 * 加判断
	 */
	public void modifyOutApplication(ModifyOutInfo modifyOutInfo) {
		OutApplication application = new OutApplication();
		application.setUserId(modifyOutInfo.getId());
		application.setStartTime(modifyOutInfo.getStartTime());
		application.setEndTime(modifyOutInfo.getEndTime());
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
		String name = memberDao.qureyUser(id).getName();
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
	 * doing
	 */
	public List<ApplicationBO> getNeedToProcessById(int id) {
		String name = memberDao.qureyUser(id).getName();
		List<Message> messages = messageDao.qureyPersonMessage(id);
		List<ApplicationBO> mApplications = new ArrayList<ApplicationBO>();
		for (Message message: messages) {
			Application application = applicationDao.qureyApplicationByAppId(message.getApplicationId());
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
	 * 为外出申请添加处理意见
	 */
	public void addOpinion(ProcessInfo processInfo) {
		applicationDao.updateApplicationResult(
				new LeaderOpinion(processInfo.getId(), processInfo.getApplicationId(),
								  processInfo.getResult(), processInfo.getOpinion())
			);
	}
	
	/*
	 * 获取全部正在外出的员工信息
	 */
	public List<MemberBO> getOutMembers() {
		List<MemberBO> members = new ArrayList<MemberBO>();
		java.util.Date t0 = new java.util.Date();
		java.sql.Date t1 = new java.sql.Date(t0.getTime());
		String date = t1.toString();
		List<Application> applications = applicationDao.qureyAllApplication();
		for (Application application: applications) {
			if ((application instanceof OutApplication) &&
				(application.getStartTime().compareTo(date) <= 0) &&
				(application.getEndTime().compareTo(date) >= 0)) {
				int userId = application.getUserId();
				Member member = memberDao.qureyUser(userId);
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
			int leaderId = opinion.getApplicationId();
			Member member = memberDao.qureyUser(leaderId);
			lob.setTitle(member.getTitle());
			lob.setName(member.getName());
			results.add(lob);
		}
		application.setLeadersOpinion(results);
	}
	
}
