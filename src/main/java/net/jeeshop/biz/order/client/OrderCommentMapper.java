package net.jeeshop.biz.order.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.order.model.OrderComment;
import net.jeeshop.biz.order.model.OrderCommentExample;

import java.util.List;

public interface OrderCommentMapper extends BaseMapper<OrderComment, OrderCommentExample> {
    int countByExample(OrderCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderComment record);

    int insertSelective(OrderComment record);

    List<OrderComment> selectByExample(OrderCommentExample example);

    OrderComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderComment record);

    int updateByPrimaryKey(OrderComment record);
}