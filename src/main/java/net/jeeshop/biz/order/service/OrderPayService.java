package net.jeeshop.biz.order.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.client.OrderPayMapper;
import net.jeeshop.biz.order.model.OrderPay;
import net.jeeshop.biz.order.model.OrderPayExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengdong on 2016/6/6.
 */
@Service
public class OrderPayService extends BaseService<OrderPay, OrderPayExample> {
    @Autowired
    private OrderPayMapper orderPayMapper;
    @Override
    protected BaseMapper<OrderPay, OrderPayExample> getMapper() {
        return orderPayMapper;
    }
}
