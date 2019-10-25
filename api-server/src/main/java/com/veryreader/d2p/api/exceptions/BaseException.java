package com.veryreader.d2p.api.exceptions;

public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected int code = 1;
    protected Object[] args;
    protected Object data;

    public BaseException() {
        this.init();
    }

    public BaseException(String message) {
        super(message);
        this.code = 1;
        this.init();
    }

    public BaseException(int code, Throwable cause) {
        super(cause);
        this.code = code;
        this.init();
    }

    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.init();
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
        this.init();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.init();
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.init();
    }

    public void init() {

    }

    public int getCode() {
        return this.code;
    }

    public Object[] getArgs() {
        return this.args;
    }


    public BaseException withArgs(Object... args) {
        this.args = args;
        return this;
    }

    public BaseException withData(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
