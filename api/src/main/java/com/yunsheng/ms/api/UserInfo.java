package com.yunsheng.ms.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 8409426884315590903L;
    private Long id;
    private String name;
    private Integer age;
    private String desc;
}
