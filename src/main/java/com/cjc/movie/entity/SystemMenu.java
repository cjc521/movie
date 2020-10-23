package com.cjc.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * ID
     */
         @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 父ID
     */
         private Integer pid;

        /**
     * 名称
     */
         private String title;

        /**
     * 菜单图标
     */
         private String icon;

        /**
     * 链接
     */
         private String href;

        /**
     * 链接打开方式
     */
         private String target;

        /**
     * 菜单排序
     */
         private Integer sort;

        /**
     * 状态(0:禁用,1:启用)
     */
         private Boolean status;

        /**
     * 备注信息
     */
         private String remark;

        /**
     * 创建时间
     */
         private LocalDateTime createAt;

        /**
     * 更新时间
     */
         private LocalDateTime updateAt;

        /**
     * 删除时间
     */
         private LocalDateTime deleteAt;


}
