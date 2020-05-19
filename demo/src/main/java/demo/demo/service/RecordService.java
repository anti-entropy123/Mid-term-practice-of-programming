package demo.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.demo.bo.RecordBO;
import demo.demo.dao.RecordDao;
import demo.demo.entity.Record;

@Service
public class RecordService {

	@Autowired
	private RecordDao recordDao;
	
	/*
	 * 返回对应id的全部打卡信息
	 */
	public List<RecordBO> getRecordsById(int id) {
		List<Record> records = recordDao.qureyPersonRecords(id);
		List<RecordBO> minRecords = new ArrayList<RecordBO>();
		for(Record record: records) {
			minRecords.add(new RecordBO(record.getDate(), record.getStatus()));
		}
		return minRecords;
	}

	/*
	 * 返回该id没有签到次数
	 */
	public int getUnsignedById(int id) {
		int sum = 0;
		List<Record> records = recordDao.qureyPersonRecords(id);
		for(Record record: records) {
			if (record.getStatus().equals("unsigned")) {
				sum++;
			}
		}
		return sum;
	}

}
