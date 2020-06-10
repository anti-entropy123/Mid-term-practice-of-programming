package demo.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import demo.demo.entity.Member;
import demo.demo.mappers.MemberRowMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        String sql = "select * from "+ memberTable + " where id = ?";
        Member member = null;
        try{
            member = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), id);
        }catch(EmptyResultDataAccessException e){
        }
        return member;
    } 
    /* 
        作用:   根据 id 返回 该id对应的 username.
        输入:   用户id
        返回:   该用户所有个人数据, 包括密码等
    */
    public String qureyUsernameById(int id){
        String sql = "select name from "+ memberTable + " where id = ?";
        try{
            Map<String, Object> name = jdbcTemplate.queryForMap(sql, id);
            return (String)name.getOrDefault("name", "null");
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    /* 
        作用:   根据 id 返回对应的 password 数据
        输入:   用户 id
        返回:   该用户的密码
    */
    public String qureyUserPassword(int id){
        String sql = "select password from " + memberTable + " where id = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
        return (String)result.getOrDefault("password", "null");
    }

    /* 
        作用:   查询所有员工的所有信息
        输入:   无 
        返回:   所有用户的所有数据
    */
    public List<Member> qureyMembersList(){
        String sql = "select * from " + memberTable;
        List<Member> members = jdbcTemplate.query(sql, new MemberRowMapper());
        return members;
    }

    /* 
        作用:   增加一名员工
        输入:   员工实例对象
        返回:   新增的员工的id
    */
    public int insertMember(Member m){
        String sql = "insert " + memberTable + " (password, name, title, sex) values(?,?,?,?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getPassword());
            ps.setString(2, m.getName());
            ps.setString(3, m.getTitle());
            ps.setString(4, m.getSex());
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
