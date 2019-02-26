package com.ylzinfo.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("jie")
public class IndexController {
    @GetMapping("index")
    public String index() {
        return "jie/index";
    }

    @GetMapping("detail")
    public String detail() {
        return "jie/detail";
    }

    @GetMapping("add")
    public String add() {
        return "jie/add";
    }
}
