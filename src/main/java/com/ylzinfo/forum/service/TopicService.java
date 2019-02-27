package com.ylzinfo.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.entity.Topic;

public interface TopicService extends IService<Topic> {
    int add(TopicDTO topicDTO);

    TopicDTO getDetail(Long id);
}
