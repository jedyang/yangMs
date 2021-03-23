package com.yunsheng.ms.twoservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class RibbonController {
    /**
     * 注入ribbon负载均衡器
     * 在引入 starter-netflix-ribbon后在 SpringBoot 启动时会自动实例化 LoadBalancerClient 对象。
     */
    @Resource
    private LoadBalancerClient loadBalancerClient;
    /**
     * 注入启动时创建的RestTemplate
     */
    @Resource
    private RestTemplate restTemplate;


    /**
     * 代码方式
     * @return
     */
    @GetMapping("/consumer/msg")
    public String getProviderMessage() {
        //loadBalancerClient.choose()方法会从 Nacos 获取 one-service 所有可用实例，
        //并按负载均衡策略从中选择一个可用实例，封装为 ServiceInstance（服务实例）对象
        ServiceInstance serviceInstance = loadBalancerClient.choose("one-service");
        //获取服务实例的 IP 地址
        String host = serviceInstance.getHost();
        //获取服务实例的端口
        int port = serviceInstance.getPort();
        //在日志中打印服务实例信息
        log.info("本次调用由provider-service的" + host + ":" + port + " 实例节点负责处理");
        //通过 RestTemplate 对象的 getForObject() 方法向指定 URL 发送请求，并接收响应。
        //getForObject()方法有两个参数：
        //2. String.class说明 URL 返回的是纯字符串，如果第二参数是实体类， RestTemplate 会自动进行反序列化，为实体属性赋值
        String result = restTemplate.getForObject("http://" + host + ":" + port + "/one/msg", String.class);
        //输出响应内容
        log.info("one-service 响应数据:" + result);
        //向浏览器返回响应
        return "one-service 响应数据:" + result;
    }

    /**
     * @LoadBalanced注解方式
     * @return
     */
    @GetMapping("/consumer/msg2")
    public String getProviderMessage2() {
        //关键点：将原有IP:端口替换为服务名，RestTemplate便会在通信前自动利用Ribbon查询可用provider-service实例列表
        //再根据负载均衡策略选择节点实例
        String result = restTemplate.getForObject("http://one-service/one/msg", String.class);
        //输出响应内容
        log.info("one-service 响应数据:" + result);
        //向浏览器返回响应
        return "one-service 响应数据:" + result;
    }
}
