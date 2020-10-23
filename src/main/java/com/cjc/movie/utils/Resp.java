package com.cjc.movie.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/15 11:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resp {
    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

    public Resp(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }
    public Resp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
