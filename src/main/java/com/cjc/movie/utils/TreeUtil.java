package com.cjc.movie.utils;

import com.cjc.movie.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/12 16:53
 */

public class TreeUtil {
        public static List<Menu> toTree(List<Menu> menuList, Integer pid) {
            List<Menu> retList = new ArrayList<Menu>();
            for (Menu parent : menuList) {
                if (pid.equals(parent.getPid())) {
                    retList.add(findChildren(parent,menuList));
                }
            }
            return retList;
        }
        private static Menu findChildren(Menu parent, List<Menu> menuList) {
            for (Menu child : menuList) {
                if (parent.getId().equals(child.getPid())) {
                    if (parent.getChild() == null) {
                        parent.setChild(new ArrayList<>());
                    }
                    parent.getChild().add(findChildren(child,menuList));
                }
            }
            return parent;
        }
    
}
