package net.jeeshop.web.controller.front.member;

import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.member.service.MemberService;
import net.jeeshop.biz.system.bean.AreaItem;
import net.jeeshop.biz.system.service.AreaService;
import net.jeeshop.core.util.MD5;
import net.jeeshop.web.bean.ResultBean;
import net.jeeshop.web.controller.front.FrontBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dylan
 * @date 16/3/2 21:59
 * Email: dinguangx@163.com
 */
@Controller
@RequestMapping("/member")
public class HomeController extends FrontBaseController {
    @Autowired
    MemberService memberService;
    @Autowired
    AreaService areaService;

    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        Member currentMember = LoginUserHolder.getLoginMember();
        if (currentMember == null) {
            return page_toLoginRedirect;
        }
        Member member = memberService.selectById(currentMember.getId());
        modelMap.addAttribute("e", member);

        long provinceId = 0;
        List<AreaItem> provinces = areaService.loadAreasByPid(0l, false);
        modelMap.addAttribute("provinces", provinces);
        if (StringUtils.isNotBlank(member.getProvince())) {
            for (AreaItem item : provinces) {
                if (item.getAreaCode().equals(member.getProvince())) {
                    provinceId = item.getId();
                    break;
                }
            }
            modelMap.addAttribute("cities", areaService.loadAreasByPid(Long.valueOf(provinceId), false));
        } else {
            modelMap.addAttribute("cities", new ArrayList<Area>());
        }
        return "member/home";
    }

    /**
     * 更新个人信息
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String updateInfo(Member member, ModelMap modelMap) {
        Member currentMember = LoginUserHolder.getLoginMember();
        if (currentMember == null) {
            return page_toLoginRedirect;
        }
        member.setId(currentMember.getId());
        memberService.update(member);
        //TODO 更新session中存储的登录用户信息
        return "redirect:home";
    }


    @RequestMapping("changeEmail")
    public String changeEmail(ModelMap modelMap) {
        return "member/changeEmail";
    }

    @RequestMapping("/changePwd")
    public String changePwd(ModelMap modelMap) {
        return "member/changePwd";
    }

    @RequestMapping("checkPassword")
    @ResponseBody
    public String checkPassword(String password) {
        Member currentMember = LoginUserHolder.getLoginMember();
        if (currentMember == null) {
            return "用户未登录";
        }
        String encPassword = MD5.md5(password);
        if (!encPassword.equals(currentMember.getPassword())) {
            return "密码不正确!";
        }
        return "";
    }

    @RequestMapping(value = "doChangePwd", method = RequestMethod.POST)
    public String doChangePwd(String password, String newPassword, ModelMap modelMap) {
        Member currentMember = LoginUserHolder.getLoginMember();
        if (currentMember == null) {
            return page_toLoginRedirect;
        }
        ResultBean result = memberService.updatePassword(currentMember, password, newPassword);
        if (!result.isSuccess()) {
            modelMap.addAttribute("errorMsg", result.getMsg());
            return "redirect:changePwd";
        }
        return "redirect:changePwdSuccess";
    }

    /**
     * 更新密码成功
     *
     * @return
     */
    @RequestMapping("changePwdSuccess")
    public String changePwdSuccess() {
        Member currentMember = LoginUserHolder.getLoginMember();
        if (currentMember == null) {
            return page_toLoginRedirect;
        }
        return "member/changePwdSuccess";
    }

    @RequestMapping("orders")
    public String orders(ModelMap modelMap) {
        return "member/orders";
    }

}
