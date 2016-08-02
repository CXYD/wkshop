package net.jeeshop.web.controller.manage.system;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.bean.SysUserBean;
import net.jeeshop.biz.system.enums.LogType;
import net.jeeshop.biz.system.model.SysRoleExample;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.biz.system.model.SysUserExample;
import net.jeeshop.biz.system.service.*;
import net.jeeshop.core.ManageContainer;
import net.jeeshop.core.exception.JShopException;
import net.jeeshop.core.util.MD5;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import net.jeeshop.web.util.RequestHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 后台用户管理
 *
 * @author huangf
 * @author dylan
 */
@Controller
@RequestMapping("/manage/user")
public class UserController extends ManageBaseController<SysUser, SysUserExample> {
    @Autowired
    private SMSService smsService;
    private static final String page_input = "/manage/system/login";
//    private static final String page_home = "/manage/system/home";
    private static final String page_home = "/manage/system/index";
    private static final String page_toList = "/manage/system/user/userList";
    private static final String page_toAdd = "/manage/system/user/editUser";
    private static final String page_toEdit = "/manage/system/user/editUser";
    private static final String page_toChangePwd = "/manage/system/user/toChangePwd";
    private static final String page_changePwd_result = "/manage/system/user/changePwd";
    private static final String page_show = "/manage/system/user/show";
    private static final String page_forgetPwd = "/manage/system/user/forgetPwd";

    private static final String page_initManageIndex = page_home;

    public UserController() {
        super.page_toEdit = page_toEdit;
        super.page_toList = page_toList;
        super.page_toAdd = page_toAdd;
    }

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService menuService;
    @Autowired
    private SystemLogService systemLogService;
//    @Resource
//    private ManageCache manageCache;

    @Override
    public BaseService<SysUser, SysUserExample> getService() {
        return userService;
    }

    @RequestMapping("loadData")
    @ResponseBody
    public PageBean<SysUserBean> loadData(SysUserBean queryParams, PageQueryBean pageQueryBean) {
        PageBean pager = userService.selectPageBean(queryParams, pageQueryBean);
        return pager;
    }

