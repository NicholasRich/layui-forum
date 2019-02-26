package com.ylzinfo.forum.entity;

import lombok.Data;

import java.util.Date;

//id         int auto_increment comment '自增主键'
//        primary key,
//        user_id    int            null comment '发帖人id',
//        user_name  varchar(20)    null comment '发帖人姓名',
//        user_dept  varchar(20)    null comment '发帖人部门',
//        theme      int            null comment '帖子主题',
//        type       int            null comment '帖子类别:1.提问 2.公告 3.讨论 4.建议 5.反馈',
//        title      varchar(50)    null comment '标题',
//        post_text  varchar(4000)  null comment '帖子内容',
//        content    varchar(12000) null comment '帖子内容(含样式)',
//        begin_time datetime       null comment '发帖时间',
//        end_time   datetime       null comment '结帖时间',
//        edit_time  datetime       null comment '最后编辑时间',
//        read_count  int            null comment '浏览量',
//        like_count int            null comment '点赞量'
@Data
public class Post {
    private Integer id;
    private Integer userId;
    private String userName;
    private String userDept;
    private Integer theme;
    private Integer type;
    private String title;
    private String postText;
    private String content;
    private Date beginTime;
    private Date endTime;
    private Date editTime;
    private Integer readCount;
    private Integer likeCount;
}
