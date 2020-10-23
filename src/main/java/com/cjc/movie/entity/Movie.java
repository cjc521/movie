package com.cjc.movie.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String poster;

    private String film;

    @TableField("release_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "UTC")
    private LocalDate release_time;

    private String introduce;

    @TableField("areaId")
    private Integer areaId;

    @TableField("typeId")
    private Integer typeId;

    @TableField("down")
    private Integer down;

    private Double score;

    @TableField(fill = FieldFill.INSERT,value = "upload_time")
    private LocalDateTime uploadTime;

    @TableLogic
    @TableField(select = false,value = "state")
    private Integer state;  // 状态码 0:逻辑未删除 1：逻辑删除


}
