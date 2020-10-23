package com.cjc.movie.controller;

import com.cjc.movie.entity.Movie;
import com.cjc.movie.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:  配置页面跳转
 * @author: cjc
 * @time: 2020/10/12 21:17
 */
@Controller

public class RestController {
    //跳转错误页面
    @RequestMapping("err/{url}")
    public String toErr(@PathVariable String url){
        return ("/exception/"+url);
    }
    @RequestMapping("404")
    public String toErr(){
        return "/exception/404";
    }
    @RequestMapping("/main")
    public String toIndex(){
        return "/reception/main";
    }

//    @RequestMapping("/admin-user")
//    public String ToUserManage(){
//        return "admin/admin-userMange";
//    }
//    @RequestMapping("/admin-main")
//    public String toMain(){
//        return "admin/admin-main";
//    }
//    @RequestMapping("/admin-index")
//    public String toIndex(){
//        return "admin/admin-index";
//    }
//    @RequestMapping("/admin-movieMange")
//    public String toMovieMange(){
//        return "admin/admin-movieMange";
//    }
//    @RequestMapping("/admin-userMange")
//    public String toUserMange(){
//        return "admin/admin-userMange";
//    }


    @RequestMapping("/admin/{url}")
    public String url(@PathVariable String url){
        System.out.println("begin"+url);
        if (url.contains(".html")){
            url = url.split(".html")[0];
        }
        return url;
    }
    @RequestMapping("/reception/{url}")
    public String toReception(@PathVariable String url){
        url="reception"+"/"+url;
        if (url.contains(".html")){
            url = url.split(".html")[0];
        }
        return url;
    }

   /* @RequestMapping("/{url}")
    public String url2(@PathVariable String url){
        System.out.println("begin"+url);
        if (url.contains(".html")){
            url = url.split(".html")[0];
        }
        System.out.println("end"+url);
        if(url.contains("admin-")){
            url="admin"+"/"+url;
        }else if (url.contains("user-")){
            url="user"+"/"+url;
        }
        return url;
    }*/

}
