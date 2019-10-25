package com.veryreader.d2p.api.config.upload;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/10/18$
 */
@ConfigurationProperties("uploader.cos")
@Data
@Configuration
public class CosUploaderConfig {
    private String secretId= "AKIDbz3tUqJn6D8tPeNJm22lJb9xWq0uqz8x";
    private String secretKey="kFVWFhwve7KGwMUQ6XrypRmphKgnkQIx";
    private String bucket = "d2p-demo-1251260344";
    private String region = "ap-guangzhou";


    @Bean
    public COSCredentials createCOSCredentials(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        return cred;
    }

    @Bean
    public COSClient createCOSClient(){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = createCOSCredentials();
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        return cosclient;
    }
}
