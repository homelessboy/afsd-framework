package me.afsd.service.base;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
public class BaseException extends RuntimeException {
    private IErrorInfo iErrorInfo;

    public BaseException(Throwable cause){
        super(cause);
    }

    public BaseException(IErrorInfo iErrorInfo){
        super(iErrorInfo.toString());
        this.iErrorInfo=iErrorInfo;
    }
    public BaseException(String message){
        super(message);
    }

    public BaseException(String message, IErrorInfo iErrorInfo) {
        super(message);
        this.iErrorInfo=iErrorInfo;
    }

    public IErrorInfo getiErrorInfo() {
        return iErrorInfo;
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
