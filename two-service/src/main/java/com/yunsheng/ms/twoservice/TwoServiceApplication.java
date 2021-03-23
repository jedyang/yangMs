package com.yunsheng.ms.twoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TwoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoServiceApplication.class, args);
    }

    /**
     * 演示使用ribbon+restTemplate做负载均衡
     * @return
     */
//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    /**
     * 演示使用ribbon+restTemplate做负载均衡
     * @LoadBalanced //使RestTemplate对象自动支持Ribbon负载均衡
     * 一般我们都用这个
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
