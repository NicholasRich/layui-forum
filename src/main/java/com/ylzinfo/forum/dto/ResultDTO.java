package com.ylzinfo.forum.dto;

import lombok.Data;

@Data
public class ResultDTO<T> {
    private Integer status;
    //    表单提交跳转链接
    private String action;
    //    上传图片路径
    private String url;
    //    记录总数
    private Long count;
    private String msg;
    private T data;

    public ResultDTO actionSuccess(String action) {
        this.status = 0;
        this.action = action;
        return this;
    }

    public ResultDTO imageSuccess(String url) {
        this.status = 0;
        this.url = url;
        return this;
    }

    public ResultDTO fail(String msg) {
        this.status = 500;
        this.msg = msg;
        return this;
    }

    public ResultDTO success(String msg) {
        this.status = 0;
        this.msg = msg;
        return this;
    }

    public ResultDTO<T> dataSuccess(T t) {
        this.status = 0;
        this.data = t;
        return this;
    }

    public ResultDTO<T> dataSuccess(T t, String msg) {
        this.status = 0;
        this.msg = msg;
        this.data = t;
        return this;
    }

    public ResultDTO<T> pageSuccess(T t, Long count) {
        dataSuccess(t);
        this.count = count;
        return this;
    }
}
