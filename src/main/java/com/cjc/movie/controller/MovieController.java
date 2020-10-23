package com.cjc.movie.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.Movie;
import com.cjc.movie.entity.vo.MovieVO;
import com.cjc.movie.entity.vo.RemarkVO;
import com.cjc.movie.service.MovieService;
import com.cjc.movie.service.RemarkService;
import com.cjc.movie.utils.CastUtil;
import com.cjc.movie.utils.Resp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
@Controller
//@RestController
public class MovieController {
    @Autowired
    private RemarkService remarkService;
    @Autowired
    private MovieService movieService;

    @ResponseBody
    @PostMapping(value = "/movie")
    public Resp addMovie(
            @RequestBody(required = false) Map<String,Object> map
//           @RequestParam(required = false) Movie movie
    ) {
        Movie movie = CastUtil.mapToEntity(map, Movie.class);
        System.out.println(map);
        Resp resp = new Resp();
        boolean flag = movieService.save(movie);
        if(flag){
            resp.setCode(0);
            resp.setMsg("添加成功");
        }else {
            resp.setCode(1);
            resp.setMsg("添加失败");
        }
        return resp;
    }

    @ResponseBody
    @DeleteMapping(value="/movies")
    public Resp deleteMovies(
//            @RequestBody(required = false) int[] ids,
            @RequestBody ArrayList<Integer> ids
            ) {
        Resp resp = new Resp();
        System.out.println(ids);
        boolean flag = movieService.removeByIds(ids);
        if(flag){
            resp.setCode(0);
            resp.setMsg("删除成功");
        }else {
            resp.setCode(1);
            resp.setMsg("删除失败");
        }
        return resp;
    }
    @DeleteMapping(value="/movie") //删除 movie
    public Resp removeMovie(
            @RequestBody(required = false) int id ){
        Resp resp = new Resp();
        boolean flag = movieService.removeById(id);
        if(flag){
            resp.setCode(0);
            resp.setMsg("删除成功");
        }else {
            resp.setCode(1);
            resp.setMsg("删除失败");
        }
        return resp;
    }


    @ResponseBody
    @PutMapping(value = "/movie")
    public Resp update(
//            @RequestBody(required = false) Map<String,Object> map
            @RequestBody(required = false) Movie movie
    ){
        System.out.println(movie);
        Resp resp = new Resp();
        boolean flag = movieService.updateById(movie);
        if(flag){
            resp.setCode(0);
            resp.setMsg("修改成功");
        }else {
            resp.setCode(1);
            resp.setMsg("修改失败");
        }
        return resp;
    }
    @ResponseBody
    @GetMapping("/movie")
    public Resp movie(@RequestParam Integer id){
        Movie movie = movieService.getById(id);
        return new Resp(0,movie);
    }

    @ResponseBody
    @GetMapping("/movies")
    public Map<String, Object> Movies(
            @RequestParam(value="movieTypeId",required = false,defaultValue = "0") Integer movieTypeId,
            @RequestParam(value="areaId",required = false,defaultValue = "0") Integer areaId,
            @RequestParam(value = "page",required =false ,defaultValue="1") Integer pageNum,
            @RequestParam(value = "limit",required =false ,defaultValue= "6")Integer pageSize
    ){
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(areaId+"-"+movieTypeId+"-"+pageSize);
        Page<MovieVO> movieVOPage = movieService.selectMovies(new Page<>(pageNum, pageSize),areaId,movieTypeId);
        if(movieVOPage.getRecords().size() == 0){
            map.put("code", 1);
            map.put("msg", "暂时没有数据");
        }else {
            map.put("code", 0);
            map.put("count", movieVOPage.getTotal());
            map.put("data", movieVOPage.getRecords());
        }
        return map;
    };

    @ResponseBody
    @GetMapping("/movieBySearch")
    public Map<String, Object> movieBySearch(
            @RequestParam(required = false) String searchParams,
            @RequestParam(value = "page",required =false ,defaultValue="1") Integer pageNum,
            @RequestParam(value = "limit",required =false ,defaultValue= "6")Integer pageSize
    ) throws JsonProcessingException {
        System.out.println("1"+searchParams);
        ObjectMapper objectMapper = new ObjectMapper();
        Map jsonMap = objectMapper.readValue(searchParams, Map.class);

        System.out.println("3"+jsonMap);
        HashMap<String, Object> map = new HashMap<>();

        Page<MovieVO> movieVOPage = movieService.selectMoviesBySearch(new Page<>(pageNum, pageSize),jsonMap);
        if(movieVOPage.getRecords().size() == 0){
            map.put("code", 1);
            map.put("msg", "暂时没有数据");
        }else {
            map.put("code", 0);
            map.put("count", movieVOPage.getTotal());
            map.put("data", movieVOPage.getRecords());
        }
        return map;
    };

    @GetMapping("/movieDetail")
    public String movieDetail(
//            @PathVariable("id") String id
            @RequestParam String id,
            @RequestParam(required = false,defaultValue = "") String type
            , ModelMap modelMap
            ){
        Integer id1 = Integer.parseInt(id);

        MovieVO movieVO = movieService.selectMovie(id1);
        List<MovieVO> movieVOs= movieService.selectOtherMovies(type);
        modelMap.put("movieVO",movieVO);
        modelMap.put("movieVOs",movieVOs);
        return "reception/movie-detail";
    }

    @GetMapping("/movie-broadcast")
    public String movieBroadcast(
            ModelMap modelMap,
      @RequestParam(required = false,defaultValue = "") String film ,
      @RequestParam(required = false,defaultValue = "1") Integer page,
      @RequestParam(required = false,defaultValue = "10") Integer limit,
      @RequestParam(required = false,defaultValue = "") Integer movieId
    ){
        if(("".equals( film) || film==null)||("".equals( movieId) || movieId==null)){
            return "/exception/404";
        }
        Page<RemarkVO> remarks = remarkService.selectRemarks(new Page<RemarkVO>(page, limit), movieId);
        modelMap.put("remarks",remarks.getRecords());
        System.out.println(remarks.getRecords());
        modelMap.put("film",film);
        return "/reception/movie-broadcast";
    }
}

