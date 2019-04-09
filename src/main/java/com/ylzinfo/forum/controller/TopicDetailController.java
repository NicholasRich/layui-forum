package com.ylzinfo.forum.controller;

import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.service.TopicDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("topicDetail")
public class TopicDetailController {
    @Autowired
    private TopicDetailService topicDetailService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "/jie/add";
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResultDTO<Topic> getOne(@PathVariable Long id) {
        Topic content = topicDetailService.getContent(id);
        return new ResultDTO<Topic>().dataSuccess(content);
    }

    @PostMapping("update")
    @ResponseBody
    private ResultDTO update(Topic topic) {
        if (topicDetailService.updateTopic(topic)) {
            return new ResultDTO().actionSuccess(request.getContextPath() + "/topic/detail/" + topic.getId());
        } else {
            return new ResultDTO().fail("编辑失败");
        }
    }
}
