package com.veryreader.d2p.api.modules.uploader.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.qcloud.cos.http.HttpMethodName;
import com.veryreader.d2p.api.modules.uploader.config.AliossUploaderConfig;
import com.veryreader.d2p.api.model.vo.Ret;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/8/30$
 */
@RestController
@RequestMapping("/upload/alioss")
@Slf4j
@AllArgsConstructor
public class AliossController {
    private AliossUploaderConfig aliossConfig;
    private static final long DEFAULT_EXPIRE_TIME = 3600*1000;
    /**
     * @return
     */
    @RequestMapping(value = "/getUploadUrl", method = RequestMethod.GET)
    public Ret getUploadUrl(@RequestParam(value = "key") String key,
                            @RequestParam(value = "bucket", required = false) String bucket,
                            @RequestParam(value = "endpoint", required = false) String endpoint) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        if (endpoint == null) {
            endpoint = aliossConfig.getEndpoint();
        }
        if (bucket == null) {
            bucket = aliossConfig.getBucket();
        }
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = aliossConfig.getAccessKey();
        String accessKeySecret = aliossConfig.getAccessKeySecret();

        String objectName = key;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        long expire = System.currentTimeMillis() +DEFAULT_EXPIRE_TIME;
        Date expirationTime = new Date( expire);
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucket, objectName, expiration);

        // 关闭OSSClient。
        ossClient.shutdown();
        return Ret.success("", url.toString());
    }


    /**
     * 跳转到已签名下载地址
     *
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response, @RequestParam String key, @RequestParam(value = "bucket",required = false) String bucket) throws IOException {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = aliossConfig.getEndpoint();
        if (bucket == null) {
            bucket = aliossConfig.getBucket();
        }
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = aliossConfig.getAccessKey();
        String accessKeySecret = aliossConfig.getAccessKeySecret();

        String objectName = key;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        long expire = System.currentTimeMillis() +DEFAULT_EXPIRE_TIME;
        // 设置URL过期时间为1小时。
        Date expiration = new Date(expire);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucket, objectName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        response.sendRedirect(url.toString());
        response.setDateHeader("expries",expire);
    }

    @RequestMapping(value = "/getAuthorization", method = RequestMethod.GET)
    public Ret getAuthorization() throws  ClientException {
        String endpoint = "sts.aliyuncs.com";
        String accessKeyId = aliossConfig.getAccessKey();
        String accessKeySecret = aliossConfig.getAccessKeySecret();
        String roleArn =aliossConfig.getRoleArn();

        String roleSessionName = "session-name";
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:*\"\n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:*\" \n" +  //acs:oss:*:*:my-bucket/*
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setSysEndpoint(endpoint);
            request.setSysMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            // request.setPolicy(policy);
            final AssumeRoleResponse response = client.getAcsResponse(request);
//            System.out.println("Expiration: " + response.getCredentials().getExpiration());
//            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
//            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
//            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
//            System.out.println("RequestId: " + response.getRequestId());
            return Ret.success("",response.getCredentials());

    }

}
