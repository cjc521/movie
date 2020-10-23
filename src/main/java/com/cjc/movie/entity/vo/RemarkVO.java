package com.cjc.movie.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/23 8:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemarkVO {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userPicture;
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

    private Integer unMark;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "UTC")
    private LocalDateTime createTime;



}
