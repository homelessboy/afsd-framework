package me.afsd.dao;

import me.afsd.dao.base.BaseRepository;
import me.afsd.domain.Company;
import org.springframework.stereotype.Repository;


/**
 * User: afsd
 * Date: 2016/1/26
 * Time: 17:41
 */
@Repository
public interface CompanyRepository extends BaseRepository<Company, Long> {
    Company findByName(String name);

    Company findById(Long id);
}
