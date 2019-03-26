package com.ylzinfo.forum.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.entity.Topic;

import java.util.List;
import java.util.Map;

public interface TopicService extends IService<Topic> {
    Long insert(TopicDTO topicDTO);

    TopicDTO getDetail(Long id);

    boolean delete(Long topicId);

    IPage<Topic> getPublish(String userId, Long page);

    IPage<Topic> getCollection(String userId, Long page);

    List<Map<String, Object>> getTopicCount(String userId);
}
