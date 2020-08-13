package com.veryreader.d2p.api.modules.uploader.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.veryreader.d2p.api.model.vo.Ret;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @Description: 文件默认存储在临时目录下，上生产前需要注意
 * @Author: xiaojunnuo
 * @CreateDate: 2019/8/30$
 */
@Controller
@RequestMapping("/upload/form")
@Slf4j
public class FormController {

    private static final String FILE_DIR = System.getProperty("java.io.tmpdir");

    @Value("${server.url.prefix:/api}")
    private String urlPrefix;
    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/download",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam("key") String key)  {
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
    /**
     * 文件默认存储在临时目录下，如果你要直接用这个上生产的话，需要注意
     * @return
     */
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    @ResponseBody
    public Ret<String> upload(HttpServletRequest request,@RequestParam(value="file", required=false) MultipartFile file,
            @RequestParam("key") String key) throws IOException {
        if (file.isEmpty()) {
            return Ret.error("上传失败，请选择文件",null);
        }


        if(key == null){
            String oname = file.getOriginalFilename();
            key = buildKey(oname);
        }
        String dir = key.substring(0,key.lastIndexOf("/"));
        File dirFile = new File(FILE_DIR+dir);
        if(!dirFile.exists()){
            boolean mkdirs = dirFile.mkdirs();
        }
        String fileDiskPath =  FILE_DIR + key;

        File dest = new File(fileDiskPath);
        file.transferTo(dest);
        return  Ret.success("",urlPrefix+"/upload/form/download?key="+key);
    }

    private String buildKey(String oname) {
        String ext = "";
        if(StringUtils.isNotBlank(oname)&& oname.lastIndexOf(".")>=0){
            ext = oname.substring(oname.lastIndexOf("."));
        }
        String fileName = RandomUtil.randomString(10);

        String dir = "/"+ DateUtil.format(new Date(),"yyyy-MM-dd");
        String key = dir + "/"+fileName+ext;
        return key;
    }


}
