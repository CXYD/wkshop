package net.jeeshop.web.controller.manage.shop;

import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.shop.model.Goods;
import net.jeeshop.biz.shop.model.ShopInfo;
import net.jeeshop.biz.shop.service.GeneralService;
import net.jeeshop.biz.shop.service.ShopInfoService;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.web.controller.manage.ManageBaseController;
import net.jeeshop.web.util.LoginUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hyg
 * Date: 16/5/30
 * Time: 17:29
 */
@Controller
@RequestMapping("manage/shopInfo")
public class ShopInfoController extends ManageBaseController {
    @Autowired
    ShopInfoService shopInfoService;
    @Autowired
    GeneralService generalService;

    @Override
    public BaseService getService() {
        return null;
    }

    ShopInfoController(){
        super.page_toList = "manage/shop/shopInfo/shopIndex";
        super.page_toEdit = "manage/shop/shopInfo/editShopInfo";
        super.page_toAdd = "manage/shop/shopInfo/addShop";
    }


    @RequestMapping("addShop")
    @ResponseBody
    public String addShop(ShopInfo shopInfo){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        if (loginUser == null) {
            return "redirect:/manage/user/login";
        }
        String khid = "SH"+System.currentTimeMillis()/1000;
        shopInfo.setKhid(khid);
        shopInfo.setCreatetime(new Date());
        shopInfo.setUpdateaccount(loginUser.getUsername());
        shopInfoService.addShop(shopInfo);
        loginUser.setKhid(khid);
        loginUser.setRoleId(Long.parseLong("9"));
        shopInfoService.upUserKhid(loginUser);
        logger.debug("shopInfo{}",shopInfo);
        return "";
    }

    @RequestMapping("addShopSucc")
    public String addSucc(){
        return "/manage/shop/shopInfo/addSuccess";
    }

    /**
     * 编辑
     * @param map
     * @return
     */
    @RequestMapping("editInfo")
    public String editInfo(Map<String, Object> map){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        if (LoginUserHolder.getLoginUser() == null) {
            return "redirect:/manage/user/login";
        }
        if(LoginUserHolder.getLoginUser().getKhid()==null||LoginUserHolder.getLoginUser().getKhid().equals("")){
            return "/manage/shop/shopInfo/addShop";
        }
        ShopInfo shopInfo = shopInfoService.getShopInfo(loginUser.getKhid());
        if(shopInfo.getLogurl()==null||shopInfo.getLogurl()==""){
            shopInfo.setLogurl("${ctxStatic}/img/avatar-2.jpg");
        }
        logger.debug("shopInfo:{}",shopInfo);
        map.put("shopInfo",shopInfo);
        return page_toEdit;
    }


    /**
     * 更新
     * @param shopInfo
     *
     */
    @RequestMapping("uppic")
    @ResponseBody
    public String uppic(ShopInfo shopInfo){
        shopInfoService.upPic(shopInfo);
        return "";
    }
    @RequestMapping("updateInfo")
    public void updateInfo(ShopInfo shopInfo){
        shopInfoService.upShopInfo(shopInfo);
    }

    /**
     * 商城首页
     * @param map
     * @return
     */
    @RequestMapping("shopIndex")
    public String shopIndex(Map<String, Object> map){
        SysUser loginUser = LoginUserHolder.getLoginUser();
        if (LoginUserHolder.getLoginUser() == null) {
            return "redirect:/manage/user/login";
        }
        if(StringUtils.isBlank(LoginUserHolder.getLoginUser().getKhid())){
            return "/manage/shop/shopInfo/addShop";
        }

        List<String> moons=shopInfoService.getMoons();
        map.put("moons",moons);
        String lastMoon=moons.get(0);

        map.put("lastMoon",lastMoon);
        return "/manage/shop/shopInfo/shopIndex";
    }

    /**
     * 获取数据
     * @param moon
     * @return
     */
    @RequestMapping(value = "getGoodsByMoon")
    @ResponseBody
    public Object getGoodsByMoon(String moon){

        List good  = new ArrayList();
        List num = new ArrayList();
        List resultList=new ArrayList();
        List<Goods> gList=new ArrayList<Goods>();

        Goods g1=new Goods("手机","201606",67);
        Goods g2=new Goods("电脑","201606",31);
        Goods g3=new Goods("冰箱","201606",18);
        Goods g4=new Goods("哈哈","201606",23);
        Goods g5=new Goods("洗衣机","201606",42);
        Goods g6=new Goods("电视机","201606",56);
        Goods g7=new Goods("鼠标","201606",98);
        Goods g8=new Goods("键盘","201606",72);
        Goods g9=new Goods("显卡","201606",88);
        Goods g10=new Goods("电源","201606",118);
        //按月份查询，并按销量排序（取前十）
        gList.add(g1);
        gList.add(g2);
        gList.add(g3);
        gList.add(g4);
        gList.add(g5);
        gList.add(g6);
        gList.add(g7);
        gList.add(g8);
        gList.add(g9);
        gList.add(g10);

        if(moon.equals("201606")) {
            good.add("6手机");
            good.add("6电脑");
            good.add("6冰箱");
            System.out.println("haha ");
        }
        if(moon.equals("201605")){
            good.add("5月份手机");
            good.add("5月份电脑");
            good.add("5月份冰箱");
        }
        num.add(59);
        num.add(22);
        num.add(37);

        resultList.add(good);
        resultList.add(num);

        return resultList;
    }

    @RequestMapping("statistics")
    @ResponseBody
    public List Statistics(){
        List goods  = new ArrayList();
//        List moons  = new ArrayList();
        List result = new ArrayList();
        goods.add("显卡");
        goods.add("主板");
        goods.add("CPU");
        goods.add("电源");
        goods.add("硬盘");
//        moons.add("201601");
//        moons.add("201602");
//        moons.add("201603");
//        moons.add("201604");
//        moons.add("201605");
//        moons.add("201606");
        result.add(goods);
        logger.debug("result:{}",result);
        return result;
    }
    @RequestMapping("look")
    @ResponseBody
    public List look(String moon,String good){
        List days = new ArrayList();
        List nums  = new ArrayList();
        List result = new ArrayList();
        nums.add("26");
        nums.add("57");
        nums.add("79");
        nums.add("29");
        nums.add("37");
        nums.add("62");

        days.add("2016-06-01");
        days.add("2016-06-02");
        days.add("2016-06-03");
        days.add("2016-06-04");
        days.add("2016-06-05");
        days.add("2016-06-06");
        result.add(days);
        result.add(nums);
        logger.debug("result:{}",result);
        return result;
    }

    @RequestMapping("flowrate")
    @ResponseBody
    public List flowrate(String moon){
        List days = new ArrayList();
        List pvs  = new ArrayList();
        List uvs  = new ArrayList();
        List result3 = new ArrayList();

        days.add("2016-06-01");
        days.add("2016-06-02");
        days.add("2016-06-03");
        days.add("2016-06-04");
        days.add("2016-06-05");
        days.add("2016-06-06");

        pvs.add("61");
        pvs.add("36");
        pvs.add("36");
        pvs.add("69");
        pvs.add("55");
        pvs.add("29");

        uvs.add("51");
        uvs.add("29");
        uvs.add("36");
        uvs.add("59");
        uvs.add("39");
        uvs.add("51");

        result3.add(days);
        result3.add(pvs);
        result3.add(uvs);
        logger.debug("result:{}",result3);
        return result3;
    }

    @RequestMapping("commodityStatistics")
    public String commodityStatistics(){
        return "/manage/shop/shopInfo/commodityStatistics";
    }

}
