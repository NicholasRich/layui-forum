package com.ylzinfo.forum.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ylzinfo.forum.entity.Topic;

public interface TopicService extends IService<Topic> {
    Long insert(Topic topic);

    Topic getDetail(Long id, Long userId);

    boolean delete(Long topicId);

    IPage<Topic> getPublish(Long userId, Long page);

    IPage<Topic> getCollection(Long userId, Long page);

}
