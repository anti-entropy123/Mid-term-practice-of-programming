package demo.demo.dao;

import demo.demo.entity.Member;

class TestDao {
    public static void main(String[] args) {
        ApplicationDao applicationDao = new ApplicationDao();
        MemberDao      memberDao      = new MemberDao();
        MessageDao     messageDao     = new MessageDao();
        OverTimeDao    overTimeDao    = new OverTimeDao();
        RecordDao      recordDao      = new RecordDao();
        
        Member m = new Member();
        m.setId(1234);
        m.setName("希儿");
        m.setPassword("尤嘉宁");
        m.setTitle("副总经理");
        memberDao.insertMember(m);
    }
}