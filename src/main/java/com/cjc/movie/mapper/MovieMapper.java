package com.cjc.movie.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.Movie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjc.movie.entity.vo.MovieVO;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
public interface MovieMapper extends BaseMapper<Movie> {

    List<MovieVO> selectMovies(
            @Param("page") Page<MovieVO> page,
            @Param("areaId") Integer areaId,
            @Param("movieTypeId") Integer movieTypeId);
    List<MovieVO> selectMoviesBySearch(
            @Param("page") Page<MovieVO> page,
            @Param("name") String name,
            @Param("minScore")Double minScore,
            @Param("maxScore")Double maxScore);

    int addMovie(Map map);
    MovieVO selectMovie(@Param("id") Integer id);
    List<MovieVO> selectOtherMovies(@Param("type") String type);

}
