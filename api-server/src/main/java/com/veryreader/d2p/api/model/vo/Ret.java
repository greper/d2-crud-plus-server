package com.veryreader.d2p.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/10/22$
 */
@Data
@AllArgsConstructor
public class Ret<T> {
    /**
     * 0=success
     */
    private int code = 0;
    private String msg;
    private T data;

    public Ret() {
    }

    public Ret(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Ret(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public static Ret error(int code, String msg, Object data) {
        return new Ret(code,msg,data);
    }
    public static Ret error(String msg, Object data) {
        return new Ret(1,msg,data);
    }

    public static Ret success(String msg, Object data) {
        return new  Ret(0,msg,data);
    }
    public static Ret success() {
        return new  Ret(0,"success",null);
    }
}
