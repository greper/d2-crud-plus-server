package com.veryreader.d2p.api.config;

import com.veryreader.d2p.api.web.interceptor.CORSInterceptor;
import com.veryreader.d2p.api.web.interceptor.DemoInterceptor;
import com.veryreader.d2p.api.web.interceptor.GlobalExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author xiaojunnuo
 */
@Configuration("ApiWebConfig" )
@Primary
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private DemoInterceptor demoInterceptor;
    @Autowired
    private CORSInterceptor corsInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(getGlobalExceptionResolver());
    }


    @Bean
    GlobalExceptionResolver getGlobalExceptionResolver() {
        return new GlobalExceptionResolver();
    }
}

