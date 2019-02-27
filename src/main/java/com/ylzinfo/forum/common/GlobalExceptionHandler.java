package com.ylzinfo.forum.common;

import com.ylzinfo.forum.dto.ResultDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.ylzinfo.forum.controller")
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO handler(Exception e) {
        return new ResultDTO().fail(e.getMessage() == null ? "接口异常" : e.getMessage());
    }
}
