package net.jeeshop.web.controller.manage.system;

import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.bean.MenuItem;
import net.jeeshop.biz.system.enums.ResourceType;
import net.jeeshop.biz.system.model.SysPrivilege;
import net.jeeshop.biz.system.model.SysPrivilegeExample;
import net.jeeshop.biz.system.model.SysResource;
import net.jeeshop.biz.system.model.SysResourceExample;
import net.jeeshop.biz.system.service.ResourceService;
import net.jeeshop.biz.system.service.PrivilegeService;
import net.jeeshop.core.util.EnumUtils;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by carlosxiao on 29/12/2015.
 */

@Controller
@RequestMapping("/manage/menu/")
public class SystemResourceController extends ManageBaseController<SysResource, SysResourceExample> {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public BaseService<SysResource, SysResourceExample> getService() {
        return resourceService;
    }

    private static final String PAGE_TOLIST = "/manage/system/menu/menuList";
    private static final String PAGE_TOEDIT = "/manage/system/menu/editMenu";
    private static final String PAGE_ADDORUPDATE = "/manage/system/menu/addOrUpdate";

    SystemResourceController() {
        super.page_toList = PAGE_TOLIST ;
        super.page_toEdit = PAGE_TOEDIT ;
        super.page_toAdd = PAGE_TOEDIT ;
    }

    @RequestMapping(value = "getMenusByPid" , method = RequestMethod.GET )
    @ResponseBody
    public String getMenusByPid(@RequestParam(required = false , defaultValue = "0") String pid ,
                                @RequestParam(required = false) String id) throws Exception 
    {
        // 加载全部的菜单
        List<MenuItem> menus = resourceService.loadMenus(null, Long.parseLong(pid), "#");
        if(StringUtils.isNotBlank(id)) {
            // 加载指定角色的权限
            SysPrivilegeExample privilegeExample = new SysPrivilegeExample();
            SysPrivilegeExample.Criteria criteria = privilegeExample.createCriteria();
            criteria.andRoleIdEqualTo(Long.parseLong(id));
            List<SysPrivilege> rolePs = privilegeService.selectByExample(privilegeExample);
            // 拿角色拥有的菜单和全部的菜单做比对，进行勾选
            for (int i = 0; i < rolePs.size(); i++) {
                SysPrivilege sysPrivilege = rolePs.get(i);
                checkRoleMenu(sysPrivilege , menus);
            }
        }
        return writeMenus(menus);
    }

    /**
     * 角色权限和资源菜单进行对比，使checkbox选中
     * @param p
     * @param menus
     */
    private void checkRoleMenu(SysPrivilege p, List<MenuItem> menus){
        for (int j = 0; j < menus.size(); j++) {
            MenuItem menu = menus.get(j);
            if (p.getResourceId().equals(menu.getId())) {
                menu.setChecked(true);
                return;
            }else{
                if(menu.getChildren()!=null && menu.getChildren().size()>0) {
                    checkRoleMenu(p, menu.getChildren());
                }
            }
        }
    }

    //输出菜单到页面
    private String writeMenus(List<MenuItem> root) throws IOException {
        JSONArray json = JSONArray.fromObject(root);
        String jsonStr = json.toString();
        try {
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * 点击菜单树右侧显示菜单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "showcase" , method = RequestMethod.GET)
    public String showcase(@RequestParam String id , ModelMap model) {
        SysResource sysMenu = resourceService.selectById(Long.parseLong(id));
        model.addAttribute("e", sysMenu);
        return PAGE_ADDORUPDATE;
    }

    @RequestMapping(value = "addOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String addOrUpdate(HttpServletRequest request) throws Exception
    {
        //选中菜单的信息
        //String updateP = request.getParameter("updateP");
        String id = request.getParameter("id");
        String pid = request.getParameter("pid");
        String name = request.getParameter("name");
        String orderNum = request.getParameter("orderNum");
        String type = request.getParameter("type");

        //要添加的子菜单
        String url = request.getParameter("url");
        String n_name = request.getParameter("n_name");
        String n_url = request.getParameter("n_url");
        String parentOrChild = request.getParameter("parentOrChild");
        String n_orderNum = request.getParameter("n_orderNum");
        String n_type = request.getParameter("n_type");

        SysResource itemMenu = null;
        if(n_name!=null && !n_name.trim().equals("")){
            itemMenu = new SysResource();
            //添加子菜单
            if(parentOrChild.equals("0")){//顶级模块
                itemMenu.setParentId(0l);
                itemMenu.setResourceType(ResourceType.module);
            } else if(parentOrChild.equals("1")){//顶级页面
                itemMenu.setParentId(0l);
                itemMenu.setResourceType(ResourceType.page);
            } else if(parentOrChild.equals("2")){//子模块
                itemMenu.setParentId(Long.parseLong(id));
                itemMenu.setResourceType(ResourceType.module);
            } else if(parentOrChild.equals("3")){//子页面
                itemMenu.setParentId(Long.parseLong(id));
                itemMenu.setResourceType(ResourceType.page);
            } else if(parentOrChild.equals("4")){   //功能
                itemMenu.setParentId(Long.parseLong(id));
                itemMenu.setResourceType(ResourceType.button);
            } else {
                throw new IllegalAccessException("添加菜单异常。");
            }
            itemMenu.setResourceName(n_name);
            itemMenu.setResourceValue(n_url);
            itemMenu.setOrdinal(Integer.valueOf(n_orderNum));
//            itemMenu.setResourceType(n_type);
        }
        //修改父菜单
        SysResource m = new SysResource();
        m.setId(Long.parseLong(id));
        m.setResourceName(name);
        m.setResourceValue(url);
        m.setParentId(Long.parseLong(pid));
        m.setOrdinal(Integer.valueOf(orderNum));
        m.setResourceType(EnumUtils.parseEnum(ResourceType.class, type));

        resourceService.addOrUpdate(m, itemMenu);

        return "ok";
    }

}
