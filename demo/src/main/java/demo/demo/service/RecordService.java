package demo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import demo.demo.bo.RecordBO;
import demo.demo.dao.RecordDao;

public class RecordService {

	@Autowired
	private RecordDao recordDao;
	
	/*
	 * 返回对应id的全部打卡信息
	 */
	public List<RecordBO> getRecordsById(int id) {
		return null;
	}

	/*
	 * 返回该id没有签到次数
	 */
	public int getUnsignedById(int id) {
		return 0;
	}

}
