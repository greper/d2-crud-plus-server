package com.veryreader.d2p.api.exceptions;

/**
 * @author Administrator
 */
public class ClientException extends BaseException {
    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }


    public ClientException(int code) {
        super("");
        this.code = code;
        this.init();
    }

    public ClientException(int code, Throwable cause) {
        super(code, cause);
    }

    public ClientException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ClientException(int code, String message) {
        super(code, message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    @Override
    public ClientException withData(Object data) {
        return (ClientException)super.withData(data);
    }
}
