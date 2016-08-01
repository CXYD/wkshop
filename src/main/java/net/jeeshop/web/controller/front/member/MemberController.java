package net.jeeshop.web.controller.front.member;

import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.member.service.MemberService;
import net.jeeshop.web.controller.common.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @RequestMapping("/nickname")
    public String nickname(Member member,Map<String, Object> map){
        map.put("member",member);
        return "front/user/nickname";
    }
    @RequestMapping("/upnickname")
    @ResponseBody
    public String upnickname(Member member){
        try {
            String nickName = member.getNickName();
            if(memberService.selectByNickname(nickName) != null) {
                return "昵称已经被使用！";
            }
            Member mb = memberService.selectByUsername(member.getUsername());
            if(mb!=null){
                mb.setNickName(member.getNickName());
                memberService.upNickName(mb);
            }
            return "昵称修改成功！";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "昵称修改失败！";
    }
    @RequestMapping("/gender")
    public String gender(Member member,Map<String, Object> map){
        map.put("member",member);
        return "front/user/gender";
    }
    @RequestMapping("/upgender")
    @ResponseBody
    public String upgender(Member member){
        try {
            Member mb = memberService.selectByUsername(member.getUsername());
            if(mb!=null){
                mb.setGender(member.getGender());
                memberService.upGender(mb);
            }
            return "性别修改成功！";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "性别修改失败！";
    }
    /**
     * 转到修改密码页面
     *
     * @return
     */
    @RequestMapping("toUpdatePwd")
    public String toChangePwd(String username,HttpServletRequest request) {
        request.setAttribute("username",username);
        return "front/user/toUpdatePwd";
    }
    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "updateMemberPwd", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateMemberPwd(@RequestParam("password")String password,@RequestParam("username")String username) {
        try {
            Member member = memberService.selectByUsername(username);
            if(member!=null){
                memberService.updateMemberPwd(member.getId(),password);
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }
    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "updateBirthDate", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateBirthDate(String birthDate,@RequestParam("username")String username) {
        try {
            Member member = memberService.selectByUsername(username);

            if(member!=null && StringUtils.isNotBlank(birthDate)){
                memberService.updateBirthDate(member.getId(),DateUtils.str2Date(birthDate));
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }
}
