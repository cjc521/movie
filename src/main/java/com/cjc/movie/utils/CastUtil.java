package com.cjc.movie.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/16 21:57
 */
public class CastUtil {
    public static <T> T mapToEntity(Map<String, Object> paramMap, Class<T> cls) {
        return JSONObject.parseObject(JSONObject.toJSONString(paramMap), cls);
    }
}
