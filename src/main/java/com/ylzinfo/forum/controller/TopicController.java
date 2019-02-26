package com.ylzinfo.forum.controller;

import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @RequestMapping("get")
    @ResponseBody
    public List<Topic> list() {
        return topicService.list();
    }

    @PostMapping("add")
    @ResponseBody
    public int add(TopicDTO topicDTO) {
        return topicService.add(topicDTO);
    }
}
