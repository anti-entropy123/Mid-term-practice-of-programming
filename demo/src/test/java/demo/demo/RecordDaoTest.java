package demo.demo;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import demo.demo.dao.RecordDao;
import demo.demo.entity.Record;


public class RecordDaoTest extends TestBase{
    @Autowired
    private RecordDao recordDao;

    @Test
    public void testInsertRecord(){
        recordDao.insertSignInRecord(new Record(123, "2020/5/17", "signed"));
    }

    @Test
    public void testQureyRecord(){
        List<Record> rs = recordDao.qureyPersonRecords(123);
        for(Record r: rs){
            System.out.println(String.format("%s %s %s", r.getId(), r.getDate(), r.getStatus()));
        }
        rs = recordDao.qureyAllRecord();
        for(Record r: rs){
            System.out.println(String.format("%s %s %s", r.getId(), r.getDate(), r.getStatus()));
        }
    }

    @Test
    public void testQureyRecordByDate(){
        System.out.println(recordDao.qureyRecordByDate(123, "2020/05/17").getStatus());
    }
}