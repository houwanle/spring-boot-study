package com.lele.springbootstudy.controller;

import com.lele.springbootstudy.common.BaseErrorCode;
import com.lele.springbootstudy.method.BaseLogMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lele
 * @date: 2022/6/6 22:25
 * @description:
 */

@RestController
public class HelloController extends BaseController {

    private static final String TAG = "HelloController";

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = {"/hello"}, produces = CHAR_SET, method = RequestMethod.GET)
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
    @RequestMapping(value= {"/message"}, produces = CHAR_SET, method = RequestMethod.GET)
    public String message() {
        BaseLogMethod.logInfo(TAG, "12345");
        return BaseErrorCode.getMessage("welcome");
    }

}
