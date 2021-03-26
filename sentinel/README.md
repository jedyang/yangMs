## 工程简介
### 演示sentinel服务限流  

1， 访问：https://github.com/alibaba/Sentinel/releases，下载最新版 Sentinel Dashboard。  
2，启动sentinel-dashboard  

```java -jar -Dserver.port=9100 sentinel-dashboard-1.8.1.jar```
然后登录http://localhost:9100/#/login  

用户名密码：sentinel/sentinel



3，application.yml

application.yml中注册sentinel

启动后，在setinel控制台可以看到sentinel-demo应用



4，测试

写一个controller，没有流控时，怎么刷新都是返回success

在控制台配置流控规则。

从簇点链路中找到我们的请求，对其添加流控规则。

之后快速刷新，触发qps阈值。会报`Blocked by Sentinel (flow limiting)`



### 原理

Sentinel Core 为服务限流、熔断提供了核心拦截器 SentinelWebInterceptor，这个拦截器默认对所有请求 /** 进行拦截，然后开始请求的链式处理流程。有任何一个处理链的节点验证未通过，请求处理都会中断，并返回Blocked by sentinel的异常信息。

Sentinel 内置提供了7个处理节点。

实现限流降级的核心是如何统计单位时间某个接口的访问量，常见的算法有计数器算法、令牌桶算法、漏桶算法、滑动窗口算法。Sentinel 采用滑动窗口算法来统计访问量。



熔断降级的配置差不多。

具体的高级选项配置，查阅官方文档。