package demo.demo.vo;

/*
 * 某员工的全部打卡记录
 */
import java.util.ArrayList;
import java.util.List;

import demo.demo.bo.RecordBO;

public class PersonRecordVO implements ViewObject {
	
	private List<RecordBO> records = new ArrayList<RecordBO>();
	
	public PersonRecordVO() {}
	public PersonRecordVO(List<RecordBO> records) {
		this.records = records;
	}
	
	public List<RecordBO> getRecords() {
		return records;
	}
	
	public void setRecords(List<RecordBO> records) {
		this.records = records;
	}
	
}
