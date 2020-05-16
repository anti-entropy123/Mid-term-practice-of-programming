package demo.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import demo.demo.entity.OverTime;

import demo.demo.mappers.OverTimeRowMapper;

public class OverTimeDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private String overTimeTable = "overtime";

    public void insert_overtime_record(OverTime overTime){
        String sql = "insert ?(user_id, date) values(?,?)";
        jdbcTemplate.update(sql, overTimeTable, overTime.getUserId(), overTime.getDate());
    }
    
    public List<OverTime> query_overtime_by_id(int id){
        String sql = "select * from ? where id=?";
        List<OverTime> results = jdbcTemplate.query(sql, new OverTimeRowMapper(), overTimeTable, id);
        return results;
    }
}