package com.cjc.movie.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjc.movie.entity.vo.RemarkVO;
import com.cjc.movie.service.RemarkService;
import com.cjc.movie.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
@Controller
@RequestMapping("/remark")
public class RemarkController {
    @Autowired
private RemarkService remarkService;

    public Resp selectRemarks(
            @RequestParam(required = false,defaultValue = "") Integer movieId,
            @RequestParam(required = false,defaultValue = "1") Integer page,
            @RequestParam(required = false,defaultValue = "10") Integer limit
    ){
        Resp resp = new Resp();
        Page<RemarkVO> remarks = remarkService.selectRemarks(new Page<RemarkVO>(page, limit), movieId);
        if(remarks!=null){
            resp.setCount((int) remarks.getTotal());
            resp.setData(remarks.getRecords());
            resp.setCode(0);
        }else {
            resp.setCode(1);
            resp.setMsg("暂时无数据");
        }
        return resp;

    }


}

