package com.cjc.movie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.User;
import com.cjc.movie.service.UserService;
import com.cjc.movie.utils.CastUtil;
import com.cjc.movie.utils.Resp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Resp users(
            @RequestParam(value = "user",required =false) String map,
            @RequestParam(value = "page",required =false ,defaultValue="1") Integer pageNum,
            @RequestParam(value = "limit",required =false ,defaultValue= "6")Integer pageSize
    ) throws JsonProcessingException {
        System.out.println(map);
        Resp resp = new Resp();
        IPage<User> userPage;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(map!=null) {
            Map jsonMap = new ObjectMapper().readValue(map, Map.class);
            wrapper.like((jsonMap.get("name") != null && !"".equals(jsonMap.get("name"))), "name", jsonMap.get("name"));
            wrapper.eq((jsonMap.get("gender") != null && !"".equals(jsonMap.get("gender"))), "gender", jsonMap.get("gender"));
        }
        userPage = userService.page(new Page<User>(pageNum, pageSize), wrapper);
        if(userPage.getRecords().size() == 0){
            resp.setCode(1);
            resp.setMsg("暂时没有数据");
        }else {
            resp.setCode(0);
            resp.setCount((int) userPage.getTotal());
            resp.setData( userPage.getRecords());
        }
        return resp;
    };

    @DeleteMapping(value="/users")
    public Resp deleteusers(
            @RequestBody ArrayList<Integer> ids
    ) {
        Resp resp = new Resp();
        System.out.println(ids);
        boolean flag = userService.removeByIds(ids);
        if(flag){
            resp.setCode(0);
            resp.setMsg("删除成功");
        }else {
            resp.setCode(1);
            resp.setMsg("删除失败");
        }
        return resp;
    }
    @DeleteMapping(value="/user") //删除 user
    public Resp removeUser(
            @RequestBody(required = false) int id ){
        Resp resp = new Resp();
        boolean flag = userService.removeById(id);
        if(flag){
            resp.setCode(0);
            resp.setMsg("删除成功");
        }else {
            resp.setCode(1);
            resp.setMsg("删除失败");
        }
        return resp;
    }

    @PostMapping(value = "/user")
    public Resp adduser(
            @RequestBody(required = false) Map<String,Object> map
//           @RequestParam(required = false) user user
    ) {


        User user = CastUtil.mapToEntity(map,User.class);
        System.out.println(map);
        Resp resp = new Resp();
        boolean flag = userService.save(user);
        if(flag){
            resp.setCode(0);
            resp.setMsg("添加成功");
        }else {
            resp.setCode(1);
            resp.setMsg("添加失败");
        }
        return resp;
    }

    @PutMapping(value = "/user")
    public Resp update(
//            @RequestBody(required = false) Map<String,Object> map
            @RequestBody(required = false) User user
    ){
        Resp resp = new Resp();
        boolean flag = userService.updateById(user);
        if(flag){
            resp.setCode(0);
            resp.setMsg("修改成功");
        }else {
            resp.setCode(1);
            resp.setMsg("修改失败");
        }
        return resp;
    }

    @GetMapping("/user")
    public Resp user(@RequestParam Integer id){
        User user = userService.getById(id);
        return new Resp(0,user);
    }

}

