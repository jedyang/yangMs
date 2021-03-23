package com.yunsheng.ms.openfeign.controller;

import com.yunsheng.ms.openfeign.feignClient.UserInfo;
import com.yunsheng.ms.openfeign.feignClient.UserServiceFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserServiceFeignClient userServiceFeignClient;

    @GetMapping("/user/info")
    public String getUserInfo(@RequestParam Long userId) {
        UserInfo userInfo = userServiceFeignClient.getUserInfo(userId);
        return userInfo.toString();
    }
}
