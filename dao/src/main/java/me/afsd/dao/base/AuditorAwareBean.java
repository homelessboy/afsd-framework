package me.afsd.dao.base;

import me.afsd.dao.UserDao;
import me.afsd.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

/**
 * User: afsd
 * Date: 2016/2/13
 * Time: 11:05
 */
public class AuditorAwareBean implements AuditorAware<User> {
    @Autowired
    private UserDao userDao;

    @Override
    public User getCurrentAuditor() {
        System.out.println("in");
        User user=userDao.getOne(2l);
        return null;
    }
}
