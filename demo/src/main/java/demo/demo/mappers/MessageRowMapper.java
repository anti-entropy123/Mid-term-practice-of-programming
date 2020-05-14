package demo.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import demo.demo.entity.Message;

public class MessageRowMapper implements RowMapper<Message>{
    @Override
    public Message mapRow(ResultSet rs, int i) throws SQLException{
        Message message = new Message();
        message.setApplicationId(rs.getInt("application_id"));
        message.setUserId(rs.getInt("user_id"));

        return message;
    }
}