package com.program.haohu.business.service;

import com.program.haohu.entity.admin.User;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月24日 23:01
 * @Copyright:
 */
public interface LoginService {
    public User login(String userName, String password);
}
