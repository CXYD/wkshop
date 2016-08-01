package net.jeeshop.web.controller.front.user;

import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.member.service.MemberService;
import net.jeeshop.biz.system.service.SMSService;
import net.jeeshop.core.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by pengdong on 2016/6/20 0020.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MemberService memberService;
    @Autowired
    private SMSService smsService;
    @RequestMapping("checkUserByName")
    @ResponseBody
    public boolean checkUserByName(String username){
        Member user=memberService.selectByUsername(username);
        if(user!=null){
            return true;
        }
        return false;
    }
    @RequestMapping("/addRegUser")
    @ResponseBody
    public boolean register(Member member){
        try {
            member.setCreateTime(new Date());
            String md5Pass = MD5.md5(member.getPassword());
            member.setPassword(md5Pass);
            memberService.regMember(member);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequestMapping("frontRegister")
    public String frontRegister(String khid,HttpServletRequest request){
        request.setAttribute("khid",khid);
        return "/front/user/register";
    }

}
