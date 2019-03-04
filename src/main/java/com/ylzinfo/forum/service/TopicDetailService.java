package com.ylzinfo.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.entity.TopicDetail;

public interface TopicDetailService extends IService<TopicDetail> {
    TopicDTO getContent(Long id);

    boolean updateTopic(TopicDTO topicDTO);
}
