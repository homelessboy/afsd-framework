package me.afsd.site.base;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public class CrudAction extends BaseActionImp implements BaseAction {

    private CrudAction(String msg, String detail, String jumpUrl) {
        super(msg, detail, jumpUrl);
    }

    private CrudAction(String op,Boolean isSuccess,String domainType,String domainName,String listUrl){
        super();
        String result=isSuccess?"成功":"失败";
        String msg=domainType+op+result;
        String detail=op+result;
        if (domainName != null && !domainName.isEmpty()) {
            detail=op+"'"+domainName+"'"+result;
        }
        this.msg=msg;
        this.detail=detail;
        this.jumpUrl=listUrl;
    }

    public static CrudAction ADD_SUCCESS(String domainType,String domainName,String listUrl){
        return new CrudAction("添加",Boolean.TRUE,domainType,domainName,listUrl);
    }

    public static CrudAction EDIT_SUCCESS(String domainType, String domainName, String listUrl) {
        return new CrudAction("编辑", Boolean.TRUE, domainType, domainName, listUrl);
    }
    public static CrudAction DELETE_SUCCESS(String domainType,String domainName,String listUrl) {
        return new CrudAction("删除", Boolean.FALSE, domainType, domainName, listUrl);
    }

    public static CrudAction NOT_EXIST(String domainType){
        return new CrudAction("所选"+domainType+"不存在","请刷新页面",null);
    }

}
