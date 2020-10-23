package com.cjc.movie.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.Remark;
import com.cjc.movie.entity.vo.RemarkVO;
import com.cjc.movie.mapper.RemarkMapper;
import com.cjc.movie.service.RemarkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RemarkServiceImpl extends ServiceImpl<RemarkMapper, Remark> implements RemarkService {
    @Autowired
    private RemarkMapper remarkMapper;
    @Override
    public Page<RemarkVO> selectRemarks(Page<RemarkVO> page, Integer movieId) {
        return  page.setRecords(remarkMapper.selectRemarks(page,movieId));
    }
}
