package com.program.haohu.controller.home;

import com.program.haohu.business.service.HisService;
import com.program.haohu.business.service.LoginService;
import com.program.haohu.controller.AjaxResult;
import com.program.haohu.controller.BaseController;
import com.program.haohu.entity.admin.User;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @ClassName:
 * @Description: 登录
 * @author:
 * @date: 2020年04月24日 22:58
 * @Copyright:
 */
@RequestMapping("/login")
@Controller
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private HisService hisService;

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(HttpSession session, String username, String password, String cpacha,Integer _newsId_){
        try {
            Object loginCpacha = session.getAttribute("loginCpacha");
            if (StringUtils.isBlank(cpacha)||loginCpacha==null||!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
                return AjaxResult.error("验证码错误！");
            }
            User user=loginService.login(username,password);
            user.setPassword(null);
            session.setAttribute("_login_user_",user);
            session.setAttribute("_login_user_json_", JSONObject.fromObject(user).toString());
            hisService.loginHisInit(session);
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        AjaxResult success = AjaxResult.success("登录成功！");
        if (_newsId_!=null&&_newsId_!=0){
            success.put("_newsId_",_newsId_);
        }
        return success;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("_login_user_");
        session.removeAttribute("_login_user_json_");
        return new ModelAndView("redirect:/");
    }
}
