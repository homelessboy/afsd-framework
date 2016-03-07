package me.afsd.site.base;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.afsd.dao.base.query.Query;
import me.afsd.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.io.Serializable;

/**
 * User: afsd
 * Date: 2016/3/7
 * Time: 14:43
 */
public abstract class CrudController<T, ID extends Serializable> extends BaseController {
    @Autowired
    protected BaseService<T,ID> service;

    private static final String ATTRIBUTOR_FORM="viewForm";
    private static final String ATTRIBUTOR_PAGE="page";
    private static final String ATTRIBUTOR_QUERY="query";

    protected void superAdd(Model model){
        this.superAdd(model, "添加" + service.getDomainName());
    }

    protected void superAdd(Model model,String title){
        this.superAdd(model, title, Boolean.TRUE);
    }

    protected void superAdd(Model model,String title,Boolean goBack) {
        this.setPageTitle(model, title);
        if(goBack) this.enableGoBack(model);
        model.addAttribute(ATTRIBUTOR_FORM, getBlankForm());
    }

    protected BaseDataResponse add(BaseForm<T,ID> form,BindingResult bindingResult,BaseAction action){
        if(bindingResult.hasErrors())
            return BaseDataResponse.validationFail(bindingResult);
        service.save(form.as());
        return BaseDataResponse.ok().action(action);
    }

    protected void superEdit(Model model,ID id,BaseAction action) {
        this.superEdit(model, "编辑" + service.getDomainName(), id, action);
    }

    protected void superEdit(Model model, String title,ID id,BaseAction action) {
        this.superEdit(model, title, Boolean.TRUE, id, action);
    }

    protected void superEdit(Model model, String title, Boolean goBack,ID id,BaseAction action) {
        this.setPageTitle(model, title);
        if(goBack) this.enableGoBack(model);
        T t=service.getOne(id);
        model.addAttribute(ATTRIBUTOR_FORM,getForm(t));
    }

    protected BaseDataResponse edit(BaseForm<T,ID> form,BindingResult bindingResult,BaseAction action){
        T persist=service.getOne(form.getId());
        if(persist==null)
            return BaseDataResponse.nodata();
        if(bindingResult.hasErrors())
            return BaseDataResponse.validationFail(bindingResult);
        T t=form.as();
        service.save(t);
        return BaseDataResponse.ok().action(action);
    }

    protected BaseDataResponse delete(ID id,BaseAction action) {
        T t = service.getOne(id);
        if(t==null)
            return BaseDataResponse.nodata();
        service.delete(t);
        return BaseDataResponse.ok().action(action);
    }

    protected void list(Model model,Query<T> query){
        this.list(model,service.getDomainName()+"列表",query);
    }

    protected void list(Model model,String title,Query<T> query) {
        this.list(model, title, Boolean.FALSE, query);
    }

    protected void list(Model model,String title,Boolean goBack,Query<T> query) {
        this.setPageTitle(model, title);
        if(goBack) this.enableGoBack(model);
        Page<T> page=service.findAll(query);
        model.addAttribute(ATTRIBUTOR_QUERY,query);
        model.addAttribute(ATTRIBUTOR_PAGE,page);
    }

    protected BaseDataResponse select(Query<T> query) {
        Page<T> page = service.findAll(query);
        if(page.getTotalElements()<1){
            return BaseDataResponse.nodata();
        }else{
            return BaseDataResponse.ok().data(page.getContent());
        }
    }

    protected abstract BaseForm<T,ID> getBlankForm();

    protected abstract BaseForm<T,ID> getForm(T t);
}
