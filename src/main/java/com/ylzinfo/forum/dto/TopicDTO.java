package com.ylzinfo.forum.dto;

import com.ylzinfo.forum.entity.Topic;
import lombok.Data;

@Data
public class TopicDTO extends Topic {
    private Long detailId;
    private String content;
}
