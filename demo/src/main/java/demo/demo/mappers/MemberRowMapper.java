package demo.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import demo.demo.entity.Member;

public class MemberRowMapper implements RowMapper<Member>{
    @Override
    public Member mapRow(ResultSet rs, int i) throws SQLException{
        Member member = new Member();
        member.setId(rs.getString("id"));
        member.setPassword(rs.getString("password"));
        member.setName(rs.getString("name"));
        member.setTitle(rs.getString("title"));
        
        return member;
    }
}