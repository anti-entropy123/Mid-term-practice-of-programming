package demo.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import demo.demo.entity.OverTime;

public class OverTimeRowMapper implements RowMapper<OverTime> {
    @Override
    public OverTime mapRow(ResultSet rs, int i) throws SQLException{
        OverTime overTime = new OverTime();
        overTime.setUserId(rs.getInt("user_id"));
        overTime.setDate(rs.getString("date"));
        
        return overTime;
    }
}