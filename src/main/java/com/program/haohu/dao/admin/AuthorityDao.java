package com.program.haohu.dao.admin;

import com.program.haohu.entity.admin.Authority;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限实现类dao
 *
 * @author hh
 */
@Repository
public interface AuthorityDao {
    public int add(Authority authority);
    public int deleteByRoleId(Long roleId);
    public List<Authority> findListByRoleId(Long roleId);
}
