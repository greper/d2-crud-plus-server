package com.veryreader.d2p.api.modules.ueditor;

import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.Base64Uploader;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2020/6/3 0003$
 */
public class CustomUploader {
    private HttpServletRequest request = null;
    private Map<String, Object> conf = null;

    public CustomUploader(HttpServletRequest request, Map<String, Object> conf) {
        this.request = request;
        this.conf = conf;
    }

    public State doExec() {
        String filedName = (String) this.conf.get("fieldName");
        State state = null;

        if ("true".equals(this.conf.get("isBase64"))) {
            state = Base64Uploader.save(this.request.getParameter(filedName),
                    this.conf);
        } else {
            state = CustomBinaryUploader.save(this.request, this.conf);
        }

        return state;
    }
}
