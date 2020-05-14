package demo.demo.dao;

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
    private String overTimeTable = "overtime";

    public void insertOvertimeRecord(OverTime overTime){
        String sql = "insert ?(user_id, date) values(?,?)";
        jdbcTemplate.update(sql, overTimeTable, overTime.getUserId(), overTime.getDate());
    }
    
    public List<OverTime> queryOvertimeById(int id){
        String sql = "select * from ? where id=?";
        List<OverTime> results = jdbcTemplate.query(sql, new OverTimeRowMapper(), overTimeTable, id);
        return results;
    }
}