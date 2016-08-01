package net.jeeshop.web.controller.manage.shop;

import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.shop.model.BuySet;
import net.jeeshop.biz.shop.model.WeiWan;
import net.jeeshop.biz.shop.service.GeneralService;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import net.jeeshop.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hyg
 * Date: 16/5/30
 * Time: 17:48
 */
@Controller
@RequestMapping("manage/generalSet")
public class GeneralSetController extends ManageBaseController {
    @Autowired
    GeneralService generalService;
    @Override
    public BaseService getService() {
        return null;
    }

    GeneralSetController(){
        super.page_toList = "manage/shop/generalSet/generalSetList";
        super.page_toEdit = "manage/shop/generalSet/editGeneralSet";
        super.page_toAdd = "manage/shop/generalSet/editGeneralSet";
    }

    @RequestMapping("edit")
    public String edit(Map<String, Object> map){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        if (LoginUserHolder.getLoginUser() == null) {
            return "redirect:/manage/user/login";
        }
        String khid=loginUser.getKhid();
        if(khid==null||khid.equals("")){
            return "/manage/shop/shopInfo/addShop";
        }
        BuySet buySet = generalService.getBuySet(khid);
        if(buySet==null){
            buySet=new BuySet("0","0","0","0","0");
        }
        List dkd = generalService.getDKd(khid);
        if(dkd.size()==0){
            dkd.add("商品内容为空,请先添加商品！");
        }
        map.put("dkd",dkd);
        map.put("buySet", buySet);
        return page_toEdit;
    }

    @RequestMapping("buySet")
    @ResponseBody
    public String buySet(BuySet buySet){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        if (LoginUserHolder.getLoginUser() == null) {
            return "redirect:/manage/user/login";
        }
        buySet.setKhid(loginUser.getKhid());
        if(generalService.getBuySet(loginUser.getKhid())!=null){
            generalService.upBuySet(buySet);
        }else {
            generalService.addBuySet(buySet);
        }
        return "";
    }
    @RequestMapping("getWeiWan")
    @ResponseBody
    public Page getWeiWan(Page page){
        List list=generalService.getWeiWanCount(page);
        Integer count=list.size();
        Page<WeiWan> page2 = new Page<WeiWan>(page.getPageNo(),count,"");
        page2.setZifei(page.getZifei());
        page2.setT_0206(page.getT_0206());
        page2.setTuijian(page.getTuijian());
        page2.setSort(page.getSort());
        List<WeiWan> list2 = generalService.getWeiWanList(page2);
        page2.setList(list2);
        return page2;
    }
    @RequestMapping("tuiJian")
    @ResponseBody
    public String tuiJian(String param){
        String name=generalService.TuiJian(param);
        return name;
    }
    @RequestMapping("upTuiJian")
    @ResponseBody
    public String upTuiJian(WeiWan weiWan){
        generalService.upTuiJian(weiWan);
        return "成功";
    }


    @ResponseBody
    @RequestMapping("queryBuySet")
    public Object queryBuySet(String khid){
        return generalService.getBuySet(khid)==null?"":generalService.getBuySet(khid);
    }
}
