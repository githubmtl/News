package com.program.haohu.controller.admin;

import com.program.haohu.entity.admin.Authority;
import com.program.haohu.entity.admin.Menu;
import com.program.haohu.entity.admin.Role;
import com.program.haohu.page.admin.Page;
import com.program.haohu.service.admin.AuthorityService;
import com.program.haohu.service.admin.MenuService;
import com.program.haohu.service.admin.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色role控制器
 *
 * @author hh
 */
@RequestMapping("/admin/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private MenuService menuService;

    /**
     * 角色管理列表页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        // 指定视图名称，直接找到指定的jsp页面
        model.setViewName("/role/list");
        return model;
    }

    /**
     * 获取角色列表
     *
     * @param page
     * @param name
     * @return
     */
    // list对应于role/list.jsp中$('#data-datagrid').datagrid({
    //        url:'list',中的list
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody // 返回json字符串，返回值对象（比如：Map）直接转换为json字符串
    public Map<String, Object> getList(Page page,
            @RequestParam(name = "name", required = false, defaultValue = "") String name
            ) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name", name);
        queryMap.put("offset", page.getOffset()); // offset对应RoleMapper中的limit #{offset},#{pageSize}中的offset
        queryMap.put("pageSize", page.getRows()); // pageSize对应RoleMapper中的limit #{offset},#{pageSize}中的pageSize
        // ret.put中的"rows"和"total" 是easyui框架中定义好的字段
        ret.put("rows", roleService.findList(queryMap));
        ret.put("total", roleService.getTotal(queryMap));
        return ret;
    }

    /**
     * 角色添加
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Role role) {
        Map<String, String> ret = new HashMap<String, String>();
        if (role == null) {
            // ret.put中的"type"和"msg" 是easyui框架中定义的字段
            ret.put("type", "error");
            ret.put("msg", "请填写正确的角色信息！");
            return ret;
        }
        if (StringUtils.isEmpty(role.getName())) {
            ret.put("type", "error");
            ret.put("msg", "请填写角色名称！");
            return ret;
        }
        if (roleService.add(role) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "角色添加失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "角色添加成功！");
        return ret;
    }

    /**
     * 角色修改
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Role role) {
        Map<String, String> ret = new HashMap<String, String>();
        if (role == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写正确的角色信息！");
            return ret;
        }
        if (StringUtils.isEmpty(role.getName())) {
            ret.put("type", "error");
            ret.put("msg", "请填写角色名称！");
            return ret;
        }
        if (roleService.edit(role) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "角色修改失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "角色修改成功！");
        return ret;
    }

    /**
     * 删除角色信息
     * @param id
     * @author hh
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(Long id) {
        Map<String, String> ret = new HashMap<String, String>();
        if (id == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择要删除的角色！");
            return ret;
        }
        try {
            if (roleService.delete(id) <= 0) {
                ret.put("type", "error");
                ret.put("msg", "删除失败，请联系管理员！");
                return ret;
            }
        } catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "该角色下存在权限或者用户信息，不能删除！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "角色删除成功！");
        return ret;
    }

    /**
     * 获取所有的菜单信息
     *
     * @author hh
     */
    @RequestMapping(value = "/get_all_menu", method = RequestMethod.POST)
    @ResponseBody // 返回json字符串
    public List<Menu> getAllMenu() {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 99999);
        return menuService.findList(queryMap);
    }

    /**
     * 添加权限
     * @param ids
     * @param roleId
     * @author hh
     */
    @RequestMapping(value = "/add_authority", method = RequestMethod.POST)
    @ResponseBody // 返回json字符串
    public Map<String, String> addAuthority(
            @RequestParam(name = "ids",required = true) String ids,
            @RequestParam(name = "roleId",required = true) Long roleId
    ) {
        Map<String, String> ret = new HashMap<String, String>();
        if (StringUtils.isEmpty(ids)) {
            ret.put("type", "error");
            ret.put("msg", "请选择相应的权限！");
            return ret;
        }
        if (roleId == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择相应的角色！");
            return ret;
        }
        if (ids.contains(",")) {
            ids = ids.substring(0, ids.length()-1);
        }
        String[] idArr = ids.split(",");
        if (idArr.length > 0) {
            authorityService.deleteByRoleId(roleId);

        }
        for (String id:idArr) {
            Authority authority = new Authority();
            authority.setMenuId(Long.valueOf(id));
            authority.setRoleId(roleId);
            authorityService.add(authority);
        }
        ret.put("type", "true");
        ret.put("msg", "权限编辑成功！");
        return ret;
    }

    /**
     * 获取某个角色的所有权限，value = "/get_role_authority" 语句中get_role_authority对应role/list.jsp页面中的url
     * @param roleId
     * @author hh
     */
    @RequestMapping(value = "/get_role_authority", method = RequestMethod.POST)
    @ResponseBody // 返回json字符串
    public List<Authority> getRoleAuthority(
            // @RequestParam(name = "roleId",required = true) 中的name = "roleId"
            // 对应前台role/list.jsp页面中ajax请求发过来的$.ajax({ data:{roleId:item.id} 中的roleId
            @RequestParam(name = "roleId",required = true) Long roleId
    ) {
        return authorityService.findListByRoleId(roleId);
    }

}
