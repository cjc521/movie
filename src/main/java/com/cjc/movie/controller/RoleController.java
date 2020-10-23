package com.cjc.movie.controller;


import com.cjc.movie.entity.Area;
import com.cjc.movie.entity.Role;
import com.cjc.movie.service.AreaService;
import com.cjc.movie.service.RoleService;
import com.cjc.movie.utils.CastUtil;
import com.cjc.movie.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public Resp roles(){
        List<Role> roles = roleService.list(null);
        Resp resp = new Resp();
        resp.setCode(0);
        if(roles.size()==0){
            resp.setMsg("抱歉，暂无查到数据！");
        }
        resp.setData(roles);
        return resp;
    }
    @GetMapping("/role")
    public Resp role(@RequestParam Integer id){
        Role role = roleService.getById(id);
        Resp resp = new Resp();
        resp.setCode(0);
        resp.setData(role);
        return resp;
    }
    @DeleteMapping("/role")
    public Resp delRole(@RequestBody(required = false) Integer id){
        boolean flag = roleService.removeById(id);
        Resp resp = new Resp();
        if(flag){
            resp.setCode(0);
            resp.setMsg("删除成功");
        }else {
            resp.setCode(1);
            resp.setMsg("删除失败");
        }
        return resp;
    }
    @DeleteMapping("/roles")
    public Resp delRoles(@RequestBody(required = false) ArrayList<Integer> ids){
        boolean flag = roleService.removeByIds(ids);
        Resp resp = new Resp();
        if(flag){
            resp.setCode(0);
            resp.setMsg("删除成功");
        }else {
            resp.setCode(1);
            resp.setMsg("删除失败");
        }
        return resp;
    }
    @PostMapping(value = "/role")
    public Resp addRole(
            @RequestBody(required = false) Map<String,Object> map
    ) {
        Role role = CastUtil.mapToEntity(map, Role.class);
        Resp resp = new Resp();
        boolean flag = roleService.save(role);
        if(flag){
            resp.setCode(0);
            resp.setMsg("添加成功");
        }else {
            resp.setCode(1);
            resp.setMsg("添加失败");
        }
        return resp;
    }

    @PutMapping(value = "/role")
    public Resp update(
            @RequestBody(required = false) Role role
    ){
        Resp resp = new Resp();
        boolean flag = roleService.updateById(role);
        if(flag){
            resp.setCode(0);
            resp.setMsg("修改成功");
        }else {
            resp.setCode(1);
            resp.setMsg("修改失败");
        }
        return resp;
    }
}

