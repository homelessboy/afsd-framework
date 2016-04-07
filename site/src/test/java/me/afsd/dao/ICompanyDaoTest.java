package me.afsd.dao;

import me.afsd.domain.Company;
import me.afsd.domain.Employee;
import me.afsd.service.CompanyService;
import me.afsd.service.base.BaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * User: afsd
 * Date: 2016/1/26
 * Time: 17:49
 */
@Transactional
@Rollback(false)
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:springmvc-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ICompanyDaoTest {

    @Autowired
    private CompanyDao companyDao;

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
        CompanyQuery query=new CompanyQuery();
        List<Company> companyList=companyService.findAll();
        System.out.println("in"+companyService.getDomainName());
    }

    @Test
    public void testSave(){
        Company company=new Company();
        company.setName("test4");
        company.setAddress("天堂路81号");
        Employee employee1=new Employee();
        employee1.setCompany(company);
        employee1.setName("13");
        Employee employee2=new Employee();
        employee2.setName("13");
        employee2.setCompany(company);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        company.setEmployeeList(employees);

        companyService.save(company);
        System.out.println("in");
    }

    @Test
    public void testdd(){
        Company company=companyDao.findOne(10l);
        company.setName(company.getName()+"1");
        company.getEmployeeList().remove(0);
        companyDao.save(company);
    }

    @Test
    public void testFind(){
        List<Company> companies=companyDao.findAll((root, query, cb) -> {
            Predicate predicate=cb.like(root.join("employeeList").get("name"),"%13%");
            query.distinct(true);
         //   query.multiselect(root.get("id"));
            return predicate;
        });
        System.out.println(companies.size());
    }

    @Test
    public void testFindByName(){
        List<Company> companies=companyDao.findByName("test4");
        System.out.println(companies.size());
    }

    @Test
    public void test(){
        Optional<Company> companyOptional=Optional.ofNullable(null);
        try {
            Company company = companyOptional.orElseThrow(() -> new BaseException("空指针"));
        }catch (BaseException e){
            System.out.println(e.getMessage());
        }
        System.out.println(-1%-2);
    }
}