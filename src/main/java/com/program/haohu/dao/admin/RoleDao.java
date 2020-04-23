package com.program.haohu.dao.admin;

import com.program.haohu.entity.admin.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色role dao
 *
 * @author hh
 */
@Repository
public interface RoleDao {
    public int add(Role role);
    public int edit(Role role);
    public int delete(Long id);
    public List<Role> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public Role find(Long id);
}
