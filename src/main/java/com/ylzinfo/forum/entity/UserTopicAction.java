package com.ylzinfo.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor(staticName = "getInstance")
@TableName("rl_user_topic_action")
public class UserTopicAction implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NonNull
    private Long topicId;
    @NonNull
    private String userId;
    @NonNull
    private String userTopicType;
}
