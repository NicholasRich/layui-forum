package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class TopicReply {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long topicId;
    private Long userId;
    private String replyContent;
    private Date createTime;
}
