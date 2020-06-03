package com.veryreader.d2p.api.modules.ueditor;

import com.baidu.ueditor.CustomActionEnter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
        String path  = UeditorController.class.getClassLoader().getResource("application.yaml").getPath();
        File file =  new File(path);
        if(file.getParentFile().isDirectory()) {
            rootPath = new File(path).getParent()+"/static/";
        }else{
            rootPath = new File(path).getParentFile().getParent()+"/static";
        }
        rootPath = rootPath.replace("file:","");
    }

    /**
     *
     * 上传示例仅供参考，切勿上生产环境
     * @return
     */
    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{


            request.setCharacterEncoding( "utf-8" );
            response.setHeader("Content-Type" , "text/html");
            response.getWriter().write(new CustomActionEnter( request, rootPath ).exec());
            response.getWriter().flush();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

    }




}
