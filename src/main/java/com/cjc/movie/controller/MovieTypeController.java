package com.cjc.movie.controller;


import com.cjc.movie.entity.Area;
import com.cjc.movie.entity.MovieType;
import com.cjc.movie.service.MovieTypeService;
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
public class MovieTypeController {

    @Autowired
    private MovieTypeService movieTypeService;

    @GetMapping("/movieTypes")
    public Resp types(){
        List<MovieType> movieTypes = movieTypeService.list(null);
        Resp resp = new Resp();
        resp.setCode(0);
        resp.setData(movieTypes);
        return resp;
    }
    @GetMapping("/movieType")
    public Resp area(@RequestParam Integer id){
        MovieType movieType = movieTypeService.getById(id);
        Resp resp = new Resp();
        resp.setCode(0);
        resp.setData(movieType);
        return resp;
    }
   
    @DeleteMapping("/movieType")
    public Resp delmovieType(@RequestBody(required = false) Integer id){
        boolean flag = movieTypeService.removeById(id);
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
    @DeleteMapping("/movieTypes")
    public Resp delmovieTypes(@RequestBody(required = false) ArrayList<Integer> ids){
        boolean flag = movieTypeService.removeByIds(ids);
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
    @PostMapping(value = "/movieType")
    public Resp addMovie(
            @RequestBody(required = false) Map<String,Object> map
    ) {
        MovieType movieType = CastUtil.mapToEntity(map, MovieType.class);
        Resp resp = new Resp();
        boolean flag = movieTypeService.save(movieType);
        if(flag){
            resp.setCode(0);
            resp.setMsg("添加成功");
        }else {
            resp.setCode(1);
            resp.setMsg("添加失败");
        }
        return resp;
    }

    @PutMapping(value = "/movieType")
    public Resp update(
            @RequestBody(required = false) MovieType movieType
    ){
        Resp resp = new Resp();
        boolean flag = movieTypeService.updateById(movieType);
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

