package com.cjc.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.Movie;
import com.cjc.movie.entity.vo.MovieVO;
import com.cjc.movie.mapper.MovieMapper;
import com.cjc.movie.service.MovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Page<MovieVO> selectMovies(Page<MovieVO> page,Integer areaId,Integer movieTypeId ) {
        return page.setRecords(movieMapper.selectMovies(page,areaId,movieTypeId));
    }

    @Override
    public Page<MovieVO> selectMoviesBySearch(Page<MovieVO> page,Map req) {
        String name=null;
        Double minScore=null;
        Double maxScore=null;
        if( !"".equals(req.get("name"))){
            name = (String)req.get("name");
        }
        if( !"".equals(req.get("minScore"))){
            minScore = Double.parseDouble((String)req.get("minScore"));
        }
        if( !"".equals(req.get("maxScore"))){
            minScore = Double.parseDouble((String)req.get("maxScore"));
        }
        return page
                .setRecords(movieMapper
                        .selectMoviesBySearch(page,name,minScore,maxScore));
    }

    @Override
    public boolean addMovie(Map map) {
        int count = movieMapper.addMovie(map);
        if(count==1){
            return true;
        }
        return false;
    }

    @Override
    public MovieVO selectMovie(Integer id) {
        return movieMapper.selectMovie(id);
    }

    @Override
    public List<MovieVO> selectOtherMovies(String type) {
        return movieMapper.selectOtherMovies(type);
    }
}
