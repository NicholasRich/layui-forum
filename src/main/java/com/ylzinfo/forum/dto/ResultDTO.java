package com.ylzinfo.forum.dto;

import lombok.Data;

@Data
public class ResultDTO<T> {
    private Integer status;
    //    表单提交跳转链接
    private String action;
    //    上传图片路径
    private String url;
    private String msg;
    private T data;

    public ResultDTO actionSuccess(String action) {
        this.setStatus(0);
        this.setAction(action);
        return this;
    }

    public ResultDTO imageSuccess(String url) {
        this.status = 0;
        this.url = url;
        return this;
    }

    public ResultDTO fail(String msg) {
        this.setStatus(500);
        this.setMsg(msg);
        return this;
    }

    public ResultDTO<T> dataSuccess(T t) {
        this.status = 0;
        this.data = t;
        return this;
    }
}