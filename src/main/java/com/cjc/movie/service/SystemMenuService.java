package com.cjc.movie.service;

import com.cjc.movie.entity.SystemMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
public interface SystemMenuService extends IService<SystemMenu> {
    Map<String, Object> menu();
}
