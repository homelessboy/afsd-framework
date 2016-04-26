package me.afsd.example.site.form;

import me.afsd.example.domain.Company;
import me.afsd.site.base.BaseForm;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public class CompanyForm extends BaseForm<Company,Long>{
    private String address;

    public CompanyForm(){}

    public CompanyForm(Company company) {
        setEntity(company);
    }

    @Override
    public void setEntity(Company company) {
        this.name=company.getName();
        this.id=company.getId();
        this.address=company.getAddress();
    }

    @Override
    public Company as() {
        Company company=new Company();
        company.setAddress(address);
        company.setName(name);
        company.setId(id!=null&&id>0?id:null);
        return company;
    }
}
