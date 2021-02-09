package com.veryreader.d2p.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("cors")
@Data
@Configuration
public class CorsConfig {

    private String mapping;
    private String[] allowedOrigins;
    private String[] allowedMethods;
}
