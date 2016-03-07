package me.afsd.service.base;

/**
 * User: afsd
 * Date: 2016/3/4
 * Time: 15:52
 */
public interface IErrorInfo {
    String getNameSpace();
    String getErrorCode();
    String ErrorMessage();
    String toString();
}
