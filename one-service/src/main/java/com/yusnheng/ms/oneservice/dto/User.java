package com.yusnheng.ms.oneservice.dto;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    // 演示消费者端并不需要这个字段
    private String desc;
}
