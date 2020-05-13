package demo.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import demo.demo.entity.LeaveApplication;

public class LeaveApplicationRowMapper implements RowMapper<LeaveApplication>{
    @Override
    public LeaveApplication mapRow(ResultSet rs, int i) throws SQLException{
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setApplicationId(rs.getString("application_id"));
        leaveApplication.setStartTime(rs.getString("startTime"));
        leaveApplication.setEndTime(rs.getString("endTime"));
        leaveApplication.setUserId(rs.getString("user_id"));
        leaveApplication.setReason(rs.getString("user_reason"));
        leaveApplication.setType(rs.getString("type"));
        return leaveApplication;
    }
}