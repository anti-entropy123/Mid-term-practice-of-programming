package demo.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import demo.demo.dao.MessageDao;
import demo.demo.entity.Message;

public class MessageDaoTest extends TestBase{
    @Autowired MessageDao messageDao;

    @Test
    public void testInsertMessage(){
        //messageDao.insertMessage(new Message(123, 1));
        System.out.println(messageDao.qureyPersonMessage(123));
    }
}