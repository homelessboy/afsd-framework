package me.afsd.site.base;

/**
 * User: afsd
 * Date: 2016/3/7
 * Time: 17:33
 */
public class CrudAction implements BaseAction {
    private String msg;
    private String detail;
    private String jumpUrl;

    public CrudAction(String msg, String detail, String jumpUrl) {
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
