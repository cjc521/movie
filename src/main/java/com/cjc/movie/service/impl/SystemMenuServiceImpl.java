package com.cjc.movie.service.impl;

import com.cjc.movie.entity.Menu;
import com.cjc.movie.entity.SystemMenu;
import com.cjc.movie.mapper.SystemMenuMapper;
import com.cjc.movie.service.SystemMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjc.movie.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements SystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;
    @Override
    public Map<String, Object> menu() {
        Map<String, Object> map = new HashMap<>(16);
        Map<String,Object> home = new HashMap<>(16);
        Map<String,Object> logo = new HashMap<>(16);
        List<SystemMenu> menuList =systemMenuMapper.selectList(null);
        List<Menu> menuInfo = new ArrayList<>();
        for (SystemMenu e : menuList) {
            Menu menu = new Menu();
            menu.setId(e.getId());
            menu.setPid(e.getPid());
            menu.setHref(e.getHref());
            menu.setTitle(e.getTitle());
            menu.setIcon(e.getIcon());
            menu.setTarget(e.getTarget());
            menuInfo.add(menu);
        }
        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0));
        home.put("title","首页");
        home.put("href","admin-index.html");//控制器路由
        logo.put("title","后台管理系统");
        logo.put("href","admin-main.html");
        logo.put("image","/images/logo.png");//logo标签
        map.put("homeInfo", home);
        map.put("logoInfo", logo);
        return map;
    }
}
