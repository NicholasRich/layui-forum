package com.ylzinfo.forum.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.entity.TopicReply;
import com.ylzinfo.forum.service.TopicReplyService;
import org.springframework.stereotype.Service;

@Service
public class TopicReplyServiceImpl extends ServiceImpl<BaseMapper<TopicReply>, TopicReply> implements TopicReplyService {
}
