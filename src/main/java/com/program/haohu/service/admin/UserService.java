package com.program.haohu.service.admin;

import com.program.haohu.entity.admin.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * user用户service
 *
 * @author hh
 */
@Service
public interface UserService {
    public User findByUsername(String username);
    public int add(User user);
    public int edit(User user);
    public int editPassword(User user);
    public int delete(String ids);
    public List<User> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}
