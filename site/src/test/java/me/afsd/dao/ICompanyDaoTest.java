package me.afsd.dao;

import me.afsd.domain.Company;
import me.afsd.service.CompanyService;
import me.afsd.site.hadoop.Hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.URI;
import java.util.List;

/**
 * User: afsd
 * Date: 2016/1/26
 * Time: 17:49
 */
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:springmvc-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ICompanyDaoTest {

    @Qualifier("companyRepository")
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private Configuration hadoopConfiguration;

    @Autowired
    private Hadoop hadoop;

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
        company.setAddress("天堂路8号");
        companyService.save(company);
        System.out.println("in");
    }

    @Test
    public void testFindByName(){
        List<Company> companies=companyRepository.findByName("test4");
        System.out.println(companies.size());
    }

    @Test
    public void testHBase() throws IOException {
        System.out.println(hadoopConfiguration.get("fs.defaultFS"));

        hadoop.getDateNodeHost();
//        Hadoop.createNewHDFSFile("/tmp/hello","hello from java");
    }
}