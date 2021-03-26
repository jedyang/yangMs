package com.yunsheng.ms.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelDemoController {

    @GetMapping("/flowControl")
    public String testFlowControl() {
        return "success";
    }
}
