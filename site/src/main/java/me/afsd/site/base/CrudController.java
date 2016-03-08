package me.afsd.site.base;

import me.afsd.dao.base.query.Query;
import me.afsd.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * User: afsd
 * Date: 2016/3/7
 * Time: 14:43
 */
public abstract class CrudController<T, ID extends Serializable,F extends BaseForm<T,ID>,Q extends Query<T>> extends BaseController{
    @Autowired(required = false)
    protected BaseService<T, ID> service;

    private static final String ATTRIBUTOR_FORM = "viewForm";
    private static final String ATTRIBUTOR_PAGE = "page";
    private static final String ATTRIBUTOR_QUERY = "query";

    public String add(Model model) {
        return this.add(model, "添加" + service.getDomainName());
    }

    protected String add(Model model, String title) {
        return this.add(model, title, Boolean.TRUE);
    }

    protected String add(Model model, String title, Boolean goBack) {
        this.setPageTitle(model, title);
        if (goBack) this.enableGoBack(model);
        model.addAttribute(ATTRIBUTOR_FORM, getBlankForm());
        return getFormUrl();
    }

    public BaseDataResponse add(@RequestBody @Valid F form, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return BaseDataResponse.validationFail(bindingResult);
        service.save(form.as());
        return BaseDataResponse.ok().action(CrudAction.ADD_SUCCESS(service.getDomainName(), form.getName(), getListUrl()));
    }

    public String edit(@PathVariable ID id, Model model) {
        return this.edit(id, "编辑" + service.getDomainName(), model);
    }

    protected String edit(ID id, String title, Model model) {
        return this.edit(id, title, model, Boolean.TRUE);
    }

    protected String edit(ID id, String title, Model model, Boolean goBack) {
        this.setPageTitle(model, title);
        if (goBack) this.enableGoBack(model);
        T t = service.getOne(id);
        model.addAttribute(ATTRIBUTOR_FORM, getForm(t));
        return getFormUrl();
    }

    public BaseDataResponse edit(F form, BindingResult bindingResult) {
        T persist = service.getOne(form.getId());
        if (persist == null)
            return BaseDataResponse.nodata();
        if (bindingResult.hasErrors())
            return BaseDataResponse.validationFail(bindingResult);
        T t = form.as();
        service.save(t);
        return BaseDataResponse.ok().action(CrudAction.EDIT_SUCCESS(service.getDomainName(), form.getName(), getListUrl()));
    }

    public BaseDataResponse delete(@PathVariable ID id) {
        T t = service.getOne(id);
        if (t == null)
            return BaseDataResponse.nodata();
        service.delete(t);
        return BaseDataResponse.ok().action(CrudAction.DELETE_SUCCESS(service.getDomainName(), id + "", getListUrl()));
    }

    public String list(Model model, Q query) {
        return this.list(model, service.getDomainName() + "列表", query);
    }

    protected String list(Model model, String title, Query<T> query) {
        return this.list(model, title, Boolean.FALSE, query);
    }

    protected String list(Model model, String title, Boolean goBack, Query<T> query) {
        this.setPageTitle(model, title);
        if (goBack) this.enableGoBack(model);
        Page<T> page = service.findAll(query);
        model.addAttribute(ATTRIBUTOR_QUERY, query);
        model.addAttribute(ATTRIBUTOR_PAGE, page);
        return getListUrl();
    }

    public BaseDataResponse select(Q query) {
        Page<T> page = service.findAll(query);
        if (page.getTotalElements() < 1) {
            return BaseDataResponse.nodata();
        } else {
            return BaseDataResponse.ok().data(page.getContent());
        }
    }

    protected abstract BaseForm<T, ID> getBlankForm();

    protected abstract BaseForm<T, ID> getForm(T t);

    protected abstract String getListUrl();

    protected abstract String getFormUrl();
}
