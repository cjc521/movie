package com.cjc.movie.service.impl;

import com.cjc.movie.entity.MovieType;
import com.cjc.movie.mapper.MovieTypeMapper;
import com.cjc.movie.service.MovieTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
@Service
public class MovieTypeServiceImpl extends ServiceImpl<MovieTypeMapper, MovieType> implements MovieTypeService {

}
