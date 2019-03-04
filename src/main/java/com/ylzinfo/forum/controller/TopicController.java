package com.ylzinfo.forum.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.dto.TopicDTO;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.service.TopicService;
import com.ylzinfo.forum.util.UploadUtil;
import com.ylzinfo.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("get")
    @ResponseBody
    public List<Topic> list() {
        return topicService.list();
    }

    @PostMapping("insert")
    @ResponseBody
    public ResultDTO insert(TopicDTO topicDTO) {
        topicDTO.setUserId(UserUtil.getUserId());
        Long id = topicService.insert(topicDTO);
        if (id != null) {
            return new ResultDTO().actionSuccess(request.getContextPath() + "/topic/detail?page=0&id=" + id);
        }
        return new ResultDTO().fail("发帖失败");
    }

    @GetMapping("add")
    public String add() {
        return "jie/add";
    }

    @GetMapping("detail")
    public String detail(Model model, Long id, Long page) {
        model.addAttribute("id", id);
        model.addAttribute("page", page);
        return "jie/detail";
    }

    @GetMapping("content")
    @ResponseBody
    public ResultDTO<TopicDTO> content(Long id) {
        return new ResultDTO<TopicDTO>().dataSuccess(topicService.getDetail(id));
    }

    @PostMapping("image/upload")
    @ResponseBody
    public ResultDTO upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String preUrl = request.getContextPath();
        return new ResultDTO().imageSuccess(UploadUtil.imageUpload(multipartFile, preUrl));
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultDTO delete(Long topicId) {
        if (topicService.delete(topicId)) {
            return new ResultDTO().success("删除成功");
        }
        return new ResultDTO().fail("删除失败");
    }

    @PostMapping("marrow")
    @ResponseBody
    public ResultDTO marrow(Long topicId) {
        if (topicService.update(Wrappers.<Topic>lambdaUpdate()
                .eq(Topic::getId, topicId)
                .set(Topic::getMarrow, "MARROW"))) {
            return new ResultDTO().success("加精成功");
        } else {
            return new ResultDTO().fail("加精失败");
        }
    }

    @PostMapping("cancelMarrow")
    @ResponseBody
    public ResultDTO cancelMarrow(Long topicId) {
        if (topicService.update(Wrappers.<Topic>lambdaUpdate()
                .eq(Topic::getId, topicId)
                .set(Topic::getMarrow, "NOMARROW"))) {
            return new ResultDTO().success("取消成功");
        } else {
            return new ResultDTO().fail("取消失败");
        }
    }
}
