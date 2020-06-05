package demo.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import demo.demo.bo.RecordBO;
import demo.demo.dao.ApplicationDao;
import demo.demo.dao.RecordDao;
import demo.demo.entity.Application;
import demo.demo.entity.LeaveApplication;
import demo.demo.entity.OutApplication;
import demo.demo.entity.Record;
import demo.utils.DateUtil;
import demo.utils.ExcelUtil;
import demo.utils.IdAndState;

@Service
public class RecordService {

	@Autowired
	private RecordDao recordDao;
	@Autowired
	private ApplicationDao applicationDao;
	
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
	
	/*
	 * 处理excel 保存记录
	 */
	public void saveRecords(MultipartFile file) {
		java.util.Date t = new java.util.Date();
		String date = new DateUtil().getDateFromDate(t);
		try {
			List excel = new ExcelUtil().readExcelFile(file.getInputStream(), file.getOriginalFilename());
			List sheet1 = (List)excel.get(0);
			for (int i = 0; i < sheet1.size(); i++) {
				IdAndState idAndState = (IdAndState)sheet1.get(i);
				Record record = new Record();
				record.setId(idAndState.getId());
				record.setDate(date);
				if ((idAndState.getState().equals("signed"))){
					record.setStatus("signed");
				} else {
					Application application = applicationDao.qureyApplicationByDate(idAndState.getId(), date);
					if (application instanceof LeaveApplication) {
						record.setStatus("leave");
					} else if (application instanceof OutApplication) {
						record.setStatus("out");
					} else {
						record.setStatus("unsigned");
					}
				}
				recordDao.insertSignInRecord(record);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
