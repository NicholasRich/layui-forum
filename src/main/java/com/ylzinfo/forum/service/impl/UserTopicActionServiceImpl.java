package com.ylzinfo.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ylzinfo.forum.entity.UserTopicAction;
import com.ylzinfo.forum.mapper.UserTopicActionMapper;
import com.ylzinfo.forum.service.UserTopicActionService;
import org.springframework.stereotype.Service;

@Service
public class UserTopicActionServiceImpl extends ServiceImpl<UserTopicActionMapper, UserTopicAction> implements UserTopicActionService {
}
