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

