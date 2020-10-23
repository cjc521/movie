package com.cjc.movie.controller;


import com.cjc.movie.entity.Area;
import com.cjc.movie.entity.Permission;
import com.cjc.movie.service.AreaService;
import com.cjc.movie.service.PermissionService;
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
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permissions")
    public Resp permissions(){
        List<Permission> permissions = permissionService.list(null);
        Resp resp = new Resp();
        resp.setCode(0);
        if(permissions.size()==0){
            resp.setMsg("抱歉，暂无查到数据！");
        }
        resp.setData(permissions);
        return resp;
    }
    @GetMapping("/permission")
    public Resp permission(@RequestParam Integer id){
        Permission permission = permissionService.getById(id);
        Resp resp = new Resp();
        resp.setCode(0);
        resp.setData(permission);
        return resp;
    }
    @DeleteMapping("/permission")
    public Resp delPermission(@RequestBody(required = false) Integer id){
        boolean flag = permissionService.removeById(id);
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
    @DeleteMapping("/permissions")
    public Resp delPermissions(@RequestBody(required = false) ArrayList<Integer> ids){
        boolean flag = permissionService.removeByIds(ids);
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
    @PostMapping(value = "/permission")
    public Resp addPermission(
            @RequestBody(required = false) Map<String,Object> map
    ) {
        Permission permission = CastUtil.mapToEntity(map, Permission.class);
        Resp resp = new Resp();
        boolean flag = permissionService.save(permission);
        if(flag){
            resp.setCode(0);
            resp.setMsg("添加成功");
        }else {
            resp.setCode(1);
            resp.setMsg("添加失败");
        }
        return resp;
    }

    @PutMapping(value = "/permission")
    public Resp update(
            @RequestBody(required = false) Permission permission
    ){
        Resp resp = new Resp();
        boolean flag = permissionService.updateById(permission);
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

