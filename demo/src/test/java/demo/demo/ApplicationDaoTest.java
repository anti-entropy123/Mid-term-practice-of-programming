package demo.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import demo.demo.dao.ApplicationDao;
import demo.demo.entity.Application;
import demo.demo.entity.HolidayBalance;
import demo.demo.entity.LeaderOpinion;
import demo.demo.entity.LeaveApplication;
import demo.demo.entity.OutApplication;

public class ApplicationDaoTest extends TestBase{
    @Autowired
    private ApplicationDao applicationDao;

    OutApplication a1 = new OutApplication();
    LeaveApplication a2 = new LeaveApplication();
    HolidayBalance hb;
    LeaderOpinion lo;

    private void initObj(){
        a1.setApplicationId(1);
        a1.setStartTime("2000/6/21");
        a1.setEndTime("2000/6/23");
        a1.setReason("i want to play a game");
        a1.setUserId(123);

        a2.setApplicationId(2);
        a2.setStartTime("2020/7/21");
        a2.setEndTime("2020/7/22");
        a2.setReason("i still want to play a game");
        a2.setUserId(123);
        a2.setType("annualLeave");

        hb = new HolidayBalance(123, 10, "annualLeave");
        lo = new LeaderOpinion(123, 1, "agree", "refuse");
    }

    @Test
    public void testInsertApplication(){
        initObj();
        applicationDao.insertApplication(a1);
        applicationDao.insertApplication(a2);
    }

    @Test 
    public void testUpdateHolidayBalance(){
        initObj();
        
        List<HolidayBalance> hbs = new ArrayList<>();
        hbs.add(hb);
        applicationDao.insertHolidayBalance(hb);
        hb.setBalance(5);
        applicationDao.updateHolidayBalance(hb);
        assertEquals(5, applicationDao.qureyHolidayBalance(123).get(0).getBalance());
    }

    @Test
    public void testQueryApplication(){
        initObj();
        List<Application> apps = new ArrayList<>();
        
        apps = applicationDao.qureyApplicationById(123);
        for(Application a: apps){
            System.out.println(a.getStartTime());
            System.out.println(a.getEndTime());
            System.out.println(a.getUserId());
            System.out.println(a.getApplicationId());
            System.out.println(a.getReason()); 

            if(a instanceof LeaveApplication){
                System.out.println(((LeaveApplication)a).getType());
            }
        }

        apps.clear(); 
        apps.add(applicationDao.qureyApplicationByAppId(1));
        for(Application a: apps){
            System.out.println(a.getStartTime());
            System.out.println(a.getEndTime());
            System.out.println(a.getUserId());
            System.out.println(a.getApplicationId());
            System.out.println(a.getReason()); 

            if(a instanceof LeaveApplication){
                System.out.println(((LeaveApplication)a).getType());
            }
        }
        apps.clear();
        apps.add( applicationDao.qureyApplicationByAppId(2));
        for(Application a: apps){
            System.out.println(a.getStartTime());
            System.out.println(a.getEndTime());
            System.out.println(a.getUserId());
            System.out.println(a.getApplicationId());
            System.out.println(a.getReason()); 

            if(a instanceof LeaveApplication){
                System.out.println(((LeaveApplication)a).getType());
            }
        }

        apps = applicationDao.qureyAllApplication();
        for(Application a: apps){
            System.out.println(a.getStartTime());
            System.out.println(a.getEndTime());
            System.out.println(a.getUserId());
            System.out.println(a.getApplicationId());
            System.out.println(a.getReason()); 

            if(a instanceof LeaveApplication){
                System.out.println(((LeaveApplication)a).getType());
            }
        }
    }

    @Test
    public void testUpdateApplication(){
        initObj();
        a1.setEndTime("2020/6/21");
        applicationDao.updateApplication(a1);
    }

    @Test
    public void testUpdateLeaderOpinion(){
        initObj();
        applicationDao.updateApplicationResult(lo);
    }
}