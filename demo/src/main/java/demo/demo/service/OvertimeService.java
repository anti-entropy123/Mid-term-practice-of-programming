package demo.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.demo.bo.MemberBO;
import demo.demo.dao.MemberDao;
import demo.demo.dao.OverTimeDao;
import demo.demo.entity.Member;
import demo.demo.entity.OverTime;
import demo.demo.requestbody.OvertimeInfo;
import demo.utils.DateUtil;

@Service
public class OvertimeService {
	
	@Autowired
	private OverTimeDao overTimeDao;
	@Autowired
	private MemberDao memberDao;

	/*
	 * 加班登记
	 */
	public void addOvertimeRecord(OvertimeInfo overtimeInfo) {
		overTimeDao.insertOvertimeRecord(
					new OverTime(overtimeInfo.getId(), new DateUtil().getDateFromLong(overtimeInfo.getDatetime()))
				);
	}

	/*
	 * 返回该id的加班次数
	 */
	public int getOvertimeById(int id) {
		List<OverTime> lists = overTimeDao.queryOvertimeById(id);
		return lists.size();
	}

	/*
	 * 返回正在加班的人
	 */
	public List<MemberBO> getOvertimeMembers() {
		List<MemberBO> memberBOs = new ArrayList<MemberBO>();
		java.util.Date t = new java.util.Date();
		String date = new DateUtil().getDateFromDate(t);
		List<OverTime> overTimes = overTimeDao.queryOvertimeByDate(date);
		for (OverTime overTime: overTimes) {
			int id = overTime.getUserId();
		    Member member = memberDao.qureyUser(id);
		    MemberBO memberBO = new MemberBO();
		    memberBO.setId(id);
		    memberBO.setName(member.getName());
		    memberBO.setTitle(member.getTitle());
		    memberBOs.add(memberBO);
		}
		return memberBOs;
	}

}
