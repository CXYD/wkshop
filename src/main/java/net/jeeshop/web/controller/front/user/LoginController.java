package net.jeeshop.web.controller.front.user;

import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.member.service.MemberService;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.model.OrderExample;
import net.jeeshop.biz.order.service.OrderService;
import net.jeeshop.biz.shop.model.ShopInfo;
import net.jeeshop.biz.shop.service.ShopInfoService;
import net.jeeshop.web.controller.common.Const;
import net.jeeshop.web.controller.common.CookieUtil;
import net.jeeshop.web.controller.front.FrontBaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author dylan
 * @date 2016-04-04 19-46
 */
@Controller
@RequestMapping("front/user")
public class LoginController extends FrontBaseController {
    @Autowired
    private MemberService memberService;
    @Autowired
    OrderService orderService;
    @Autowired
    ShopInfoService shopInfoService;

    @RequestMapping("/login")
    public String login(String khid,HttpServletRequest request) {
        request.setAttribute("khid",khid);
        return "front/user/login";
    }
    @RequestMapping("/checkAccount")
    @ResponseBody
    public boolean checkAccount(Member member) {
        try {
            //用户验证
            String username = member.getUsername();
            String password = member.getPassword();
            Member member1 = memberService.selectByUsernamePassword(username, password);
            if (member1!= null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequestMapping("/doLogin")
    public String doLogin(Member member,String khid,HttpServletRequest request,HttpServletResponse response) {
        try {
            String username = member.getUsername();
            String password = member.getPassword();
            //记录登录日志
            //memberService.logLogin(acc);
            request.setAttribute("khid",khid);
            //获取商城的名称
            if(StringUtils.isNotBlank(khid)){
                ShopInfo shopInfo = shopInfoService.getShopInfo(khid);
                request.setAttribute("shopname",shopInfo.getShopname());
            }
            OrderExample orderExample = new OrderExample();
            if(StringUtils.isNotBlank(username)){
                Member acc = memberService.selectByUsernamePassword(username, password);
                //保存到cookie中
                CookieUtil.saveCookie(acc,response);
                OrderExample.Criteria criteria = orderExample.createCriteria();
                criteria.andContractmobileEqualTo(username);
                List<OrderBean> orderList = orderService.queryOrderDetail(orderExample);
                int noPaySum=0;
                int nohandledSum =0;
                int refundedSum=0;
                for(int i=0;i<orderList.size();i++){
                    OrderBean orderBean = orderList.get(i);
                    String orderstatus = orderBean.getOrderstatus();
                    if(orderstatus.equals(Const.ORDERSTATUS_NOPAY)){
                        noPaySum++;
                    }else if(orderstatus.equals(Const.ORDERSTATUS_NOHANDLE)){
                        nohandledSum++;
                    }else if(orderstatus.equals(Const.ORDERSTATUS_REFUNDED)){
                        refundedSum++;
                    }
                }
                request.setAttribute("noPaySum", noPaySum);
                request.setAttribute("nohandledSum", nohandledSum);
                request.setAttribute("refundedSum", refundedSum);
                request.setAttribute("username", acc.getUsername());
                request.setAttribute("nickname", acc.getNickName());
                return "front/loginInMyPage";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:login?khid="+khid;
    }
    @RequestMapping("/forgetPassword")
    public String forgetPassword(String khid,HttpServletRequest request) {
        request.setAttribute("khid",khid);
        return "front/user/forgetPassword";
    }

    @RequestMapping("/logout")
    public String logout(String khid,HttpServletRequest request,HttpServletResponse response) {
        try {
            CookieUtil.clearCookie(response,request);
            request.setAttribute("khid",khid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "front/myPage";
    }
}
