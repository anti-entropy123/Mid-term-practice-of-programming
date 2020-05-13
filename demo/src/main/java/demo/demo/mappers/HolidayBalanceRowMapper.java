package demo.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import demo.demo.entity.HolidayBalance;

public class HolidayBalanceRowMapper implements RowMapper<HolidayBalance>{
    @Override
    public HolidayBalance mapRow(ResultSet rs, int i) throws SQLException{
        HolidayBalance holidayBalance = new HolidayBalance();
        holidayBalance.setId(rs.getString("id"));
        holidayBalance.setType(rs.getString("type"));
        holidayBalance.setBalance(rs.getInt("balance"));
        
        return holidayBalance;
    }
}