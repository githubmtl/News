package com.program.haohu.business.service;

import javax.servlet.http.HttpSession;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月25日 13:43
 * @Copyright:
 */
public interface HisService {
    public void addHis(Integer newsId, HttpSession session);
    public void loginHisInit(HttpSession httpSession);
}
