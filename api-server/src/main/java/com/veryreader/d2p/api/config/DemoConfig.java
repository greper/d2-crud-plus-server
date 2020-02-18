package com.veryreader.d2p.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/10/18$
 */
@ConfigurationProperties("demo")
@Data
@Configuration
public class DemoConfig {
    private Boolean editDisabled = false;
}
