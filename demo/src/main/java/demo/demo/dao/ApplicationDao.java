package demo.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import demo.demo.entity.Application;
import demo.demo.entity.HolidayBalance;
import demo.demo.entity.LeaderOpinion;
import demo.demo.entity.LeaveApplication;
import demo.demo.entity.OutApplication;
import demo.demo.mappers.HolidayBalanceRowMapper;


@Service
public class ApplicationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private String applicationTable = "application";
    private String holidayBalanceTable = "holiday_balance";
    private String leaderOpinionTable = "leader_opinion";

    // 增加申请
    public void insertLeaveApplication(Application application){
        String type;
        if(application instanceof OutApplication){
            type = "out";
        }else if(application instanceof LeaveApplication){
            type = ((LeaveApplication)application).getType();
        }else{
            // todo 补签申请
            type = "re-signed";
        }
        String sql = "insert ?(user_id, application_id, startTime, endTime, reason, type) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, applicationTable, application.getUserId(), application.getApplicationId(), application.getStartTime(), application.getEndTime(), application.getReason(), type);
    }

    public void updateLeaveApplication(LeaveApplication application){
        String sql = 
            "update ?" +
            "set startTime = ? " +
                "endTime = ?"    +
                "reason = ?"     +
                "type = ?"       +
            "where user_id = ? and application_id = ?";
        jdbcTemplate.update(sql, applicationTable, application.getStartTime(), application.getEndTime(), application.getReason(), application.getType(), application.getUserId(), application.getApplicationId()); 
    }

    public void updateOutApplication(OutApplication  application){
        String sql = 
            "update ?" +
            "set startTime = ? " +
                "endTime = ?"    +
                "reason = ?"     +
            "where user_id = ? and application_id = ?";
        jdbcTemplate.update(sql,applicationTable, application.getStartTime(), application.getEndTime(), application.getReason(), application.getUserId(), application.getApplicationId());
    }
    
    private List<Application> transMapToApplication(List<Map<String, Object>> maps){
        List<Application> applications = new ArrayList<Application>();
        for(Map<String, Object> result: maps){
            // 如果是外出申请
            String type = (String)result.getOrDefault("type", "unkown");
            if(type.equals("out")){
                applications.add(new OutApplication(
                    (int)result.get("user_id"), 
                    (int)result.get("application_id"), 
                    (String)result.get("startTime"), 
                    (String)result.get("endTime"), 
                    (String)result.get("reason")));
            // 如果是请假申请
            }else if(type.indexOf("Leave") >= 0 ){
                applications.add(new LeaveApplication(
                    (int)result.get("user_id"), 
                    (int)result.get("application_id"), 
                    (String)result.get("startTime"), 
                    (String)result.get("endTime"), 
                    (String)result.get("reason"),
                    (String)result.get("type")));
            // 补签申请
            }else if(type.equals("re-signed")){
                // todo
            }
        }
        return applications;
    }

    public List<Application> qureyAllApplication(){
        String sql = "select * from ?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, applicationTable);
        return transMapToApplication(results);
    }

    public List<Application> qureyApplicationById(int id){
        String sql = "select * from ? where id=?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, applicationTable, id);
        return transMapToApplication(results);
    }

    public List<HolidayBalance> qureyHolidayBalance(int id){
        String sql = "select * from ? where id=?";
        List<HolidayBalance> result = jdbcTemplate.query(sql, new HolidayBalanceRowMapper(), holidayBalanceTable, id);
        return result;
    }
        
    public void updateApplicationResult(LeaderOpinion opinion){
        String sql = "insert ?(application_id, leader_id, result, opinion) values(?,?,?,?)";
        jdbcTemplate.update(sql, leaderOpinionTable, opinion.getApplicationId(), opinion.getLeaderId(), opinion.getResult(), opinion.getOpinion());
    }
}