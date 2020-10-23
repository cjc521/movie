package com.cjc.movie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.Movie;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjc.movie.entity.vo.MovieVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
public interface MovieService extends IService<Movie> {
    Page<MovieVO> selectMovies(Page<MovieVO> page,Integer areaId,Integer movieTypeId);
    Page<MovieVO> selectMoviesBySearch(Page<MovieVO> page,Map req);
    boolean addMovie(Map map);
    MovieVO selectMovie(Integer id);
    List<MovieVO> selectOtherMovies(String type);
}
