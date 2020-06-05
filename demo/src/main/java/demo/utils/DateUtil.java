package demo.utils;

import java.text.SimpleDateFormat;

/*
 * 需要对时间戳进行倍数操作的地方 
 * 此类的getDateFromLong方法
 * LeaveService的addLeaveApplication、modifyLeaveApplication
 * 
 */
import java.util.Date;

public class DateUtil {
	
	final private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	public String getDateFromDate(Date date) {
		return dateFormat.format(date);
	}
	
	public String getDateFromLong(long time) {
		try {
			Date date = new Date(time);
			return dateFormat.format(date);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getDays(long endTime, long startTime) {
		return (int)((endTime - startTime) / (1000 * 3600 * 24));
	}
	
	public int getDays(String endTime, String startTime) {
		try {
			Date endDate = dateFormat.parse(endTime);
			Date startDate = dateFormat.parse(startTime);
			return getDays(endDate.getTime(), startDate.getTime());
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
