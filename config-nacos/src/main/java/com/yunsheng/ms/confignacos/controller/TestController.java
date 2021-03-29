package com.yunsheng.ms.confignacos.controller;

import com.yunsheng.ms.confignacos.config.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    @Value("${database.url}")
//    private String url;
//
//    @GetMapping("/url")
//    public String showUrl() {
//        return url;
//    }


    @Autowired
    private ConfigBean configBean;

    @GetMapping("/url")
    public String showUrl() {
        return configBean.getUrl();
    }
}
