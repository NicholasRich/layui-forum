package com.ylzinfo.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.entity.Topic;

public interface TopicService extends IService<Topic> {
    Long insert(TopicDTO topicDTO);

    TopicDTO getDetail(Long id);

    boolean delete(Long topicId);

}
