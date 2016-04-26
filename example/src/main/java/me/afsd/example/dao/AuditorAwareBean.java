//package me.afsd.example.dao;
//
//import me.afsd.example.domain.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.AuditorAware;
//
///**
// * @author  XuHui(416422546@qq.com)
// * @version 0.0.1
// */
//public class AuditorAwareBean implements AuditorAware<User> {
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public User getCurrentAuditor() {
//        System.out.println("in");
//        User user=userDao.getOne(2l);
//        return null;
//    }
//}
