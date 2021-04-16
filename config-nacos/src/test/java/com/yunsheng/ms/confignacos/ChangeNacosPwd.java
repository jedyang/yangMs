package com.yunsheng.ms.confignacos;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ChangeNacosPwd {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("Haier,123"));
    }
}
