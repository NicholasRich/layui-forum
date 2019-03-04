package com.ylzinfo.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.entity.TopicTop;
import com.ylzinfo.forum.mapper.TopicTopMapper;
import com.ylzinfo.forum.service.TopicTopService;
import org.springframework.stereotype.Service;

@Service
public class TopicTopServiceImpl extends ServiceImpl<TopicTopMapper, TopicTop> implements TopicTopService {
}