package com.cennavi.cloudalibaba.commons;

/**
 * @author Rain
 * @Date 2020/8/29 14:06
 **/
public class RetObj<T> {
    private boolean isSuccess;
    private String errcode = "0";
    private String errmsg;
    private Exception exception;
    private T result;


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
