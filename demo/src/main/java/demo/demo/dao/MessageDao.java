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

    /*
        作用:   查询一个员工的个人消息(但是该对象中只有
                用户id和申请id, 消息的具体内容需要你自己再去查)
        输入:   个人id
        返回:   消息对象列表
    */
    public List<Message> qureyPersonMessage(int id){
        String sql = "select * from ? where user_id = ?";
        List<Message> results = jdbcTemplate.query(sql, new MessageRowMapper(), MessageTable);
        return results;
    }

    /*
        作用:   增加一个员工消息
        输入:   消息的实例对象
        返回:   无
    */
    public void insertMessage(Message m){
        String sql = "insert ?(user_id, application_id) values(?,?)";
        jdbcTemplate.update(sql, MessageTable, m.getUserId(), m.getApplicationId());
    } 
}