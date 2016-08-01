package net.jeeshop.web.controller.manage.system;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.bean.SysRoleBean;
import net.jeeshop.biz.system.bean.SysUserBean;
import net.jeeshop.biz.system.model.SysRole;
import net.jeeshop.biz.system.model.SysRoleExample;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.biz.system.service.RoleService;
import net.jeeshop.core.exception.NotThisMethod;
import net.jeeshop.core.util.BeanUtilsExt;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import net.jeeshop.web.util.RequestHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by carlosxiao on 28/12/2015.
 */

@Controller
@RequestMapping("/manage/role")
public class SystemRoleController extends ManageBaseController<SysRole, SysRoleExample> {

    @Autowired
    private RoleService roleService;

    @Override
    public BaseService<SysRole, SysRoleExample> getService() {
        return roleService;
    }

    SystemRoleController() {
        super.page_toList = "manage/system/role/roleList";
        super.page_toEdit = "manage/system/role/editRole";
        super.page_toAdd = "manage/system/role/editRole";
    }

    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<SysUserBean> loadData(SysRoleExample queryParams, PageQueryBean pageQueryBean) {
        PageBean pager = roleService.selectPageList(queryParams, pageQueryBean);
        return pager;
    }

    /**
     * ajax验证输入的字符的唯一性
     *
     * @return
     */
    @RequestMapping("unique")
    @ResponseBody
    public String unique(@RequestParam String roleName) throws IOException {
        //验证角色名称是否被占用
        if (StringUtils.isNotBlank(roleName)) {
            SysRoleExample roleExample = new SysRoleExample();
            SysRoleExample.Criteria criteria = roleExample.createCriteria();
            criteria.andRoleNameEqualTo(roleName);
            SysRole role = roleService.selectUniqueByExample(roleExample);
            if (role == null) {
                //数据库中不存在此编码
                return "{\"ok\":\"角色名称有效!\"}";
            } else {
                return "{\"error\":\"昵称已经存在!\"}";
            }
        }
        return null;
    }

    @Override
    @RequestMapping(value = "insert" , method = RequestMethod.POST)
    public String insert(@ModelAttribute("e") SysRole role, RedirectAttributes flushAttrs) {
        HttpServletRequest request = RequestHolder.getRequest();
        SysRoleBean roleBean = new SysRoleBean();
        BeanUtilsExt.copyProperties(roleBean, role);
        //TODO 可能有更好的方式来做
        roleBean.setPrivileges(request.getParameter("privileges"));
        return saveOrUpdateRole(roleBean, flushAttrs);
    }

    /**
     * 只能是admin才具有编辑其他用户权限的功能
     */
    @Override
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("e") SysRole role, RedirectAttributes flushAttrs) {
        SysUser user = LoginUserHolder.getLoginUser();
        if(!user.getUsername().equals("admin")){
            flushAttrs.addFlashAttribute("errorMsg", "非法操作！");
            return "redirect:toEdit?id=" + role.getId();
        }
        HttpServletRequest request = RequestHolder.getRequest();
        SysRoleBean roleBean = new SysRoleBean();
        BeanUtilsExt.copyProperties(roleBean, role);
        //TODO 可能有更好的方式来做
        roleBean.setPrivileges(request.getParameter("privileges"));
        return saveOrUpdateRole(roleBean, flushAttrs);
    }

    private String saveOrUpdateRole(SysRoleBean e , RedirectAttributes flushAttrs) {
        logger.debug("保存角色id{}",e.getId());
        logger.debug("保存角色{}",e);
        if (e.getId() == null) {
            //insert
            roleService.addRole(e);
        } else {
            //update
            roleService.updateRole(e);
        }
        flushAttrs.addFlashAttribute("message", "操作成功!");
        return "redirect:back";
    }

    @Override
    @RequestMapping(value = "deletes", method = RequestMethod.POST)
    public String deletes(Long [] ids , RedirectAttributes flushAttrs) {
        throw new NotThisMethod("");
    }


}
