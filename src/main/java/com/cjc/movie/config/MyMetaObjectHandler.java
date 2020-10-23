package com.cjc.movie.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/15 11:47
 */
@Component
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("creatTime")){
            setFieldValByName("creatTime", LocalDateTime.now(),metaObject);
        }
        if (metaObject.hasSetter("uploadTime")){
            setFieldValByName("uploadTime", LocalDateTime.now(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("creatTime")){
            Object uploadTime = getFieldValByName("uploadTime", metaObject);
            if(Objects.nonNull(uploadTime)){
                setFieldValByName("uploadTime", uploadTime,metaObject);
            }else {
                setFieldValByName("uploadTime", LocalDateTime.now(),metaObject);
            }
        }
    }
}
