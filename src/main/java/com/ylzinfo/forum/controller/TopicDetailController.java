package com.ylzinfo.forum.controller;

import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.service.TopicDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("edit")
    public String edit(Long id, Model model) {
        model.addAttribute("id", id);
        return "/jie/add";
    }

    @GetMapping("getOne")
    @ResponseBody
    public ResultDTO<TopicDTO> getOne(Long id) {
        TopicDTO content = topicDetailService.getContent(id);
        return new ResultDTO<TopicDTO>().dataSuccess(content);
    }

    @PostMapping("update")
    @ResponseBody
    private ResultDTO update(TopicDTO topicDTO) {
        if (topicDetailService.updateTopic(topicDTO)) {
            return new ResultDTO().actionSuccess(request.getContextPath() + "/topic/detail?id=" + topicDTO.getId());
        } else {
            return new ResultDTO().fail("编辑失败");
        }
    }
}
