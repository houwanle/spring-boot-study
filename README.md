## SpringBootStudy

### 环境

名称 | 版本
---|---
spring boot | 2.7.0
jdk | 1.8

### profiles多环境配置
在 Spring Boot 中可以通过 spring.profiles.active=dev 来激活不同环境下的配置。

可以定义多个配置文件，每个配置文件对应一个环境，格式为 application-环境.propertie。

文件 | 范围
---|---
application.properties | 通用配置，不区分环境
application-dev.properties | 开发环境
application-test.properties | 测试环境
application-prod.properties | 生产环境

> 在开发环境中，可以通过修改 application.properties 中的 spring.profiles.active 的值来激活对应环境的配置，在部署的时候可以通过 java–jar xxx.jar--spring.profiles.active=dev 来指定使用对应的配置。

### actuator监控
Spring Boot 提供了一个用于监控和管理自身应用信息的模块，它就是 spring-boot-starter-actuator。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Http方法 | 路径 | 描述 | Http默认暴露
---|---|---|---
GET | /actuator/conflgprops	| 查看配置属性，包含默认配置 | false
GET | /actuator/beans | 查看bean及其关系列表 | false
GET | /actuator/heapdump | 打印线程栈 | false
GET | /actuator/env | 查看所有环境变量 | false
GET | /actuator/env/ {name} | 查看具体变量值 | true
GET | /actuator/health | 查看应用健康指标 | true
GET | /actuator/info | 查看应用信息 | false
GET | /actuator/mappings | 查看所有 URL 映射 | false
GET | /actuator/metrics | 查看应用基本指标 | false
GET | /actuator/metrics/{name} | 查看具体指标 | false
POST | /actuator/shutdown | 关闭应用 | false
GET | /actuator/httptrace | 查看基本追踪信息 | false
GET | /actuator/loggers | 显示应用程序中 loggers 配置 | false
GET | /actuator/scheduledtasks | 显示定时任务 | false

UP 表示当前应用处于健康状态，如果是 DOWN 就表示当前应用不健康。增加下面的配置可以让一些健康信息的详情也显示出来：

```
management.endpoint.health.show-details=ALWAYS
```

访问 /actuator/health，就可以得到健康状态的详细信息：

```json
{
    "status": "UP",
    "diskSpace": {
        "status": "UP",
        "total": 491270434816,
        "free": 383870214144,
        "threshold": 10485760
    }
}
```

大部分端点默认都不暴露出来，我们可以手动配置需要暴露的端点。如果需要暴露多个端点，可以用逗号分隔:

```
management.endpoints.web.exposure.include=configprops,beans
```

如果想全部端点都暴露的话直接配置成下面的方式：

```
management.endpoints.web.exposure.include=*
```
