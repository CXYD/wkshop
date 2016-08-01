package net.jeeshop.web.controller.manage.shop;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.shop.model.BuySet;
import net.jeeshop.biz.shop.service.ShopManagerService;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.biz.system.model.SysUserExample;
import net.jeeshop.core.util.MD5;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import net.jeeshop.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hyg
 * Date: 16/5/30
 * Time: 17:41
 */
@Controller
@RequestMapping("manage/shopManage")
public class ShopManagerController extends ManageBaseController {

    @Autowired
    ShopManagerService shopManagerService;



    @Override
    public BaseService getService() {
        return null;
    }

    ShopManagerController(){
        super.page_toList = "/manage/shop/shopManager/managerList";
        super.page_toEdit = "/manage/shop/shopManager/editManager";
        super.page_toAdd = "/manage/shop/shopManager/addManager";
    }


    @RequestMapping("addShopManager")
    public String addManager(){
        return page_toAdd;
    }

    /**
     * 商城管理员
     * @param map
     * @return
     */
    @RequestMapping("listShopManager")
    public String listManager(Map<String, Object> map){
        if (LoginUserHolder.getLoginUser() == null) {
            return "redirect:/manage/user/login";
        }
        String khid=LoginUserHolder.getLoginUser().getKhid();
        if(khid==null||khid.equals("")){
            return "/manage/shop/shopInfo/addShop";
        }
        Integer userCount = shopManagerService.getUserCount(khid);
        Page<SysUser> page = new Page<SysUser>("1", userCount, "10");
        page.setKhid(khid);
        List<SysUser> list = shopManagerService.getUserList(page);
        page.setList(list);
        BuySet buySet = new BuySet("ONO", "OFF", "OFF", "0ON", "LL0");
        logger.debug("page:{}",page);
        logger.debug("buySet:{}",list);
        map.put("userList",list);
        map.put("buySet",buySet);
        map.put("page","6");
        return page_toList;
    }


    /**
     * 初始化表格数据
     * @param queryParams
     * @param pageQueryBean
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<SysUser> loadData(SysUser queryParams, PageQueryBean pageQueryBean) {
        logger.debug("加载数据....");
        if (LoginUserHolder.getLoginUser() == null) {
            return null;
        }
        SysUser loginUser = LoginUserHolder.getLoginUser();
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andKhidEqualTo(loginUser.getKhid());
        sysUserExample.setOrderByClause(getOrderColumnData(request));
        PageBean pager = shopManagerService.selectPageList(sysUserExample,pageQueryBean);
        return pager;
    }

    @RequestMapping("addManage")
    @ResponseBody
    public String addManage(SysUser sysUser){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        Long roleId = loginUser.getRoleId();
        if(roleId!=6){
            return "权限不够";
        }
        if(shopManagerService.getManagerByName(sysUser)!=null){
            return "账号已存在";
        }
        sysUser.setCreateAccount(loginUser.getUsername());
        sysUser.setCreateTime(new Date());
        sysUser.setKhid(loginUser.getKhid());
        sysUser.setRoleId(Long.parseLong("9"));
        String md5pass = MD5.md5(sysUser.getPassword());
        sysUser.setPassword(md5pass);
        shopManagerService.addManager(sysUser);
        return "成功";
    }
    @RequestMapping("getManage")
    @ResponseBody
    public String getManage(SysUser sysUser){
        if(shopManagerService.getManagerByName(sysUser)!=null){
            return "账号已存在";
        }
        return "账号可以使用";
    }

    @RequestMapping("editShopManager")
    public String editManager(String username,Map<String, Object> map){
        SysUser user = shopManagerService.getUser(username);
        map.put("user",user);
        return page_toEdit;
    }

    @RequestMapping("getManageNoId")
    @ResponseBody
    public String getManageNoId(SysUser sysUser){
        if(shopManagerService.getManagerNoID(sysUser)!=null){
            return "账号已存在";
        }
        return "账号可以使用";
    }
    @RequestMapping("editManage")
    @ResponseBody
    public String editManage(SysUser sysUser){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        Long roleId = loginUser.getRoleId();
        if(roleId!=6){
            return "您的权限不够";
        }
        String md5pass = MD5.md5(sysUser.getPassword());
        sysUser.setPassword(md5pass);
        shopManagerService.upManager(sysUser);
        return "修改成功";
    }
    @RequestMapping("delManage")
    @ResponseBody
    public String delManage(SysUser sysUser){
        shopManagerService.delManager(sysUser);
        return "成功";
    }


}
