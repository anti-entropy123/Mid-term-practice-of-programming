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

    /* 
        作用:   根据 id 返回一条 member 记录, 并返回 Member对象
        输入:   用户id
        返回:   该用户所有个人数据, 包括密码等
    */
    public Member qureyUser(int id){
        String sql = "select * from ? where id = ?";
        Member member = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), memberTable, Integer.valueOf(id));
        return member;
    } 

    /* 
        作用:   根据 id 返回对应的 password 数据
        输入:   用户 id
        返回:   该用户的密码
    */
    public String qureyUserPassword(int id){
        String sql = "select password from ? where id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, memberTable, Integer.valueOf(id));
        return (String)result.getOrDefault("password", "null");
    }

    /* 
        作用:   查询所有员工的所有信息
        输入:   无 
        返回:   所有用户的所有数据
    */
    public List<Member> qureyMembersList(){
        String sql = "select * from ?";
        List<Member> members = jdbcTemplate.query(sql, new MemberRowMapper(), memberTable);
        return members;
    }

    /* 
        作用:   增加一名员工, (目前来看没有什么用)
        输入:   员工实例对象
        返回:   无
    */
    public void insertMember(Member m){
        String sql = "insert ?(id, password, name, title) values(?,?,?,?)";
        jdbcTemplate.update(sql, memberTable, m.getId(), m.getPassword(), m.getName(), m.getTitle());
    }
}
