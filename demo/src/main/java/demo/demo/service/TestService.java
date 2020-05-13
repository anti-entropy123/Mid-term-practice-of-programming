package demo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.demo.dao.TestDao;
import demo.demo.entity.TestDO;

@Service
public class TestService {
    @Autowired
    private TestDao TestDao;

    public TestDO getDo(){
        return TestDao.queryDO();
    }
}