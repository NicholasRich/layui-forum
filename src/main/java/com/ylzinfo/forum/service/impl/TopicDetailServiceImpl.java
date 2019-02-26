package com.ylzinfo.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.entity.TopicDetail;
import com.ylzinfo.forum.mapper.TopicDetailMapper;
import com.ylzinfo.forum.service.TopicDetailService;
import org.springframework.stereotype.Service;

@Service
public class TopicDetailServiceImpl extends ServiceImpl<TopicDetailMapper, TopicDetail> implements TopicDetailService {
}
