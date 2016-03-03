package me.afsd.service;

import me.afsd.dao.CompanyRepository;
import me.afsd.dao.base.BaseRepository;
import me.afsd.domain.Company;
import me.afsd.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * User: afsd
 * Date: 2016/3/3
 * Time: 16:50
 */
@Service
public class CompanyService extends BaseService<Company,Long> {
    @Qualifier("companyRepository")
    @Autowired
    private CompanyRepository baseRepository;

    @Override
    protected BaseRepository<Company, Long> getRepository() {
        return baseRepository;
    }
}
