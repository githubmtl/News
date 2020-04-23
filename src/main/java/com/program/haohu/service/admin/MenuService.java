package com.program.haohu.service.admin;

import com.program.haohu.entity.admin.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理service
 * 
 * @author hh
 */
@Service
public interface MenuService {
    // 方法名称add必须和MenuMapper中<insert id="add" parameterType="Menu">中的add保持一样
    public int add(Menu menu);
    public List<Menu> findList(Map<String, Object> queryMap);
    public List<Menu> findTopList();
    public int getTotal(Map<String, Object> queryMap);
    public int edit(Menu menu);
    public int delete(Long id);
    public List<Menu> findChildrenList(Long parentId);
    public List<Menu> findListByIds(String ids);
}
