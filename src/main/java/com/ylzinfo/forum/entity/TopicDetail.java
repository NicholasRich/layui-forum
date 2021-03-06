package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@TableName("pm_topic_detail")
@RequiredArgsConstructor(staticName = "getInstance")
public class TopicDetail implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NonNull
    private Long topicId;
    @NonNull
    private String content;
}
