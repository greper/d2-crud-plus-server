package com.veryreader.d2p.api.modules.uploader.controller;

import com.qiniu.util.Auth;
import com.veryreader.d2p.api.modules.uploader.config.QiniuUploaderConfig;
import com.veryreader.d2p.api.model.vo.Ret;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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



}
