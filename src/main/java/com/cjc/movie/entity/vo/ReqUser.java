package com.cjc.movie.entity.vo;

import lombok.Data;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/19 10:49
 */
@Data
public class ReqUser {
    private String name;
    private String password;
    private String code;     //验证码
}
