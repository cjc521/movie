package com.cjc.movie.controller;


import com.cjc.movie.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
@Controller
public class SystemMenuController {
    @Autowired
    private SystemMenuService systemMenuService;

    @ResponseBody
    @RequestMapping("/menus")
    public Map<String,Object> menu(){
        return systemMenuService.menu();
    }

}

