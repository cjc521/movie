package com.cjc.movie.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Remark implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 标题
     */
         private String title;

        /**
     * 评论内容
     */
         private String item;

        /**
     * 点赞量
     */
         private Integer mark;

        /**
     * 拉黑
     */
         @TableField("unMark")
    private Integer unMark;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "UTC")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

        /**
     * 用户id
     */
         private Integer userId;

        /**
     * 电影id
     */
         private Integer movieId;

         @TableLogic
         @TableField(select = false,value = "state")
         private Integer state;


}
