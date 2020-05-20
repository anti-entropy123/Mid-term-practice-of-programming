package demo.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import demo.demo.dao.MemberDao;
import demo.demo.entity.Member;

public class MemberDaoTest extends TestBase{
    @Autowired MemberDao memberDao;
    @Test
    public void testInsertMember(){
        Member m = new Member();
        m.setId(123);
        m.setName("希儿");
        m.setPassword("尤嘉宁");
        m.setTitle("副总经理");
        memberDao.insertMember(m);
    }

    @Test
    public void testQureyMemberById(){
        Member m = memberDao.qureyUser(123);
        assertEquals("希儿", m.getName(), "姓名有误:" + m.getName());
        assertEquals("尤嘉宁", m.getPassword(), "密码有误" + m.getPassword());
        assertEquals("副总经理", m.getTitle(), "title 有误:" + m.getTitle());
    }

    @Test
    public void testQureyPassById(){
        String password = memberDao.qureyUserPassword(123);
        assertEquals("尤嘉宁", password, "密码有误");
    }
}