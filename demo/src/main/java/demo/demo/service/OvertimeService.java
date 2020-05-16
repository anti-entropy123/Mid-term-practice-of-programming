package demo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import demo.demo.bo.MemberBO;
import demo.demo.dao.OverTimeDao;
import demo.demo.requestbody.OvertimeInfo;

public class OvertimeService {
	
	@Autowired
	private OverTimeDao overTimeDao;

	/*
	 * 加班登记
	 */
	public void addOvertimeRecord(OvertimeInfo overtimeInfo) {
	}

	/*
	 * 返回该id的加班次数
	 */
	public int getOvertimeById(int id) {
		return 0;
	}

	/*
	 * 返回正在加班的人
	 */
	public List<MemberBO> getOvertimeMembers() {
		return null;
	}

}
