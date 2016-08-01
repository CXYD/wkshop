package net.jeeshop.web.bean;

import java.io.Serializable;

/**
 * Created by dylan on 15-9-5.
 */
public class ResultBean<T> implements Serializable {
    public static final String CODE_SUCCESS = "00";
    private String code = CODE_SUCCESS;
    private String msg;
    private T data;

    public ResultBean() {

    }

    public ResultBean(String msg) {
        this.msg = msg;
    }

    public ResultBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return CODE_SUCCESS.equalsIgnoreCase(code);
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
