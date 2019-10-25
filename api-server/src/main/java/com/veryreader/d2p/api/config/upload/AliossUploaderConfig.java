package com.veryreader.d2p.api.config.upload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/10/18$
 */
@ConfigurationProperties("uploader.alioss")
@Data
@Configuration
public class AliossUploaderConfig {
    String accessKey = "";
    String accessKeySecret = "";
    String bucket = "d2p-demo";
    String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    String roleArn ="";
}
