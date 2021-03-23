package com.yunsheng.ms.openfeign;

import okhttp3.ConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

// 启用OpenFeign
@EnableFeignClients
@SpringBootApplication
public class OpenfeignApplication {

    //Spring IOC容器初始化时构建okHttpClient对象
    @Bean
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
                //读取超时时间
                .readTimeout(10, TimeUnit.SECONDS)
                //连接超时时间
                .connectTimeout(10, TimeUnit.SECONDS)
                //写超时时间
                .writeTimeout(10, TimeUnit.SECONDS)
                //设置连接池
                .connectionPool(new ConnectionPool())
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignApplication.class, args);
    }

}
