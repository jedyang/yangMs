package com.yusnheng.ms.oneservice.controller;

import com.yusnheng.ms.oneservice.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user/info")
    public User getUserInfo(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setName("nacos");
        user.setAge(20);
        user.setDesc("desc");
        return user;
    }
}
