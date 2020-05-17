package demo.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import demo.demo.entity.Record;
import demo.demo.mappers.RecordRowMapper;

@Service
public class RecordDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    String recordTable = "record";

    /* 
        作用:   插入一条签到记录
        输入:   签到记录实例
        返回:   无
    */
    public void insertSignInRecord(Record r){
        String sql = "insert " + recordTable + "(id, date, status) values(?, ?, ?)";
        jdbcTemplate.update(sql, r.getId() , r.getDate(), r.getStatus());
    }

    /* 
        作用:   根据用户id查询打卡记录
        输入:   某用户的id
        返回:   该用户所有的打卡记录
    */
    public List<Record> qureyPersonRecords(int id){
        String sql = "select * from " + recordTable + " where id=?";
        List<Record> records = jdbcTemplate.query(sql, new RecordRowMapper(), id);
        return records;
    }
        
    /* 
        作用:   查询所有人的所有打卡记录
        输入:   无
        返回:   所有员工所有的打卡记录
    */
    public List<Record> qureyAllRecord(){
        String sql = "select * from " + recordTable;
        List<Record> records = jdbcTemplate.query(sql, new RecordRowMapper());
        return records;
    }
}