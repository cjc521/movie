package com.cjc.movie.config;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/9 9:50
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("==========静态资源拦截！============");
      /*  ApplicationHome applicationHome = new ApplicationHome(getClass());
        File jarF = applicationHome.getSource();
        //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
        String dirPath = jarF.getParentFile().toString()+"/upload/poster/";*/

        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        //将所有/poster/** 访问都映射到 file:D:/photo/poster/ 目录下
        //将所有/upload/poster/** 访问都映射到 file:/upload/poster/目录下
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/movie/poster/**").addResourceLocations("file:D:/myUpload/movie/poster/");
//        registry.addResourceHandler("/upload/poster/**").addResourceLocations("file:/"+dirPath);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT","PATCH")
                .maxAge(3600);
    }
}
