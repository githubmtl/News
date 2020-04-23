package com.program.haohu.util;

import com.program.haohu.entity.admin.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于菜单操作的一些公用方法
 *
 * @author hh
 */
public class MenuUtil {
    /**
     * 从给定的菜单中返回所有顶级菜单
     * @param menuList
     * @return
     * @author hh
     */
    public static List<Menu> getAllTopMenu(List<Menu> menuList) {
        List<Menu> ret = new ArrayList<Menu>();
        for (Menu menu:menuList) {
            if (menu.getParentId() == 0) {
                ret.add(menu);
            }
        }
        return ret;
    }

    /**
     * 获取所有的二级菜单
     * @param menuList
     * @return
     * @author hh
     */
    public static List<Menu> getAllSecondMenu(List<Menu> menuList) {
        List<Menu> ret = new ArrayList<Menu>();
        List<Menu> allTopMenu = getAllTopMenu(menuList);
        for (Menu menu:menuList) {
            for (Menu topMenu:allTopMenu) {
                if (menu.getParentId().equals(topMenu.getId())) {
                    ret.add(menu);
                    break; // 结束for (Menu topMenu:allTopMenu)循环
                }
            }
        }
        return ret;
    }

    /**
     * 获取某个二级菜单下的按钮
     * @param menuList
     * @param secondMenuId
     * @return
     * @author hh
     */
    public static List<Menu> getAllThirdMenu(List<Menu> menuList, Long secondMenuId) {
        List<Menu> ret = new ArrayList<Menu>();
        for (Menu menu:menuList) {
            if (menu.getParentId().equals(secondMenuId)) {
                ret.add(menu);
            }
        }
        return ret;
    }
}
