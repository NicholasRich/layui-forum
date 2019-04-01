package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("pm_topic_reply")
public class TopicReply implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long topicId;
    private String userId;
    private String replyContent;
    private String adoption;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    @TableField(exist = false)
    private String topicTitle;
}
