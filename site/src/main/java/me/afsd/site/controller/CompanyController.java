package me.afsd.site.controller;

import me.afsd.dao.CompanyQuery;
import me.afsd.domain.Company;
import me.afsd.site.form.CompanyForm;
import me.afsd.site.base.BaseDataResponse;
import me.afsd.site.base.BaseForm;
import me.afsd.site.base.CrudController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * User: afsd
 * Date: 2016/3/8
 * Time: 14:23
 */
@Controller
@RequestMapping("company")
public class CompanyController extends CrudController<Company,Long,CompanyForm,CompanyQuery> {
    private static String LIST_URL="/company/list";
    private static String FORM_URL="/company/form";


    @Override
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(Model model) {
        return super.add(model);
    }

    @Override
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public BaseDataResponse add(@RequestBody @Valid CompanyForm form, BindingResult bindingResult) {
        return super.add(form,bindingResult);
    }

    @Override
    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        return super.edit(id, model);
    }

    @Override
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public BaseDataResponse edit(CompanyForm form, BindingResult bindingResult) {
        return super.edit(form,bindingResult);
    }

    @Override
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public BaseDataResponse delete(@PathVariable Long aLong) {
        return super.delete(aLong);
    }

    @Override
    @RequestMapping(value = {"","list"},method = RequestMethod.GET)
    public String list(Model model, CompanyQuery query) {
        return super.list(model,query);
    }

    @Override
    @RequestMapping(value = "select",method = RequestMethod.POST)
    @ResponseBody
    public BaseDataResponse select(CompanyQuery query) {
        return super.select(query);
    }

    @Override
    protected BaseForm<Company, Long> getBlankForm() {
        return new CompanyForm();
    }

    @Override
    protected BaseForm<Company, Long> getForm(Company company) {
        return new CompanyForm(company);
    }

    @Override
    protected String getListUrl() {
        return LIST_URL;
    }

    @Override
    protected String getFormUrl() {
        return FORM_URL;
    }
}
