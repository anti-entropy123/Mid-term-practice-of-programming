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
    private String memberTable = "member";

    // 根据 id 返回一条 member 记录, 并返回Member对象
    public Member qureyUser(int id){
        String sql = "select * from ? where id = ?";
        Member member = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), memberTable, Integer.valueOf(id));
        return member;
    } 

    // 根据 id 返回对应的 password 数据
    public String qureyUserPassword(int id){
        String sql = "select password from ? where id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, memberTable, Integer.valueOf(id));
        return (String)result.getOrDefault("password", "null");
    }

    // 查询所有员工的所有信息
    public List<Member> qureyMembersList(){
        String sql = "select * from ?";
        List<Member> members = jdbcTemplate.query(sql, new MemberRowMapper(), memberTable);
        return members;
    }

    // 增加一名员工
    public void insertMember(Member m){
        String sql = "insert ?(id, password, name, title) values(?,?,?,?)";
        jdbcTemplate.update(sql, memberTable, m.getId(), m.getPassword(), m.getName(), m.getTitle());
    }
}
