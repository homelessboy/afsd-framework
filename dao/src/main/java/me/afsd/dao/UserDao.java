package me.afsd.dao;

import me.afsd.dao.base.BaseRepository;
import me.afsd.domain.User;
import org.springframework.stereotype.Repository;

/**
 * User: afsd
 * Date: 2016/2/13
 * Time: 11:39
 */
@Repository
public interface UserDao extends BaseRepository<User,Long> {
}
