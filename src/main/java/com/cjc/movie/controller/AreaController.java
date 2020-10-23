package com.cjc.movie.controller;


import com.cjc.movie.entity.Area;
import com.cjc.movie.entity.Movie;
import com.cjc.movie.service.AreaService;
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
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/areas")
    public Resp areas(){
        List<Area> areas = areaService.list(null);
        Resp resp = new Resp();
        resp.setCode(0);
        resp.setData(areas);
        return resp;
    }
    @GetMapping("/area")
    public Resp area(@RequestParam Integer id){
        Area area = areaService.getById(id);
        Resp resp = new Resp();
        resp.setCode(0);
        resp.setData(area);
        return resp;
    }
    @DeleteMapping("/area")
    public Resp delArea(@RequestBody(required = false) Integer id){
        boolean flag = areaService.removeById(id);
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
    @DeleteMapping("/areas")
    public Resp delAreas(@RequestBody(required = false) ArrayList<Integer> ids){
        boolean flag = areaService.removeByIds(ids);
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
    @PostMapping(value = "/area")
    public Resp addrea(
            @RequestBody(required = false) Map<String,Object> map
    ) {
        Area area = CastUtil.mapToEntity(map, Area.class);
        Resp resp = new Resp();
        boolean flag = areaService.save(area);
        if(flag){
            resp.setCode(0);
            resp.setMsg("添加成功");
        }else {
            resp.setCode(1);
            resp.setMsg("添加失败");
        }
        return resp;
    }

    @PutMapping(value = "/area")
    public Resp update(
            @RequestBody(required = false) Area area
    ){
        Resp resp = new Resp();
        boolean flag = areaService.updateById(area);
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

