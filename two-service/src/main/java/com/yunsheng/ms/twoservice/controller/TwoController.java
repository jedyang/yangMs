package com.yunsheng.ms.twoservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwoController {

    @GetMapping("/two/msg")
    public String sendMsg() {
        return "two service";
    }
}
