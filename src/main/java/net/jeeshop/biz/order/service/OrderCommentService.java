package net.jeeshop.biz.order.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.client.OrderCommentMapper;
import net.jeeshop.biz.order.model.OrderComment;
import net.jeeshop.biz.order.model.OrderCommentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengdong on 2016/6/5.
 */
@Service
public class OrderCommentService extends BaseService<OrderComment, OrderCommentExample> {

    @Autowired
    private OrderCommentMapper orderCommentMapper;
    @Override
    protected BaseMapper<OrderComment, OrderCommentExample> getMapper() {
        return orderCommentMapper;


    }
}
