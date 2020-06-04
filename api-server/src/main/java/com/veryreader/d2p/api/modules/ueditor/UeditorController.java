package com.veryreader.d2p.api.modules.ueditor;

import cn.hutool.core.io.IoUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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

    private static final String FILE_DIR = System.getProperty("java.io.tmpdir");

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
            response.getWriter().write(new CustomActionEnter( request, FILE_DIR ).exec());
            response.getWriter().flush();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

    }

    @RequestMapping("/jsp/**")
    public void download(HttpServletRequest request, HttpServletResponse response)  {
        String key = request.getRequestURI();
        key = key.replace("/api","");
        String fileDiskPath =  FILE_DIR + key;
        File dest = new File(fileDiskPath);
        String fileName = key.substring(key.lastIndexOf("/"));
        //设置为png格式的文件
        response.setHeader("content-type", "image/png");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(dest));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            log.error("下载失败",e);
        } finally {
            IoUtil.close(bis);
            IoUtil.close(outputStream);
        }
        //成功后返回成功信息
    }




}
