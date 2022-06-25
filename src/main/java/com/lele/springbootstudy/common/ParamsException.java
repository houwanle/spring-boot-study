package com.lele.springbootstudy.common;

/**
 * @author: lele
 * @date: 2022/6/25 17:16
 * @description: 参数异常
 */
public class ParamsException extends RuntimeException {

    private static final long serialVersionUID = 8966565582668811760L;
    private int errorCode = -1;
    private String message;
    private String exceptionMessage;
    private Throwable throwable;

    public static void throwException(ParamsException exception) {
        throw exception;
    }

    public ParamsException() {
        super("ParamsException");
    }

    public ParamsException(Throwable cause) {
        super(cause);
    }

    public ParamsException(String message) {
        this.message = message;
    }

    public ParamsException(ParamsException e) {
        this.errorCode = e.getErrorCode();
        this.message = e.getMessage();
        this.exceptionMessage = e.getExceptionMessage();
    }

    public ParamsException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public ParamsException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ParamsException(int errorCode, String message, String exceptionMessage) {
        this.errorCode = errorCode;
        this.message = message;
        this.exceptionMessage = exceptionMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
