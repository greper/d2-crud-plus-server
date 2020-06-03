package com.veryreader.d2p.api.modules.ueditor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/8/30$
 */
@RestController
@RequestMapping("/ueditor")
@Slf4j
@AllArgsConstructor
public class UeditorController {
    private String rootPath;

    public UeditorController() {
        try {
            rootPath = ResourceUtils.getURL("static").getPath();
            //rootPath = rootPath.replace("file:","");
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(),e);
        }

    }

    /**
     *
     * 上传示例仅供参考，切勿上生产环境
     * @return
     */
    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{

            log.debug("rootPath:{}",rootPath);
            request.setCharacterEncoding( "utf-8" );
            response.setHeader("Content-Type" , "text/html");
            response.getWriter().write(new CustomActionEnter( request, rootPath ).exec());
            response.getWriter().flush();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

    }




}
