package net.jeeshop.web.controller;

import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.model.ProductInfo;
import net.jeeshop.biz.product.service.GoodsService;
import net.jeeshop.biz.product.service.ProductInfoService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/6
 * Time: 11:27
 */
@RequestMapping("/v1")
@Controller
public class EngineController extends ManageBaseController{

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private GoodsService goodsService;

    @Override
    public BaseService getService() {
        return productInfoService;
    }

    /**
     * 自定义页面
      * @param id
     * @param isindex
     * @param khid
     * @return
     */
    @RequestMapping("page")
    public String page(int id,String isindex,String khid){
        return "/front/home";
    }

    /**
     * 商品页面
     * 根据产品id
     * @return
     */
    @RequestMapping(value="goods/{id}")
    public String  goods(@PathVariable long id,Model model){

        logger.debug("加载产品页面:{}",id);
        ProductInfo info = productInfoService.selectById(id);
        logger.debug("加载产品信息：{}",info);
        model.addAttribute("goodsInfo", info);
        model.addAttribute("itemId",id);
        return "/front/product/goodsTpl";
    }
}
