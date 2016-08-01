package net.jeeshop.web.controller.manage.order;

import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.service.OrderPayService;
import net.jeeshop.web.controller.manage.ManageBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pengdong on 2016/6/5.
 */
@Controller
@RequestMapping("manage/order/orderPay")
public class OrderPayController extends ManageBaseController {
    @Autowired
    OrderPayService orderPayService;
    private static final String page_toList = "manage/order/orderPayList";
    private OrderPayController() {
        super.page_toList = page_toList;
    }

    @Override
    public BaseService getService() {
        return null;
    }
}
