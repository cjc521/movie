package com.cjc.movie.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/13 20:29
 */
@Data
public class MovieVO {
    private Integer id;

    private String name;

    private String poster;

    private String film;

    private LocalDate releaseTime;

    private String introduce;

    private String area;

    private String type;

    private Integer downCount;

    private Double score;

    private LocalDate uploadTime;
}
