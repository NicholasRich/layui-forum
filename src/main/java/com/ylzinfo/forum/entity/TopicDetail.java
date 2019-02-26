package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pm_topic_detail")
public class TopicDetail {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long topicId;
    private String content;
}
