package net.jeeshop.biz.order.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.client.OrderPayMapper;
import net.jeeshop.biz.order.model.OrderPayExample;
import net.jeeshop.biz.order.model.OrderPayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/22
 * Time: 16:08
 */
@Service
public class OrderPayRequestService extends BaseService<OrderPayRequest,OrderPayExample> {
    @Autowired
    private OrderPayMapper orderPayMapper;

    @Override
    protected BaseMapper getMapper() {
        return orderPayMapper;
    }
}
