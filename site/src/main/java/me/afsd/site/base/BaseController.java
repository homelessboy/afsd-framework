package me.afsd.site.base;

import me.afsd.service.base.BaseService;
import me.afsd.site.base.BaseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.ui.Model;

import java.io.Serializable;

/**
 * User: afsd
 * Date: 2016/3/7
 * Time: 14:35
 */
public class BaseController {
    @Autowired
    MessageSource messageSource;

    private static final String ATTRIBUTE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_GOBACK = "pageGoBack";

    public MessageSource getMessageSource(){
        return messageSource;
    }

    protected final String getMessage(String key) {
        try {
            return messageSource.getMessage(key, null, null);
        } catch (NoSuchMessageException e) {
            return "";
        }
    }

    protected void setPageTitle(Model model, String title) {
        model.addAttribute(this.ATTRIBUTE_TITLE,title);
    }

    protected void enableGoBack(Model model) {
        model.addAttribute(this.ATTRIBUTE_GOBACK, true);
    }

}
