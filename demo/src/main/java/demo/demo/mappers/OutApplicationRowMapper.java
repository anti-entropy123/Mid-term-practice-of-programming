package demo.demo.mappers;

import org.springframework.jdbc.core.RowMapper;

import demo.demo.entity.OutApplication;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OutApplicationRowMapper implements RowMapper<OutApplication> {

    @Override
    public OutApplication mapRow(ResultSet rs,int i) throws SQLException {
        OutApplication outApplication = new OutApplication();
        outApplication.setApplicationId(rs.getString("application_id"));
        outApplication.setStartTime(rs.getString("startTime"));
        outApplication.setEndTime(rs.getString("endTime"));
        outApplication.setUserId(rs.getString("user_id"));
        outApplication.setReason(rs.getString("user_reason"));
        return outApplication;
    }
}
