package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
//id                bigint auto_increment
//        primary key,
//        create_time       timestamp default CURRENT_TIMESTAMP null comment '创建时间',
//        give_like_confirm varchar(255)                        null comment '确认点赞(CONFIRM：点赞；NOCONFIRM：取消点赞)',
//        topic_id          int                                 null,
//        update_time       timestamp                           null comment '更新时间',
//        user_id           varchar(255)                        null

@Data
@TableName("pm_topic_give_like")
public class TopicGiveLike implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;
    private String giveLikeConfirm;
    private Long topicId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;
    private String userId;
}
