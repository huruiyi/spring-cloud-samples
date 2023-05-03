## Getting Started

### 1:nacos中新建命名空间

```
命名空间名：dev
     描述：开发分支
拿到生成的命名空间ID：8ca94cfb-7737-43b9-8cbf-5185725e9443
```

### 2:bootstrap-dev.properties配置

```
spring.cloud.nacos.config.namespace:生成的命名空间ID
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
```

### 3:nacos中新建配置

```
在dev空间下新建配置
Data ID：config-example.properties ，即${spring.application.name}.properties
配置格式:YAML
配置内容:
    mysql.address=dev.mysql.address........
    mysql.port=dev.mysql.port...
```

