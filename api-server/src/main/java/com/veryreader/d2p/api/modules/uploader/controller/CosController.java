package com.veryreader.d2p.api.modules.uploader.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.tencent.cloud.CosStsClient;
import com.veryreader.d2p.api.modules.uploader.config.CosUploaderConfig;
import com.veryreader.d2p.api.model.vo.Ret;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/8/30$
 */
@RestController
@RequestMapping("/upload/cos")
@Slf4j
@AllArgsConstructor
public class CosController {

    private static final long DEFAULT_EXPIRE_TIME = 5*60*1000;
    private CosUploaderConfig cosConfig;
    private COSClient cosClient;
    /**
     *
     *
     * @return
     */
    @RequestMapping(value = "/getUploadUrl", method = RequestMethod.GET)
    public Ret getUploadUrl(@RequestParam String key,@RequestParam(value = "bucket",required = false) String bucket) {
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(5分钟)
        Date expirationTime = new Date(System.currentTimeMillis() + DEFAULT_EXPIRE_TIME);
        if(bucket == null){
            bucket = cosConfig.getBucket();
        }
        URL url = cosClient.generatePresignedUrl(bucket, key, expirationTime, HttpMethodName.PUT);
        String uploadUrl = url.toString().replace("http://", "https://");
        return Ret.success("",uploadUrl);
    }
    /**
     *
     * 获取临时密钥
     * @return
     */
    @RequestMapping(value = "/getAuthorization", method = RequestMethod.GET)
    public Ret getAuthorization(@RequestParam(value = "bucket",required = false) String bucket,
                                @RequestParam(value = "region",required = false) String region,
                                @RequestParam(value = "allowPrefix",required = false) String allowPrefix) throws IOException {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        if(bucket == null){
            bucket = cosConfig.getBucket();
        }
        if(region == null){
            region = cosConfig.getRegion();
        }
        // 替换为您的 SecretId
        config.put("SecretId", cosConfig.getSecretId());
        // 替换为您的 SecretKey
        config.put("SecretKey", cosConfig.getSecretKey());

        // 临时密钥有效时长，单位是秒，默认1800秒，最长可设定有效期为7200秒
        config.put("durationSeconds", 1800);

        // 换成您的 bucket
        config.put("bucket", bucket);
        // 换成 bucket 所在地区
        config.put("region", region);

        // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子：a.jpg 或者 a/* 或者 * 。
        // 如果填写了“*”，将允许用户访问所有资源；除非业务需要，否则请按照最小权限原则授予用户相应的访问权限范围。
        if(allowPrefix==null){
            allowPrefix = "*";
        }
        config.put("allowPrefix",allowPrefix);
        // 密钥的权限列表。简单上传、表单上传和分片上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
        String[] allowActions = new String[] {
                // 简单上传
                "name/cos:PutObject",
        };
        config.put("allowActions", allowActions);

        JSONObject credential = CosStsClient.getCredential(config);
        JSONObject credentials = credential.getJSONObject("credentials");
        Map<String,Object> map = new HashMap<>();
        map.put("TmpSecretId",credentials.getString("tmpSecretId"));
        map.put("TmpSecretKey",credentials.getString("tmpSecretKey"));
        map.put("XCosSecurityToken",credentials.getString("sessionToken"));
        map.put("ExpiredTime",credential.getString("expiration"));

        //成功返回临时密钥信息，如下打印密钥信息
        log.info("credential:{}",map);
        return Ret.success("", map );
    }


}
