package demo.demo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import demo.demo.entity.Application;
import demo.demo.entity.HolidayBalance;
import demo.demo.entity.LeaderOpinion;
import demo.demo.entity.LeaveApplication;
import demo.demo.entity.OutApplication;
import demo.demo.mappers.HolidayBalanceRowMapper;
import demo.demo.mappers.LeaderOpinionRowMapper;

@Service
public class ApplicationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    final private String applicationTable = "application";
    final private String holidayBalanceTable = "holiday_balance";
    final private String leaderOpinionTable = "leader_opinion";
    final private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    /*
     * 作用: 增加一条申请 输入: 要增加的申请, 例如如果增加一个外出申请, 就传来一个OutApplication的实例 返回: 无
     */
    public void insertApplication(Application application) {
        String type;
        if (application instanceof OutApplication) {
            type = "out";
        } else if (application instanceof LeaveApplication) {
            type = ((LeaveApplication) application).getType();
        } else {
            // todo 补签申请
            type = "re-signed";
        }
        String sql = "insert " + applicationTable
                + "(user_id, startTime, endTime, reason, type) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, application.getUserId(), application.getStartTime(),
                application.getEndTime(), application.getReason(), type);
    }

    /*
     * 作用: 根据 user id 和 application id 更新申请数据 输入: 需要更新的申请,
     * 注意必须保证传来的application中的userId和applicationId都是正确的, 否则无法更新 返回: 无
     */
    public void updateApplication(Application application) {
        String type;
        if (application instanceof OutApplication) {
            type = "out";
        } else if (application instanceof LeaveApplication) {
            type = ((LeaveApplication) application).getType();
        } else {
            // todo 补签申请
            type = "re-signed";
        }

        String sql = "update " + applicationTable + " " + "set startTime = ?, " + "endTime = ?, " + "reason = ?, "
                + "type = ? " + "where user_id = ? and application_id = ?";
        jdbcTemplate.update(sql, application.getStartTime(), application.getEndTime(), application.getReason(), type,
                application.getUserId(), application.getApplicationId());
    }

    /*
     * 作用: 查询所有申请 输入: 无 返回: 所有的申请数据, 包括外出, 请假等
     */
    public List<Application> qureyAllApplication() {
        String sql = "select * from " + applicationTable;
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        return transMapToApplication(results);
    }

    /*
     * 作用: 通过 user id 查询申请记录 输入: 用户的id 返回: 某一位用户的所有的申请记录, 包括外出, 请假等
     */
    public List<Application> qureyApplicationById(int id) {
        String sql = "select * from " + applicationTable + " where user_id=?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, id);
        System.out.println("this result set length is " + results.size());
        return transMapToApplication(results);
    }

    /*
     * 作用: 通过 application id 查询申请记录 
     * 输入: 用户的id 
     * 返回: 某一位用户的所有的申请记录, 包括外出, 请假等
     */

    public Application qureyApplicationByAppId(int applicationId) {
        String sql = "select * from " + applicationTable + " where application_id=?";
        try{
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, applicationId);
            return transMapToApplication(result);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    /*
     * 作用: 通过用户id和日期字符串来查询某条申请, 使得传入的 date 位于该 申请的 startDate 和 endDate 之间 
     * 输入: 用户id和日期字符串, 注意 ! 日期字符串必须为 yyyy/MM/dd 格式, 例如 "2000/06/21" 而不能是 "2000/6/21" 
     * 返回: Application对象, 如果没有查询到, 则会返回 null
    */
    public Application qureyApplicationByDate(int id, String currentDate){
        try{
            Date current = simpleDateFormat.parse(currentDate);

            for(Application application: qureyApplicationById(id)){ 
                Date startDate = simpleDateFormat.parse(application.getStartTime());
                Date endDate = simpleDateFormat.parse(application.getEndTime());
                
                if(current.compareTo(startDate) >= 0 && endDate.compareTo(current) >= 0){
                    return application;
                }
            }
        }catch(ParseException e){
            return null;
        }

        return null;
    }

    /* 
     *   作用:   查询某员工的假期余额
     *   输入:   某位员工的id
     *   返回:   该员工的所有假期的余额的数据    
    */
    public List<HolidayBalance> qureyHolidayBalance(int id){
        String sql = "select * from " + holidayBalanceTable + " where id=?";
        List<HolidayBalance> result = jdbcTemplate.query(sql, new HolidayBalanceRowMapper(), id);
        return result;
    }

    /*
     * 作用:   增加一个假期余额
     * 输入:   假期余额实例
     * 返回:   无
    */
    public void insertHolidayBalance(HolidayBalance hb){
        String sql = "insert " + holidayBalanceTable + " (id, type, balance) values(?,?,?)";
        jdbcTemplate.update(sql, hb.getId(), hb.getType(), hb.getBalance());
    }

    /*
        作用:   修改假期余额
        输入:   假期余额实例, 需确保实例中的user id 和 type和现有数据对应
        返回:   无
    */
    public void updateHolidayBalance(HolidayBalance hb){
        String sql = 
            "update " + holidayBalanceTable + " " +
            "set balance = ? "                     +
            "where id = ? and type = ?";
        jdbcTemplate.update(sql, hb.getBalance(), hb.getId(), hb.getType());
    }

    
    /*
        作用:   查询现有的某申请的领导审批结果
        输入:   某条申请的id
        输出:   若干LeaderOrder对象
    */

    public List<LeaderOpinion> qureyLeaderOpinionByAppId(int applicationId){
        String sql = "select * from " + leaderOpinionTable + " where  application_id = ?";
        List<LeaderOpinion> results = jdbcTemplate.query(sql, new LeaderOpinionRowMapper(), applicationId);
        return results;
    }

    /*
        作用:   为某一请求增加领导(审批)意见, 注意, 是增加, 而不是修改现有的
                (因为一个申请往往需要多个领导的审批)
        输入:   领导意见实例对象
        返回:   无
    */
    public void updateApplicationResult(LeaderOpinion opinion){
        String sql = "insert " + leaderOpinionTable + " (application_id, leader_id, result, opinion) values(?,?,?,?)";
        jdbcTemplate.update(sql, opinion.getApplicationId(), opinion.getLeaderId(), opinion.getResult(), opinion.getOpinion());
    }

    /*
    内部使用
    */
    private Application transMapToApplication(Map<String, Object> result){
        Application application = new OutApplication();
        // 如果是外出申请
        String type = (String)result.getOrDefault("type", "unkown");
        if(type.equals("out")){
            application = new OutApplication(
            	(int)result.get("application_id"), 
                (int)result.get("user_id"), 
                (String)result.get("startTime"), 
                (String)result.get("endTime"), 
                (String)result.get("reason"));
        // 如果是请假申请
        }else if(type.indexOf("Leave") >= 0 ){
            application = new LeaveApplication(
                (int)result.get("application_id"),
                (int)result.get("user_id"),
                (String)result.get("startTime"), 
                (String)result.get("endTime"), 
                (String)result.get("reason"),
                (String)result.get("type"));
        // 补签申请
        }else if(type.equals("re-signed")){
            // todo
        }
        return application;
    }

    /*
        内部使用
    */
    private List<Application> transMapToApplication(List<Map<String, Object>> maps){
        List<Application> applications = new ArrayList<Application>();
        for(Map<String, Object> result: maps){
            // 如果是外出申请
            String type = (String)result.getOrDefault("type", "unkown");
            if(type.equals("out")){
                applications.add(new OutApplication(
                	(int)result.get("application_id"), 
                    (int)result.get("user_id"),
                    (String)result.get("startTime"), 
                    (String)result.get("endTime"), 
                    (String)result.get("reason")));
            // 如果是请假申请
            }else if(type.indexOf("Leave") >= 0 ){
                applications.add(new LeaveApplication(
                    (int)result.get("application_id"), 
                    (int)result.get("user_id"), 
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

}