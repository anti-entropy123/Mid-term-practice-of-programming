package demo.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import demo.demo.entity.Record;

public class RecordRowMapper implements RowMapper<Record>{
    @Override
    public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
        Record record = new Record();
        record.setId(rs.getString("user_id"));
        record.setDate(rs.getString("date"));

        return record;
    }
}