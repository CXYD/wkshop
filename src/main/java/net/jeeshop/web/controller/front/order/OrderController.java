package net.jeeshop.web.controller.front.order;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.client.OrderItemMapper;
import net.jeeshop.biz.order.model.OrderExample;
import net.jeeshop.biz.order.model.OrderItem;
import net.jeeshop.biz.order.model.OrderItemExample;
import net.jeeshop.biz.order.service.OrderService;
import net.jeeshop.biz.product.model.ProductInfo;
import net.jeeshop.biz.product.service.ProductInfoService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单
 * @author pengdong
 * @date 16/3/2 22:05
 * Email: dinguangx@163.com
 */
@Controller
@RequestMapping("front/order")
public class OrderController  extends ManageBaseController<OrderBean,OrderExample> {

    private static  final String page_searchOrder ="front/order/searchOrder";
    private static final String page_editOrder="front/order/editOrder";
    private static final String page_hasresult="front/order/hasResult";
    private static final String page_noResult="front/order/noResult";

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    ProductInfoService productInfoService;


    @RequestMapping("searchOrder")
    public String searchOrder(String consignephone, PageQueryBean pageQueryBean,String status){
        logger.debug("手机号码："+consignephone);
        try {
            OrderExample orderExample = new OrderExample();
            OrderExample.Criteria criteria = orderExample.createCriteria();
            logger.debug("手机号："+consignephone);
            logger.debug("手机号："+status);
            if(StringUtils.isNotBlank(consignephone)){
                criteria.andContractmobileEqualTo(consignephone);
                if(StringUtils.isNotBlank(status)){
                    criteria.andOrderstatusEqualTo(status);
                }
                orderExample.setOrderByClause("o.create_time desc");
                PageBean pager  = orderService.queryOrder(orderExample,pageQueryBean);
                if(pager.getList().size()>0){
                    List odlist = pager.getList();
                    request.setAttribute("odlist",odlist);
                    return  page_hasresult;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return  page_noResult;
    }
    @RequestMapping("toSearchOrder")
    public String toSearchOrder(){
        return  page_searchOrder;
    }
    @RequestMapping("editOrder")
    public String editOrder(){
        return  page_editOrder;
    }

    /**
     * 订单详情
     * @param orderid
     * @param model
     * @return
     */
    @RequestMapping("detail")
    public ModelAndView orderDetail(String orderid, ModelAndView model){
        logger.debug("查询订单详情");
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andOrderNumEqualTo(orderid);

        OrderBean orderBean = orderService.selectUniqueByExample(orderExample);


        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andOrderIdEqualTo(orderBean.getId());

        List<OrderItem> orderItemList = orderItemMapper.selectByExampleWithBLOBs(orderItemExample);
        if(orderItemList==null || orderItemList.size()<1){
            model.setViewName(page_noResult);
        }else{
            model.setViewName("front/order/orderDetail");
        }
        model.addObject("order",orderBean);
//        model.addObject("orderItemList",orderItemList);  如果存在多件商品，也就是有购物车概念的话，返回此处
        model.addObject("orderItem",orderItemList.get(0));
        ProductInfo productInfo = productInfoService.selectById(orderItemList.get(0).getProductId());
        model.addObject("productImgUrl",productInfo.getImg());
        return model;
    }

    @Override
    public BaseService<OrderBean, OrderExample> getService() {
        return orderService;
    }
}
