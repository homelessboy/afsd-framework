package me.afsd.example.dao;

import me.afsd.dao.base.BaseRepository;
import me.afsd.example.domain.ManyB;
import org.springframework.stereotype.Repository;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@Repository
public interface ManyBDao extends BaseRepository<ManyB,Long> {
}