    /**
     * 登录页面
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        if (session.getAttribute(ManageContainer.manage_session_user_info) != null) {
            return "redirect:/manage/user/home";
        }
        return page_input;
    }

    /**
     * 后台登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, ModelMap model) throws Exception {
        model.addAttribute("last_login_username", username);
        String errorMsg;
        if (RequestHolder.getSession().getAttribute(ManageContainer.manage_session_user_info) != null) {
            return "redirect:/manage/user/home";
        }

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            model.addAttribute("errorMsg", "账户和密码不能为空!");
            return page_input;
        }
        logger.info("用户登录请求:{}", username);
        SysUser u = userService.selectByUsernamePassowrd(username, password);
        logger.debug("登陆用户信息：{}", u);
        if (u == null) {
            errorMsg = "登陆失败，账户或密码错误！";
            logger.error("登陆失败，账户或密码错误,{}", username);
            model.addAttribute("errorMsg", errorMsg);
            return page_input;
        } else if (u.getIsValid() != null && u.getIsValid() == false) {
            errorMsg = "帐号审核中，请联系管理员!";
            logger.error("帐号已被禁用，请联系管理员,{}", u.getUsername());
            model.addAttribute("errorMsg", errorMsg);
            return page_input;
        }
        LoginUserHolder.setLoginUser(u);
        //用户可访问的菜单写入session
        LoginUserHolder.setLoginUserMenus(menuService.loadMenus(u));
        String accountflag = "0";
        try {
            systemLogService.newLog("login", "login", LogType.login);
            //判断是不是超级管理员
            if (LoginUserHolder.getLoginUser() == null) {
                return "redirect:/manage/user/login";
            }
            /*if (LoginUserHolder.getLoginUser().getKhid() == null || LoginUserHolder.getLoginUser().getKhid().equals("")) {
                    accountflag="1";
            }
            request.setAttribute("username",u.getUsername());
            request.setAttribute("accountflag",accountflag);*/

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/manage/user/home";
    }

    @RequestMapping("home")
    public String home() {
        String accountflag = "0";
        if (LoginUserHolder.getLoginUser() == null) {
            return "redirect:/manage/user/login";
        }
        logger.debug("客户编号："+LoginUserHolder.getLoginUser().getKhid());
        if (LoginUserHolder.getLoginUser().getKhid() == null || LoginUserHolder.getLoginUser().getKhid().equals("")) {
            accountflag="1";
        }
        request.setAttribute("username",LoginUserHolder.getLoginUser().getUsername());
        request.setAttribute("accountflag",accountflag);
        return page_home;
    }


    /**
     * 添加用户
     */
    @Override
    @RequestMapping("insert")
    public String insert(@ModelAttribute("e") SysUser user, RedirectAttributes flushAttrs) {
        return save0(user, flushAttrs);
    }

    /**
     * 修改用户信息
     */
    @Override
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("e") SysUser user, RedirectAttributes flushAttrs) {
        return save0(user, flushAttrs);
    }

    private String save0(SysUser e, RedirectAttributes flushAttrs) {
        logger.info("save user..." + e.getId() + "," + e.getUsername());

        if (e.getId() == null) {//添加
            if (StringUtils.isBlank(e.getPassword())) {
                flushAttrs.addFlashAttribute("errorMsg", "输入的密码不符合要求！");
                return "redirect:toEdit?id=" + e.getId();
            }
            userService.addUser(e);
        } else {//修改
            //当前登录用户是admin，才能修改admin的信息，其他用户修改admin信息都属于非法操作。
            SysUser currentUser = LoginUserHolder.getLoginUser();
            if (!currentUser.getUsername().equals("admin") && e.getUsername().equals("admin")) {
                logger.warn("非admin用户正在尝试修改admin用户信息，currentUser:{}", currentUser.getUsername());
                flushAttrs.addFlashAttribute("errorMsg", "非法操作！");
                return "redirect:toEdit?id=" + e.getId();
            }
            userService.updateUser(e);
        }
        flushAttrs.addFlashAttribute("message", "操作成功!");
        return "redirect:back";
    }

    /**
     * 注销登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("logout")
    public String logout() throws Exception {
        HttpSession session = RequestHolder.getSession();
        if (session != null) {
            SysUser u = LoginUserHolder.getLoginUser();
            if (u != null && StringUtils.isNotBlank(u.getUsername())) {
                systemLogService.newLog("logout", "logout", LogType.logout);
            }
            LoginUserHolder.invalidateManageSession();
        }
        return "redirect:login";
    }

    /**
     * ajax验证输入的字符的唯一性
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("unique")
    @ResponseBody
    public String unique(@ModelAttribute("e") SysUser e, HttpServletResponse response) throws IOException {
        logger.debug("验证输入的字符的唯一性:" + e);
        response.setCharacterEncoding("utf-8");
        if (StringUtils.isNotBlank(e.getNickname())) {//验证昵称是否被占用
            logger.debug("验证昵称是否被占用:" + e.getNickname());
            SysUser user = userService.selectByNickName(e.getNickname());

            if (user == null) {
                //数据库中不存在此编码
                return "{\"ok\":\"昵称可以使用!\"}";
            } else {
                if (e.getId() != null && e.getId().equals(user.getId())) {
                    //update操作，又是根据自己的编码来查询的，所以当然可以使用啦
                    return "{\"ok\":\"昵称可以使用!\"}";
                } else {
                    //当前为insert操作，但是编码已经存在，则只可能是别的记录的编码
                    return "{\"error\":\"昵称已经存在!\"}";
                }
            }
        } else if (StringUtils.isNotBlank(e.getUsername())) {//验证用户名是否被占用
            logger.debug("验证账号是否被占用, username:" + e.getUsername());
            SysUser user = userService.selectByNickName(e.getUsername());
            if (user == null) {
                //数据库中不存在此编码
                return "{\"ok\":\"账号可以使用!\"}";
            } else {
                if (e.getId() != null && e.getId().equals(user.getId())) {
                    //update操作，又是根据自己的编码来查询的，所以当然可以使用啦
                    return "{\"ok\":\"账号可以使用!\"}";
                } else {
                    //当前为insert操作，但是编码已经存在，则只可能是别的记录的编码
                    return "{\"error\":\"账号已经被占用!\"}";
                }
            }
        }
        return null;
    }
    @RequestMapping("toForgetPwd")
    public String toChangePwd(HttpServletRequest request) {
        return page_forgetPwd;
    }
    /**
     * 转到修改密码页面
     *
     * @return
     */
    @RequestMapping("toChangePwd")
    public String toChangePwd(String username,HttpServletRequest request) {
        request.setAttribute("username",username);
        return page_toChangePwd;
    }
    @RequestMapping("changePwd")
    public String changePwd() {
        return page_changePwd_result;
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "updateChangePwd", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateChangePwd(@RequestParam("password")String password,@RequestParam("username")String username) {
        try {
            SysUser sysUser = userService.selectByUsername(username);
            if(sysUser!=null){
               userService.updatePassword(sysUser.getId(),password);
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    public String toEdit(@ModelAttribute("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("e", userService.selectUserBeanById(id));
        modelMap.addAttribute("roleList", roleService.selectByExample(new SysRoleExample()));
        return page_toEdit;
    }

    @Override
    protected void beforeToAdd(SysUser e, ModelMap modelMap) {
        modelMap.addAttribute("roleList", roleService.selectByExample(new SysRoleExample()));
    }

    /**
     * 查看管理人员信息
     *
     * @return
     */
    @RequestMapping("show")
    public String show(String account, ModelMap model) {
        if (StringUtils.isBlank(account)) {
            throw new JShopException("非法请求！");
        }
        SysUser user = userService.selectByUsername(account.trim());
        if (user == null) {
            throw new JShopException("非法请求！");
        }
        model.addAttribute("e", user);
        return page_show;
    }

    /**
     * 用户修改密码--验证旧密码是否正确
     *
     * @return
     */
    @RequestMapping("checkOldPassword")
    @ResponseBody
    public String checkOldPassword(@ModelAttribute("e") SysUser e) throws Exception {
        logger.error("checkOldPassword.." + e.getPassword());
        if (StringUtils.isBlank(e.getPassword())) {
            return "{\"error\":\"旧密码不能为空!\"}";
        } else {
            //检查旧密码输入的是否正确
            SysUser currentUser = LoginUserHolder.getLoginUser();
            String oldPass = MD5.md5(e.getPassword());
            if (currentUser.getPassword().equals(oldPass)) {
                return "{\"ok\":\"旧密码输入正确!\"}";
            } else {
                return "{\"error\":\"旧密码输入错误!\"}";
            }
        }
    }

    /**
     * 加载后台首页数据
     *
     * @return
     */
    @RequestMapping("initManageIndex")
    public String initManageIndex() {
        //店主每次登陆后台都需要加载综合统计数据？！还是说每次都触发加载，但是到底加载不加载具体看系统的加载策略？！
//        manageCache.loadOrdersReport();
        return page_initManageIndex;
    }

    @Override
    public String deletes(Long[] ids, RedirectAttributes flushAttrs) {
        throw new RuntimeException("not support");
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "/manage/system/user/register";
    }

    @RequestMapping(value = "reister", method = RequestMethod.POST)
    public Object register(SysUser user) {

        userService.addUser(user);
        return "ok";
    }

    @RequestMapping("toRegister")
    public String toRegister() {
        return "/manage/system/register";
    }

    @RequestMapping("registerUser")
    @ResponseBody
    public int  registerUser(@ModelAttribute("e") SysUser e) {
        try {
            e.setCreateTime(new Date());
            int i = userService.addUser(e);
            if(i>0 && StringUtils.isNotBlank(e.getUsername())){;
                smsService.send(e.getUsername(), "43699", "43699", "", "");
            }
            return i;
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }
    @RequestMapping("checkUser")
    @ResponseBody
    public boolean  checkUser(@ModelAttribute("e") SysUser e) {
        try {
            if (StringUtils.isNotBlank(e.getUsername())) {//验证昵称是否被占用
                logger.debug("验证账号是否被占用:" + e.getUsername());
                SysUser user = userService.getUserByName(e);
                if(user!=null){
                    return true;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
        return false;
    }
    /**
     * 发送短信
     * @param phone
     * @param tpl
     * @param code1
     * @param code2
     * @param code3
     * @return
     */
    @RequestMapping("/sendUserNum")
    @ResponseBody
    public Object send(String phone,String tpl,String code1,String code2,String code3){
        String code="";
        for (int i = 0; i < 4; i++) {
            code = code+(int)(Math.random() * 9);
        }
        HttpSession session = RequestHolder.getSession();
        System.out.println("验证码："+code);
        code1=code;
        session.setAttribute("code",code);
        session.setMaxInactiveInterval(120);
        return  smsService.send(phone,tpl,code1,code2,code3);
    }

    @RequestMapping("/checkUserNum")
    @ResponseBody
    public String checkNum(String num){
        HttpSession session = RequestHolder.getSession();
        String code=(String) session.getAttribute("code");
        if(code==null){
            return "验证码已超时";
        }
        logger.debug("code值：", code);
        if(!num.equals(code)){
            return "验证码错误";
        }
        return "验证码正确";
    }


}