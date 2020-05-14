package demo.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import demo.demo.entity.Member;
import demo.demo.mappers.MemberRowMapper;

import java.util.List;
import java.util.Map;

@Service
public class MemberDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    // 根据 id 返回一条 member 记录, 并返回Member对象
    public Member qureyUser(String id){
        String sql = "select * from member where id = ?";
        Member member = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), id);
        return member;
    } 

    // 根据 id 返回对应的 password 数据
    public String qureyUserPassword(String id){
        String sql = "select password from member where id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
        return (String)result.getOrDefault("password", "null");
    }

    // 查询所有员工的所有信息
    public List<Member> qureyMembersList(){
        String sql = "select * from member";
        List<Member> members = jdbcTemplate.query(sql, new MemberRowMapper());
        return members;
    }
}
