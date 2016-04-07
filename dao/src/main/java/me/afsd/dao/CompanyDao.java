package me.afsd.dao;

import me.afsd.dao.base.BaseNameResitory;
import me.afsd.dao.base.BaseRepository;
import me.afsd.domain.Company;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * User: afsd
 * Date: 2016/1/26
 * Time: 17:41
 */
@Repository
public interface CompanyDao extends BaseNameResitory<Company, Long> {
//    Company findById(Long id);
}
