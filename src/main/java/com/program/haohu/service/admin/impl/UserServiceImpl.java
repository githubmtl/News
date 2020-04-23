package com.program.haohu.service.admin.impl;

import com.program.haohu.dao.admin.UserDao;
import com.program.haohu.entity.admin.User;
import com.program.haohu.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * user用户serviceimpl
 *
 * @author hh
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public int edit(User user) {
        return userDao.edit(user);
    }

    @Override
    public int editPassword(User user) {
        return userDao.editPassword(user);
    }

    @Override
    public int delete(String ids) {
        return userDao.delete(ids);
    }

    @Override
    public List<User> findList(Map<String, Object> queryMap) {
        return userDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return userDao.getTotal(queryMap);
    }
}
