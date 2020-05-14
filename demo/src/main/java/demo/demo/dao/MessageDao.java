package demo.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import demo.demo.entity.Message;
import demo.demo.mappers.MessageRowMapper;

@Service
public class MessageDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private String MessageTable = "message";

    public List<Message> qureyPersonMessage(int id){
        String sql = "select * from ? where user_id = ?";
        List<Message> results = jdbcTemplate.query(sql, new MessageRowMapper(), MessageTable);
        return results;
    }

    public void insertMessage(Message m){
        String sql = "insert ?(user_id, application_id) values(?,?)";
        jdbcTemplate.update(sql, MessageTable, m.getUserId(), m.getApplicationId());
    } 
}