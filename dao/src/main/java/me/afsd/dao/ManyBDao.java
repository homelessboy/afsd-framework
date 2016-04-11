package me.afsd.dao;

import me.afsd.dao.base.BaseRepository;
import me.afsd.domain.ManyB;
import org.springframework.stereotype.Repository;

/**
 * @author: afsd
 * Date: 2016/4/11
 * Time: 20:54
 * Brif:
 */
@Repository
public interface ManyBDao extends BaseRepository<ManyB,Long> {
}
