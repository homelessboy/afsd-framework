package me.afsd.dao;

import me.afsd.domain.Company;
import me.afsd.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * User: afsd
 * Date: 2016/1/26
 * Time: 17:49
 */
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:springmvc-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ICompanyDaoTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

//    @Test
//    public void testFindAll1() {
//        Specification<Company> specification=new Specification<Company>(){
//            @Override
//            public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                Path<String> namePath= root.join("createdBy").get("id");
//                query.where(cb.equal(namePath,1l));
//                return query.getRestriction();
//            }
//        };
//        Page<Company> companys = companyRepository.findAll(specification, new PageRequest(0, 10));
//        companys.getContent();
//    }
//
//    @Test
//    public void testFindQuery(){
//        CompanyQuery query=new CompanyQuery();
//        query.setName("hello");
////        query.setUserId(6l);
////        query.setSort(new Sort(new Sort.Order(Sort.Direction.DESC,"id")));
//        Page<Company> companys=companyRepository.findAll(query.asSpecification(), query.getPageable());
//
//        companys.getContent();
//    }

    @Test
    public void testService(){

        List<Company> companyList=companyService.findAll();
        System.out.println("in");
    }

}