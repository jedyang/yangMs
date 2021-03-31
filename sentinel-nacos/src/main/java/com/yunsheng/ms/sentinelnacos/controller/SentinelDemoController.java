package com.yunsheng.ms.sentinelnacos.controller;

import com.yunsheng.ms.sentinelnacos.config.ResponseObject;
import com.yunsheng.ms.sentinelnacos.service.SentinelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelDemoController {

    @Autowired
    private SentinelServiceImpl sentinelService;

    @GetMapping("/flowControl")
    public ResponseObject testFlowControl() {
        return new ResponseObject("200", "flowControl test");
    }


    @GetMapping("/degrade")
    public ResponseObject testDegrade() {
        try {
            sentinelService.testServiceMethodSentinel();
        } catch (IllegalStateException e) {
            return new ResponseObject("500", e.getMessage());
        }
        return new ResponseObject("200", "degrade test");
    }
}
