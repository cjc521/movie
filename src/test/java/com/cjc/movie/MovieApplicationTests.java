package com.cjc.movie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.Area;
import com.cjc.movie.entity.Movie;
import com.cjc.movie.entity.SystemMenu;
import com.cjc.movie.entity.User;
import com.cjc.movie.entity.vo.MovieVO;
import com.cjc.movie.mapper.AreaMapper;
import com.cjc.movie.mapper.MovieMapper;
import com.cjc.movie.mapper.MovieTypeMapper;
import com.cjc.movie.mapper.SystemMenuMapper;
import com.cjc.movie.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MovieApplicationTests {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieTypeMapper movieTypeMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
      /*  List<SystemMenu> menus = systemMenuMapper.selectList(null);
        System.out.println(menus);*/
    }

    @Test
    void test() {
//        QueryWrapper<SystemMenu> wrapper = new QueryWrapper<>();
        QueryWrapper<Area> wrapper = new QueryWrapper<>();
        wrapper.eq("id", "1");
        Area menu = areaMapper.selectById(1L);
        System.out.println(menu);
        List<Area> areas = areaMapper.selectList(null);
        System.out.println(areas.size());

    }

    @Test
    void test1() {
        Page<MovieVO> movieVOPage = new Page<>(1, 10);
        Page<Movie> moviePage = new Page<>(1, 10);
        IPage<Movie> movieIPage = movieMapper.selectPage(moviePage, null);
        System.out.println(movieIPage.getRecords());
        System.out.println(movieIPage);
    }

    @Test
    void testJson() throws JSONException, JsonProcessingException {
        String s = "{\"id\": 1,\"name\": \"小明\",\"array\": [\"1\", \"2\"]}";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(16);
        //Json映射为对象
//        Student student = mapper.readValue(s, Student.class);
        Map map1 = mapper.readValue(s, map.getClass());
        System.out.println(map1);
        //对象转化为Json
//        String json = mapper.writeValueAsString(student);
//        System.out.println(json);
//        System.out.println(student.toString());
    }

    @Test
    void testDel() {
        movieMapper.deleteById(18);
    }

    @Test
    void testUpload() {
        String path = new String("http://localhost:8081/movie/poster/" + "1.jpg");
        System.out.println(path);
    }

    @Test
    void testRemoveIds() {

        ArrayList<Integer> list = new ArrayList<>(20);
        list.add(3);
        list.add(4);
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.in("id", list);
        int delete = movieMapper.delete(wrapper);
//        int delete = movieMapper.deleteById(2);
        System.out.println(delete);
    }

    @Test
    void testSelectUsers() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(null!=null,"name", null);
        wrapper.eq(null != null, "gender", null);
        System.out.println("wrapper:" + wrapper);
        IPage<User> userPage = userService.page(new Page<User>(1, 5), wrapper);
        if (userPage.getRecords().size() == 0) {
        }

    }
}