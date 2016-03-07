package me.afsd.controller;

import me.afsd.domain.Company;
import me.afsd.site.base.BaseDataResponse;
import me.afsd.site.base.BaseForm;
import me.afsd.site.base.CrudAction;
import me.afsd.site.base.CrudController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: afsd
 * Date: 2016/1/21
 * Time: 14:26
 */
@Controller
@RequestMapping("")
public class IndexController extends CrudController<Company,Long> {

    @RequestMapping("")
    public String index(){
        return "hello";
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(Model model) {
        superAdd(model);
        return "/form";
    }


    @Override
    protected BaseForm<Company,Long> getBlankForm() {
        return new CompanyForm();
    }

    @Override
    protected BaseForm<Company, Long> getForm(Company company) {
        return new CompanyForm(company);
    }

    class CompanyForm extends BaseForm<Company,Long>{


        private String name;

        public CompanyForm(){}

        public CompanyForm(Company company) {
            setEntity(company);
        }

        @Override
        public void setEntity(Company company) {
            this.name=company.getName();
            this.id=company.getId();
        }

        @Override
        public Company as() {
            return null;
        }
    }
}
