package com.tabeeb.exception;

import java.text.MessageFormat;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public ApplicationException(){
        super();
    }

    public ApplicationException(String code, String message) {
        super(message);
        setCode(code);
    }

    public ApplicationException(String message) {
        super(message);
    }

     public ApplicationException(String message, Object[] params) {
        super(params!=null?new MessageFormat(message).format(params):message);
     }

    public ApplicationException(String message, Object[] params, String code) {
        super(params!=null?new MessageFormat(message).format(params):message);
        setCode(code);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
