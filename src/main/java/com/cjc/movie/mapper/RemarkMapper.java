package com.cjc.movie.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.Remark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjc.movie.entity.vo.RemarkVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
public interface RemarkMapper extends BaseMapper<Remark> {
    List<RemarkVO> selectRemarks(
            @Param("page") Page page,
            @Param("movieId") Integer movieId);

}
