package com.veryreader.d2p.api.modules.uploader.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.qiniu.util.Auth;
import com.veryreader.d2p.api.modules.uploader.config.QiniuUploaderConfig;
import com.veryreader.d2p.api.model.vo.Ret;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/8/30$
 */
@RestController
@RequestMapping("/upload/qiniu")
@Slf4j
@AllArgsConstructor
public class QiniuController {
    private QiniuUploaderConfig qiniu;

    /**
     *
     *
     * @return
     */
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public Ret getQiniuToken() {
        String accessKey = qiniu.getAccessKey();
        String secretKey = qiniu.getSecretKey();
        String bucket = qiniu.getBucket();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Map<String,Object> map = new HashMap<>(2);
        map.put("token",upToken);
        //默认3600l
        map.put("expires",3600);
        return Ret.success("",map);
    }


    /**
     * 跳转到已签名下载地址
     *
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response, @RequestParam String key, @RequestParam(value = "bucket",required = false) String bucket) throws IOException {

        String domainOfBucket = qiniu.getDomain();
       // String encodedFileName = URLEncoder.encode(key, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", domainOfBucket, key);
        String accessKey = qiniu.getAccessKey();
        String secretKey = qiniu.getSecretKey();
        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        long expire = System.currentTimeMillis() +expireInSeconds*1000;
        response.sendRedirect(finalUrl);
        response.setDateHeader("expries",expire);
    }


}
