package com.yunsheng.ms.sentinelnacos;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SentinelNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelNacosApplication.class, args);
    }

    /**
     * sentinel的切面类
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

}
