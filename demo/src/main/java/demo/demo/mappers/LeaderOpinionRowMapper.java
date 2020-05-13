package demo.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import demo.demo.entity.LeaderOpinion;

public class LeaderOpinionRowMapper implements RowMapper<LeaderOpinion>{
    @Override
    public LeaderOpinion mapRow(ResultSet rs, int i) throws SQLException{
        LeaderOpinion leaderOpinion = new LeaderOpinion();
        leaderOpinion.setApplicationId(rs.getString("application_id"));
        leaderOpinion.setLeaderId(rs.getString("leader_id"));
        leaderOpinion.setResult(rs.getString("result"));
        leaderOpinion.setOpinion(rs.getString("opinion"));

        return leaderOpinion;
    }
}