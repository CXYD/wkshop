package net.jeeshop.web.controller.front.member;

import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.member.model.UserAddress;
import net.jeeshop.biz.member.service.MemberService;
import net.jeeshop.biz.member.service.UserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by pengdong on 2016/7/11.
 */
@Controller
@RequestMapping("front/mine")
public class MineController {
    private Logger logger = LoggerFactory.getLogger(MineController.class);
    private static final String page_addressIndex = "front/member/addressIndex";
    private static final String page_addAddress = "front/member/addAddress";
    private static final String page_editAddress= "front/member/editAddress";

    @Autowired
    UserAddressService userAddressService;
    @Autowired
    private MemberService memberService;
    @RequestMapping("toAddress")
    public String toAddress(String name,HttpServletRequest request,HttpServletResponse response){
        try {
            logger.debug(name);
            List<UserAddress> addressList = userAddressService.selectUserAddress(name);
            request.setAttribute("addressList",addressList);
            request.setAttribute("name",name);
            return page_addressIndex;
        } catch (Exception e) {
            e.printStackTrace();
            //request.setAttribute("khid",khid);
            return null;
        }
    }

    @RequestMapping("toAddAddress")

    public String toAddAddress(HttpServletRequest request,@RequestParam("name")String name){
        request.setAttribute("name",name);
        return page_addAddress;
    }

    @RequestMapping("saveAddress")
    @ResponseBody
    public boolean saveAddress(@ModelAttribute("address")UserAddress address){
        try {
            logger.debug(address.getAddress());
             userAddressService.insert(address);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 更新默认选中的值
     */

    @RequestMapping("updateDefault")
    @ResponseBody
    public boolean updateDefault(@RequestParam("id")String id){
        try {
            logger.debug(id);
                //去除之前的默认值
            userAddressService.updateDefault();
            UserAddress userAddress = new UserAddress();
            userAddress.setId(Long.parseLong(id));
            userAddress.setDefaulttype("1");
            userAddressService.update(userAddress);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequestMapping("delById")
    @ResponseBody
    public int delById(@RequestParam("id")String id){
        try {
            return userAddressService.deleteById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
    @RequestMapping("toEditAddress")
    public String toEditAddress(@RequestParam("id")String id,HttpServletRequest request){
        UserAddress userAddress = userAddressService.selectById(Long.parseLong(id));
        if(userAddress!=null){
            request.setAttribute("userAddress",userAddress);
            request.setAttribute("name",userAddress.getUsername());
        }
        return page_editAddress;
    }

    /**
     * 编辑地址
     * @param address
     * @return
     */
    @RequestMapping("editAddress")
    @ResponseBody
    public int editAddress(@ModelAttribute("address")UserAddress address){
        try {
            return userAddressService.update(address);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    @RequestMapping("accountManage")
    public String accountManage(String name,HttpServletRequest request){
        logger.debug(name);
        Member member = memberService.selectByUsername(name);
        request.setAttribute("member", member);
     return "front/user/userManage";
    }
}
