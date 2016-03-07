package me.afsd.service.base;

/**
 * User: afsd
 * Date: 2016/3/4
 * Time: 15:47
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
        return getMessage();
    }
}
