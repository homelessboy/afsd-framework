package me.afsd.service.base;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public enum DefaultError implements IErrorInfo {
    NULL_OBJECT("0000","对象为空");

    private String errorCode;
    private String errorMessage;

    public static final String NAME_SPACE = "sys_default";

    DefaultError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getNameSpace() {
        return this.NAME_SPACE;
    }

    @Override
    public String getErrorCode() {
        return NAME_SPACE+":"+errorCode;
    }

    @Override
    public String ErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String toString(){
        return NAME_SPACE+":"+errorCode+" "+errorMessage;
    }
}
