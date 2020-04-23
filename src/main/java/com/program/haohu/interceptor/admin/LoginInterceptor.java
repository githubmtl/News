package com.program.haohu.interceptor.admin;

import com.program.haohu.entity.admin.Menu;
import com.program.haohu.util.MenuUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台登录拦截器
 *
 * @author hh
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestURI = request.getRequestURI();
        Object admin = request.getSession().getAttribute("admin");
        if (admin == null) {
            // 表示未登录或者登录失效
            System.out.println("链接" + requestURI + "进入拦截器！");
            String header = request.getHeader("X-Requested-With");
            // 判断是否是ajax请求
            if ("XMLHttpRequest".equals(header)) {
                // 表示是ajax请求
                Map<String, String> ret = new HashMap<String, String>();
                ret.put("type", "error");
                ret.put("msg", "登录会话超时或还未登录，请重新登录！");
                response.getWriter().write(JSONObject.fromObject(ret).toString());
                return false;
            }
            // 表示是普通链接跳转，直接重定向到登录页面
            response.sendRedirect(request.getServletContext().getContextPath() + "/system/login");
            return false;
        }
        // 获取菜单id
        String mid = request.getParameter("_mid");
        if (!StringUtils.isEmpty(mid)) {
            List<Menu> allThirdMenu = MenuUtil.getAllThirdMenu((List<Menu>) request.getSession().getAttribute("userMenus"), Long.valueOf(mid));
            request.setAttribute("thirdMenuList", allThirdMenu);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
