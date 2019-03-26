package com.ylzinfo.forum.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ylzinfo.forum.entity.Topic;

import java.util.List;
import java.util.Map;

public interface TopicService extends IService<Topic> {
    Long insert(Topic topic);

    Topic getDetail(Long id, String userId);

    boolean delete(Long topicId);

    IPage<Topic> getPublish(String userId, Long page);

    IPage<Topic> getCollection(String userId, Long page);

    List<Map<String, Object>> getTopicCount(String userId);
}
