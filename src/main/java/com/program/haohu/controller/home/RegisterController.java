package com.program.haohu.controller.home;


import com.program.haohu.controller.AjaxResult;
import com.program.haohu.controller.BaseController;
import com.program.haohu.business.service.RegisterService;
import com.program.haohu.entity.admin.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("/register")
@Controller
public class RegisterController extends BaseController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/reg")
    @ResponseBody
    public AjaxResult register(HttpSession session, User user, String cpacha){
        try {
            Object loginCpacha = session.getAttribute("loginCpacha");
            if (StringUtils.isBlank(cpacha)||loginCpacha==null||!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
                return AjaxResult.error("验证码错误！");
            }
            registerService.register(user);
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }
}
