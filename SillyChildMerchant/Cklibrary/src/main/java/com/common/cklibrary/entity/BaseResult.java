package com.common.cklibrary.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ruitu on 2016/11/17.
 */

public  class BaseResult<T> implements Serializable{

    /**
     * status : 1
     * msg : 成功
     * result : {}
     */
    private T result;
    private int status;
    private String msg;


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
