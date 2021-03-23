package com.yunsheng.ms.openfeign.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OpenFeign 通过接口+注解形式描述数据传输逻辑，
 * 并不需要程序员编写具体实现代码便能实现服务间高可用通信
 *
 * one-service必须是服务提供者的注册id
 */
@FeignClient("one-service")
public interface UserServiceFeignClient {

    /**
     * 用于接收数据的 User 对象并不强制要求与提供者端 User 对象完全相同，
     * 消费者端的 User 类可以根据业务需要删减属性，但属性必须要与提供者响应的 JSON 属性保持一致。
     */
    @GetMapping("/user/info")
    public UserInfo getUserInfo(@RequestParam("userId") Long id);
}
