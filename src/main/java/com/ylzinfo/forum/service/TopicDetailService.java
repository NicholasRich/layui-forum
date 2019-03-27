package com.ylzinfo.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.entity.TopicDetail;

public interface TopicDetailService extends IService<TopicDetail> {
    Topic getContent(Long id);

    boolean updateTopic(Topic topicDTO);
}
