package com.lele.springbootstudy.controller;

import com.lele.springbootstudy.common.BaseErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lele
 * @date: 2022/6/6 22:25
 * @description:
 */

@RestController
public class HelloController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/queryServerPort")
    public String queryServerPort() {
        return serverPort;
    }

    /**
     * 国际化测试接口
     * @return
     */
    @GetMapping("/message")
    public String message() {
        return BaseErrorCode.getMessage("welcome");
    }

}
