package com.yunsheng.ms.dubboprovider.service;

import com.yunsheng.ms.api.UserInfo;
import com.yunsheng.ms.api.UserService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public UserInfo getUserInfo(Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setName("dubbo");
        userInfo.setDesc("provider");
        return userInfo;
    }
}
