## sentinel同nacos的整合

1,依赖
```
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>
```
Sentinel 为 Nacos 扩展的数据源模块，允许将规则数据存储在 Nacos 配置中心，
在微服务启动时Sentinel利用该模块自动从 Nacos下载对应的规则数据。

2，配置下载规则
在application.yml 文件中增加 Nacos下载规则
```
      datasource:
        flow: # 数据源名称，自定义即可
          nacos: # 指明使用nacos作为数据源
            server-addr: ${spring.cloud.nacos.server-addr}
            dataId: ${spring.application.name}-flow-rules #定义流控规则data-id，完整名称就是sentinel-nacos-flow-rules，在nacos配置中心里设置时data-id必须对应。
            groupId: SENTINEL_GROUP #gourpId对应配置文件分组，对应配置中心groups项
            rule-type: flow #flow固定写死，说明这个配置是流控规则
            username: nacos 
            password: nacos
```

3,去nacos中配置规则
data-id和group根据配置对应配好
配置格式选择json
内容为：
```
[

    {

        "resource":"/flowControl", #资源名，说明对那个URI进行流控

        "limitApp":"default",  #命名空间，默认default

        "grade":1, #类型 0-线程 1-QPS

        "count":2, #超过2个QPS限流将被限流

        "strategy":0, #限流策略: 0-直接 1-关联 2-链路

        "controlBehavior":0, #控制行为: 0-快速失败 1-WarmUp 2-排队等待

        "clusterMode":false #是否集群模式

    }

]
```
这些配置和我们前面使用sentinel-dashboard进行配置是一一对应的。
至于其他的规则怎么写
https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6?fileGuid=xxQTRXtVcqtHK6j8
可以看官方文档。
4,测试
测试接口，发现可以限流。
另外，可以从暴漏的服务接口http://localhost:9191/actuator/sentinel
看到现在的规则

5，流控实时生效
在nacos中修改配置后，可以发现规则实时推送应用生效

## 针对service的流控
前面的配置都是对restful服务接口的。sentinel提供了@SentinelResource 注解。
用来自定义Sentinel 资源点来实现对特定方法的保护。
#### 1，声明切面类
#### 2，声明资源点
在具体的方法上加上注解即可
#### 3,配置一个熔断规则
#### 4，在nacos配置一个具体规则内容
```
[{

    "resource": "createOrder", #自定义资源名

    "limitApp": "default", #命名空间

    "grade": 0, #0-慢调用比例 1-异常比例 2-异常数

    "count": 50, #最大RT 50毫秒执行时间

    "timeWindow": 5, #时间窗口5秒

    "minRequestAmount": 1, #最小请求数

    "slowRatioThreshold": 0.1 #比例阈值

}]

```
上面这段 JSON 完整的含义是：在过去 1 秒内，如果 createOrder资源被访问 1 次后便开启熔断检查，如果其中有 10% 的访问处理时间超过 100 毫秒，则触发熔断 5 秒钟，这期间访问该方法所有请求都将直接抛出 DegradeException，5 秒后该资源点回到“半开”状态等待新的访问，如果下一次访问处理成功，资源点恢复正常状态，如果下一次处理失败，则继续熔断 5 秒钟。
ps:注意json的格式，冒号后面只能且必须是一个空格
#### 5，测试
多点几次，会触发熔断，直接显示一个错误页面。
看log显示这个异常，com.alibaba.csp.sentinel.slots.block.degrade.DegradeException:

这很不友好，可以对异常进行下处理

进行统一的响应拦截处理
针对RESTful接口的统一异常处理需要实现 BlockExceptionHandler
自定义资源点的异常处理与 RESTful 接口处理略有不同，我们需要在 @SentinelResource 注解上额外附加 blockHandler属性进行异常处理