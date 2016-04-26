package me.afsd.example.dao;

import me.afsd.dao.base.BaseNameResitory;
import me.afsd.example.domain.Company;
import org.springframework.stereotype.Repository;


/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@Repository
public interface CompanyDao extends BaseNameResitory<Company, Long> {
//    Company findById(Long id);
}
