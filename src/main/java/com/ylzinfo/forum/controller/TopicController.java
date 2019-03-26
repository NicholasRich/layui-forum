package com.ylzinfo.forum.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ylzinfo.forum.dto.ResultDTO;
import com.ylzinfo.forum.entity.Topic;
import com.ylzinfo.forum.service.TopicService;
import com.ylzinfo.forum.util.UploadUtil;
import com.ylzinfo.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public ResultDTO insert(Topic topic) {
        topic.setUserId(UserUtil.getUserId());
        Long id = topicService.insert(topic);
        if (id != null) {
            return new ResultDTO().actionSuccess(request.getContextPath() + "/topic/detail/" + id + "/1");
        }
        return new ResultDTO().fail("发帖失败");
    }

    @GetMapping("add")
    public String add() {
        return "jie/add";
    }

    @GetMapping("detail/{id}/{page}")
    public String detail(Model model, @PathVariable Long id, @PathVariable Long page) {
        model.addAttribute("id", id);
        model.addAttribute("page", page);
        return "jie/detail";
    }

    @GetMapping("content")
    @ResponseBody
    public ResultDTO<Topic> content(Long id, String userId) {
        return new ResultDTO<Topic>().dataSuccess(topicService.getDetail(id, userId));
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

    @GetMapping("getPublish")
    @ResponseBody
    public ResultDTO<IPage<Topic>> getPublish(String userId, Long page) {
        return new ResultDTO<IPage<Topic>>().dataSuccess(topicService.getPublish(userId, page));
    }

    @GetMapping("getTopicCount")
    @ResponseBody
    public ResultDTO<List<Map<String, Object>>> getTopicCount(String userId) {
        return new ResultDTO<List<Map<String, Object>>>().dataSuccess(topicService.getTopicCount(userId));
    }

    @GetMapping("getCollection")
    @ResponseBody
    public ResultDTO<IPage<Topic>> getCollection(String userId, Long page) {
        return new ResultDTO<IPage<Topic>>().dataSuccess(topicService.getCollection(userId, page));
    }
}
