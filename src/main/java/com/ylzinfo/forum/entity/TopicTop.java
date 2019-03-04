package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("rl_topic_top")
public class TopicTop implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long topicId;
}
