package com.lele.springbootstudy.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @author: lele
 * @date: 2022/6/7 7:48
 * @description: 自定义 actuator 端点----框架自带的 health 端点中进行扩展
 */
@Component
public class UserHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("status", true);
    }
}
