package com.veryreader.d2p.api.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.veryreader.d2p.api.exceptions.ClientException;
import com.veryreader.d2p.api.model.vo.Ret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Description:    统一异常处理
* @Author:         xiaojunnuo
* @CreateDate:     2018/6/26  12:02
*/
@Component
@Order(-1)
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception e) {
        Ret result = null;
        ModelAndView mv = new ModelAndView();
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            Class targetClass = AopUtils.getTargetClass(handlerMethod.getBean());
            if (null != targetClass.getAnnotation(RestController.class)
                    || null != handlerMethod.getMethodAnnotation(ResponseBody.class)) {
                if( e.getMessage() == null ){
                    //空指针异常
                    result = Ret.error(10010001,"服务器内部错误：空指针异常",null);
                    log.error("空指针异常：{}->{}",e.getMessage(),request.getRequestURL(),e);
                }else if(e.getMessage()!=null && e.getMessage().contains("Read timed out") ){
                    //请求超时
                    int code = 10010002;
                    result = Ret.error(code,e.getMessage(),null);
                    log.error("请求超时：{}->{}",e.getMessage(),request.getRequestURL(),e);
                }else if(ServletRequestBindingException.class.isAssignableFrom(e.getClass()) ){
                    //请求参数错误
                    int code = 10010000;
                    result = Ret.error(code,e.getMessage(),null);
                    log.error("参数错误：{}->{}",e.getMessage(),request.getRequestURL(),e);
                }else if (e instanceof ClientException) {
                    //业务失败的异常，如“账号或密码错误”
                    ClientException be = (ClientException) e;
                    result = Ret.error(be.getCode(),e.getMessage(),be.getData());
                    log.error("操作失败：{}->{}", e.getMessage(), request.getRequestURL(),e);
                } else {
                    //String msg =  "服务器内部错误";
                    result = Ret.error(1,"服务器内部错误",null);
                    String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                            request.getRequestURI(),
                            handlerMethod.getBean().getClass().getName(),
                            handlerMethod.getMethod().getName(),
                            e.getMessage());
                    log.error("系统异常：{}->{}",message,request.getRequestURL(), e);
                }
                responseResult(response, result);
                return mv;
            } else {//返回页面类型的
                log.error(e.getMessage(), e);
                if (response.getStatus() == 404) {
                    mv.setViewName("error/404");
                } else {
                    mv.setViewName("error/500");
                }
            }
        } else {
            log.error(e.getMessage(), e);
            mv.setViewName("error/500");
        }
        //如果返回null，则交给后续的Exceptionresolver处理
        return mv;
    }

    private void responseResult(HttpServletResponse response, Ret result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

}
