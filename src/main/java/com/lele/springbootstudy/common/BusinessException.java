package com.lele.springbootstudy.common;

/**
 * @author: lele
 * @date: 2022/6/25 17:07
 * @description: 业务异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int errorCode = -1;
    private String message;
    private String exceptionMessage;

    public BusinessException() {
    }

    public BusinessException(Throwable t) {
        super(t);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable t) {
        super(message, t);
    }

    public BusinessException(int errorCode, String message, String exceptionMessage) {
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

    @Override
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
}
