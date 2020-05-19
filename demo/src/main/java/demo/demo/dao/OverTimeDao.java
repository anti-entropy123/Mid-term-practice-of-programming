package demo.demo.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import demo.demo.entity.OverTime;

import demo.demo.mappers.OverTimeRowMapper;

@Service
public class OverTimeDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    final private String overTimeTable = "overtime";
    final private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    /*
        作用:   增加一个加班记录
        输入:   加班实例
        返回:   无
    */
    public void insertOvertimeRecord(OverTime overTime){
        String sql = "insert " + overTimeTable + "(user_id, date) values(?,?)";
        jdbcTemplate.update(sql, overTime.getUserId(), overTime.getDate());
    }
    
    /*
        作用:   查询某用户所有的加班记录
        输入:   某用户的id
        返回:   该用户所有加班记录
    */
    public List<OverTime> queryOvertimeById(int id){
        String sql = "select * from " + overTimeTable + " where user_id=?";
        List<OverTime> results = jdbcTemplate.query(sql, new OverTimeRowMapper(), id);
        return results;
    }


    /*
        作用:   通过 date 查询加班记录
        输入:   date字符串
        返回:   加班记录列表
    */
    public List<OverTime> queryOvertimeByDate(String date){
        String sql = "select * from " + overTimeTable + " where date = ?";
        List<OverTime> overTimes = jdbcTemplate.query(sql, new OverTimeRowMapper(), date);
        return overTimes;
    }
}