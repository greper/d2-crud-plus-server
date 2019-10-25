package com.veryreader.d2p.api.config.upload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/10/18$
 */
@ConfigurationProperties("uploader.qiniu")
@Data
@Configuration
public class QiniuUploaderConfig {
    String accessKey = "access key";
    String secretKey = "secret key";
    String bucket = "bucket name";
}
