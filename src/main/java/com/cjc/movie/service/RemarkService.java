package com.cjc.movie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.Remark;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjc.movie.entity.vo.RemarkVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
public interface RemarkService extends IService<Remark> {
    Page<RemarkVO> selectRemarks(Page<RemarkVO> page,Integer movieId);
}
