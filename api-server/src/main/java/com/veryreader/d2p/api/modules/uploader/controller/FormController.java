package com.veryreader.d2p.api.modules.uploader.controller;

import cn.hutool.core.date.DateUtil;
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
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2019/8/30$
 */
@Controller
@RequestMapping("/upload/form")
@Slf4j
@AllArgsConstructor
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
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //成功后返回成功信息
    }
    /**
     * @return
     */
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    @ResponseBody
    public Ret<String> upload(HttpServletRequest request,@RequestParam(value="file", required=false) MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return Ret.error("上传失败，请选择文件",null);
        }

        String oname = file.getOriginalFilename();
        String ext = "";
        if(StringUtils.isNotBlank(oname)&& oname.lastIndexOf(".")>=0){
            ext = oname.substring(oname.lastIndexOf("."));
        }
        String fileName = RandomUtil.randomString(10);

        String dir = "/"+ DateUtil.format(new Date(),"yyyy-MM-dd");
        String key = dir + "/"+fileName+ext;
        File dirFile = new File(FILE_DIR+dir);
        if(!dirFile.exists()){
            boolean mkdirs = dirFile.mkdirs();
        }
        String fileDiskPath =  FILE_DIR + key;

        File dest = new File(fileDiskPath);
        file.transferTo(dest);
        return  Ret.success("","/upload/form/download?key="+key);
    }




}
