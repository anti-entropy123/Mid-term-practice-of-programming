package demo.demo;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import demo.demo.dao.OverTimeDao;
import demo.demo.entity.OverTime;

public class OverTimeDaoTest extends TestBase{
    @Autowired
    OverTimeDao overTimeDao;

    @Test
    public void testInsertOverTime(){
        overTimeDao.insertOvertimeRecord(new OverTime(123, "2020/5/17"));
    }

    @Test
    public void testQureyOverTIme(){
        List<OverTime> overTimes = overTimeDao.queryOvertimeById(123);
        for(OverTime overTime: overTimes){
            System.out.println(overTime.getDate());
        }

        overTimes = overTimeDao.queryOvertimeByDate("2020/5/17");
        for(OverTime overTime: overTimes){
            System.out.println(overTime.getUserId());
        }
    }


}