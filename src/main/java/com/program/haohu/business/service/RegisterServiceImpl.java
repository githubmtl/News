package com.program.haohu.business.service;

import com.program.haohu.dao.admin.UserDao;
import com.program.haohu.entity.admin.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月24日 21:44
 * @Copyright:
 */
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserDao userDao;
    @Override
    public void register(User user) {
        if (StringUtils.isBlank(user.getUsername())){
            throw new IllegalArgumentException("用户名不能为空！");
        }
        if (StringUtils.isBlank(user.getPassword())){
            throw new IllegalArgumentException("密码不能为空！");
        }
        User old = userDao.findByUsername(user.getUsername().trim());
        if (old!=null){
            throw new IllegalArgumentException("用户名["+old.getUsername()+"]已存在！");
        }
        user.setRoleId(6l);
        userDao.add(user);
    }
}
