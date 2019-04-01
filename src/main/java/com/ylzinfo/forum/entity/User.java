package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
//id               bigint auto_increment
//        primary key,
//        city             varchar(255) null comment '地区',
//        display_name     varchar(255) null comment '名称',
//        email            varchar(255) null comment '邮箱',
//        image_url        varchar(255) null comment '头像',
//        integrate        int          null comment '积分',
//        mobile           varchar(255) null comment '手机',
//        password         varchar(255) null comment '密码',
//        person_signature varchar(255) null comment '个人签名',
//        sex              varchar(255) null comment '（MAN：男；WOMAN：女）',
//        user_id          varchar(255) null comment '账号'

@TableName("cm_user")
@Data
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String city;
    private String displayName;
    private String email;
    private String imageUrl;
    private Integer integrate;
    private String mobile;
    private String password;
    private String personSignature;
    private String sex;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date createTime;
}
