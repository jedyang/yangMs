package com.yunsheng.ms.confignacos.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@RefreshScope
public class ConfigBean {

    @Value("${database.url}")
    private String url;
}
