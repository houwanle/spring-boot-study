package com.lele.springbootstudy.controller;

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

}
