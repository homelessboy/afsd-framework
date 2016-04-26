//import me.afsd.example.dao.ManyADao;
//import me.afsd.example.dao.ManyBDao;
//import me.afsd.example.domain.ManyA;
//import me.afsd.example.domain.ManyB;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * User: afsd
// * Date: 2016/1/21
// * Time: 15:49
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:applicationContext.xml"})
//public class test {
//    @Autowired
//    private ManyADao manyADao;
//
//    @Autowired
//    private ManyBDao manyBDao;
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testMany2Many(){
//        ManyA manyA1=new ManyA("test_a_1");
//        ManyA manyA2=new ManyA("test_a_2");
//        ManyA manyA3=new ManyA("test_a_3");
//
//        ManyB manyB1=new ManyB("test_b_1");
//        ManyB manyB2=new ManyB("test_b_2");
//        ManyB manyB3=new ManyB("test_b_3");
//
//        manyA1=manyADao.save(manyA1);
//        manyA2=manyADao.save(manyA2);
//        manyA3=manyADao.save(manyA3);
//
//        manyB1=manyBDao.save(manyB1);
//        manyB2=manyBDao.save(manyB2);
//        manyB3=manyBDao.save(manyB3);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testMany2Many2(){
//        ManyA manyA1=manyADao.findOne(1l);
//        ManyA manyA2=manyADao.findOne(2l);
//        ManyB manyB1=manyBDao.findOne(1l);
//        ManyB manyB2=manyBDao.findOne(2l);
//        List<ManyB> manyBList = new ArrayList<>();
//        manyBList.add(manyB1);
//        manyBList.add(manyB2);
//        manyA1.setManyBs(manyBList);
//        manyA1=manyADao.save(manyA1);
//        System.out.println();
//    }
//
//    @Test
//    @Rollback(false)
//    @Transactional
//    public void testMany2Many1(){
//        ManyB manyB=manyBDao.findOne(1l);
//        ManyB manyB1 = new ManyB();
//        manyB1.setId(manyB.getId());
//        manyB1.setVersion(manyB.getVersion());
//        manyB1.setName("test");
//        manyB1=manyBDao.save(manyB1);
//    }
//
//    @Test
//    public void test(){
//        System.out.println(-1%-2);
//    }
//}
