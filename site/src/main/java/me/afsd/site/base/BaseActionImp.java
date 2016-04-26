package me.afsd.site.base;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public class BaseActionImp implements BaseAction{
    protected String msg;
    protected String detail;
    protected String jumpUrl;

    public BaseActionImp(){}

    public BaseActionImp(String msg,String detail){
        this(msg,detail,null);
    }

    public BaseActionImp(String msg, String detail, String jumpUrl) {
        this.msg=msg;
        this.detail=detail;
        this.jumpUrl=jumpUrl;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getDetail() {
        return detail;
    }

    @Override
    public String getJumpUrl() {
        return jumpUrl;
    }
}
