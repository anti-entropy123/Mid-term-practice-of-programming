package demo.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import demo.demo.entity.TestDO;

@Service
public class TestDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    public TestDO queryDO(){
        return new TestDO(1,"yjn");
    }
}