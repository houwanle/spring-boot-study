package com.lele.springbootstudy.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lele
 * @date: 2022/6/7 7:54
 * @description: 自定义 actuator 端点----自定义全新的端点
 */

@Component
@Endpoint(id = "user")
public class UserEndpoint {

    @ReadOperation
    public List<Map<String, Object>> health() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1001);
        map.put("userName", "zhangsan");
        list.add(map);
        return list;
    }
}
