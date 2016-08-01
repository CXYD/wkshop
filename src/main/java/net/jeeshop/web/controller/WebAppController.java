package net.jeeshop.web.controller;

import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.member.service.MemberService;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.model.OrderExample;
import net.jeeshop.biz.order.service.OrderService;
import net.jeeshop.biz.shop.client.CustomPageMapper;
import net.jeeshop.biz.shop.model.CustomPage;
import net.jeeshop.biz.shop.model.CustomPageExample;
import net.jeeshop.biz.shop.model.ShopInfo;
import net.jeeshop.biz.shop.service.ShopInfoService;
import net.jeeshop.core.util.MD5;
import net.jeeshop.web.controller.common.Const;
import net.jeeshop.web.controller.common.CookieUtil;
import net.jeeshop.web.controller.front.FrontBaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/5
 * Time: 17:37
 */
@Controller
@RequestMapping("/webapp")
public class WebAppController extends FrontBaseController{
    @Autowired
    MemberService memberService;
    @Autowired
    CustomPageMapper customPageMapper ;
    @Autowired
    OrderService orderService;
    @Autowired
    ShopInfoService shopInfoService;

    static String PAGE_TYPE_FOR_PRODUCT = "1";
    static String PAGE_TYPE_FOR_CLASS = "2";
    static String PAGE_TYPE_FOR_CONSTOME = "3";


    /**
     * 自定义页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}")
    public String frontIndex(@PathVariable long id,Model model){
        logger.debug("前台首页");

        CustomPage page = customPageMapper.selectByPrimaryKey(id);
        model.addAttribute("pageId", id);
        model.addAttribute("khid",page==null?"":page.getKhid());
//        return "front/home";
        return "front/pageTpl";
    }


    /**
     * 根据类型跳转到不同页面
     * @param type
     * @param id
     * @return
     */
    @RequestMapping("dspPage")
    public String dispatcherPage(String type, String id){

        if(PAGE_TYPE_FOR_PRODUCT.equals(type)){
            return "forward:/v1/goods/"+id;
        }else if(PAGE_TYPE_FOR_CONSTOME.equals(type)){
            return "forward:/webapp/"+id;
        }else{
            return "";
        }



    }


    /**
     * 首页
     * @param khid
     * @param model
     * @return
     */
    @RequestMapping(value = "homePage")
    public String homePage( String khid,Model model){
        CustomPageExample customPageExample = new CustomPageExample();
        CustomPageExample.Criteria criteria = customPageExample.createCriteria();
        criteria.andKhidEqualTo(khid);
        criteria.andIsindexEqualTo("1");
        List<CustomPage> pageList = customPageMapper.selectByExample(customPageExample);
        long id = 0l;
        if(pageList.size()>0) {
            id = pageList.get(0).getId();
        }

        model.addAttribute("pageId",id);
        model.addAttribute("khid",khid);
//        return  "front/home";
        return "front/pageTpl";
    }

    @RequestMapping("classPage")
    public String classPage(ModelMap model,String khid){
        logger.debug("前台分类");
        model.addAttribute("khid", khid);
        return "front/classPage";
    }


    @RequestMapping("findPage")
    public String findPage(ModelMap model,String khid){
        logger.debug("前台发现");
        model.addAttribute("khid", khid);
        return "front/findPage";
    }


    @RequestMapping("myPage")
    public String myPage(String khid,HttpServletRequest request,HttpServletResponse response){
        logger.debug("前台我的");
        try {
            request.setAttribute("khid", khid);
            //获取商城的名称
            if(StringUtils.isNotBlank(khid)){
                ShopInfo  shopInfo = shopInfoService.getShopInfo(khid);
                request.setAttribute("shopname",shopInfo.getShopname());
            }
            String cookieValueAfterDecode =CookieUtil.readCookie(request, response);

            if(StringUtils.isNotBlank(cookieValueAfterDecode)){
                String cookieValues[] = cookieValueAfterDecode.split(":");
                //判断是否在有效期内,过期就删除Cookie
                long validTimeInCookie = new Long(cookieValues[1]);
                if (validTimeInCookie < System.currentTimeMillis()) {
                    //删除Cookie
                    CookieUtil.clearCookie(response,request);
                    //你的Cookie已经失效,请重新登陆
                    request.setAttribute("username", null);
                }
                //取出cookie中的用户名
                String username = cookieValues[0];
                Member member = memberService.selectByUsername(username);
                if(member!=null){
                    String md5ValueInCookie = cookieValues[2];
                    String md5ValueFromUser = MD5.md5(member.getUsername() + ":" + member.getPassword()
                            + ":" + validTimeInCookie + ":" + CookieUtil.webKey);
                    //将结果与Cookie中的MD5码相比较,如果相同,自动登陆成功,并继续用户请求
                    if(md5ValueFromUser.equals(md5ValueInCookie)){
                        OrderExample orderExample = new OrderExample();
                        OrderExample.Criteria criteria = orderExample.createCriteria();
                        criteria.andContractmobileEqualTo(username);
                        List<OrderBean> orderList = orderService.selectByExample(orderExample);
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
                        request.setAttribute("username", username);
                        request.setAttribute("nickname", member.getNickName());
                        return "front/loginInMyPage";
                    }else{
                        request.setAttribute("username", null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "front/myPage";
    }



}
