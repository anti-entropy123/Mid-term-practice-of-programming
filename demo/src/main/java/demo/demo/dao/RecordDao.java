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

    // 插入一条记录
    public void insertSignInRecord(Record r){
        String sql = "insert ?(id, date, status) values(?, ?, ?)";
        jdbcTemplate.update(sql, recordTable, r.getId() , r.getDate(), r.getStatus());
    }

    // 根据用户id查询打卡记录
    public List<Record> qureyPersonRecords(int id){
        String sql = "select * from ? where id=?";
        List<Record> records = jdbcTemplate.query(sql, new RecordRowMapper(), recordTable, id);
        return records;
    }
        
    // 查询所有人的所有打卡记录
    public List<Record> qureyAllRecord(){
        String sql = "select * from ?";
        List<Record> records = jdbcTemplate.query(sql, new RecordRowMapper(), recordTable);
        return records;
    }
}