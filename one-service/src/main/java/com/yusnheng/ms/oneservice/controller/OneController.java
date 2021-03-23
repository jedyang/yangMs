package com.yusnheng.ms.oneservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneController {

    @GetMapping("/one/msg")
    public String sendMsg() {
        return "one service";
    }
}
