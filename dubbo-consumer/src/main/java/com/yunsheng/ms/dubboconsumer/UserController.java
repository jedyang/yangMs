package com.yunsheng.ms.dubboconsumer;

import com.yunsheng.ms.api.UserInfo;
import com.yunsheng.ms.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @DubboReference(version = "1.0.0")
    private UserService userService;

    @GetMapping("/user/info")
    public String getUserInfo(@RequestParam(value = "userId") Long userId) {
        log.info(userId+"");
        UserInfo userInfo = userService.getUserInfo(userId);
        return userInfo.toString();
    }
}
