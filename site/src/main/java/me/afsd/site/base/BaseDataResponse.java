package me.afsd.site.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;

import java.io.Serializable;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public class BaseDataResponse implements Serializable, Cloneable {
    private static final long serialVersionUID = 24163388165090429L;

    static{
        JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
    }

    @JSONField(ordinal = 1,name = "code")
    private int code;

    @JSONField(ordinal = 2,name = "msg")
    private String msg;

    @JSONField(ordinal = 3,name = "detail")
    private String detail;

    @JSONField(ordinal = 4,name = "jumpUrl")
    private String jumpUrl;

    @JSONField(ordinal = 5,name = "data")
    private Object data;

    @JSONField(ordinal = 6,name = "html")
    private String html;

    @JSONField(serialize = false)
    private MessageSource messageSource;

    private BaseDataResponse(int code) {
        this.code=code;
    }

    public static final BaseDataResponse ok() {
        return new BaseDataResponse(0);
    }

    public static final BaseDataResponse fail() {
        return new BaseDataResponse(1);
    }

    public static final BaseDataResponse nodata() {
        return new BaseDataResponse(2);
    }

    public static final BaseDataResponse validationFail(BindingResult bindingResult) {
        BaseDataResponse baseDataResponse = new BaseDataResponse(100);
        baseDataResponse.setMsg("表单不正确");
        baseDataResponse.setDetail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        return baseDataResponse;
    }

    public BaseDataResponse messageSource(MessageSource messageSource){
        this.messageSource=messageSource;
        return this;
    }

    public BaseDataResponse msg(String msg) {
        if(messageSource!=null &&msg.contains("."))
            this.msg = messageSource.getMessage(msg, null, null);
        else
            this.msg=msg;
        return this;
    }

    public BaseDataResponse detail(String detail){
        if(messageSource!=null && msg.contains("."))
            this.detail = messageSource.getMessage(detail, null, null);
        else
            this.detail=detail;
        return this;
    }

    public BaseDataResponse jumpUrl(String jumpUrl){
        this.jumpUrl=jumpUrl;
        return this;
    }

    public BaseDataResponse html(String html){
        this.html=html;
        return this;
    }

    public BaseDataResponse data(Object data) {
        this.data=data;
        return this;
    }

    public BaseDataResponse action(BaseAction action) {
        this.msg = action.getMsg();
        this.detail = action.getDetail();
        this.jumpUrl = action.getJumpUrl();
        return this;
    }

    public BaseDataResponse error(Exception e){
        this.msg="操作失败";
        this.detail=e.getMessage();
        return this;
    }

    /*=====================
    *  getter() and setter()
    * */

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
