package com.program.haohu.business.service;

import com.program.haohu.dao.admin.UserDao;
import com.program.haohu.entity.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月24日 23:01
 * @Copyright:
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(String userName, String password) {
        User byUsername = userDao.findByUsername(userName);
        if (byUsername==null){
            throw new IllegalArgumentException("用户不存在或密码不正确！");
        }
        if (password==null||!password.equals(byUsername.getPassword())){
            throw new IllegalArgumentException("用户不存在或密码不正确！");
        }
        return byUsername;
    }
}
