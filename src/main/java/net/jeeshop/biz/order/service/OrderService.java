package net.jeeshop.biz.order.service;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.bean.OrderBean;
import net.jeeshop.biz.order.client.OrderMapper;
import net.jeeshop.biz.order.model.OrderExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xulingqiang on 2016/5/31.
 */
@Service
public class OrderService extends BaseService<OrderBean, OrderExample> {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    protected BaseMapper<OrderBean, OrderExample> getMapper() {
        return orderMapper;
    }

    public PageBean<OrderBean> selectPageBean(final OrderBean params, PageQueryBean pageQueryBean) {
        return executePageQuery(new PageQueryExecutor<OrderBean>() {
            @Override
            public List<OrderBean> executeQuery() {
                OrderExample orderExample = new OrderExample();
                OrderExample.Criteria criteria = orderExample.createCriteria();
                if(StringUtils.isNotBlank(params.getOrderNum())){
                    criteria.andOrderNumLike("%"+params.getOrderNum()+"%");
                }
                if(StringUtils.isNotBlank(params.getLinkman())){
                    criteria.andLinkmanLike("%"+params.getLinkman()+"%");
                }
                logger.debug("手机号："+String.valueOf(params.getContractmobile()));
                if(params.getContractmobile()!=null ){
                    criteria.andContractmobileEqualTo(params.getContractmobile());
                }
                if(StringUtils.isNotBlank(params.getPaytype())){
                    criteria.andPaytypeEqualTo(params.getPaytype());
                }
                if(StringUtils.isNotBlank(params.getOrderstatus())){
                    criteria.andOrderNumEqualTo(params.getOrderstatus());
                }
                return orderMapper.selectByExample(orderExample);
            }
        }, pageQueryBean);
    }


    public PageBean<OrderBean> selectBalPayList(final OrderExample orderExample, PageQueryBean pageQueryBean)
    {
        return executePageQuery(new PageQueryExecutor<OrderBean>() {
            @Override
            public List<OrderBean> executeQuery() {
                return orderMapper.selectBalPay(orderExample);
            }
        }, pageQueryBean);
    }
    public PageBean<OrderBean> queryOrder(final OrderExample orderExample, PageQueryBean pageQueryBean)
    {
        return executePageQuery(new PageQueryExecutor<OrderBean>() {
            @Override
            public List<OrderBean> executeQuery() {
                return orderMapper.queryOrder(orderExample);
            }
        }, pageQueryBean);
    }
    public List<OrderBean> queryOrderDetail(OrderExample example)
    {
        return orderMapper.queryOrder(example);
    }

    public List<OrderBean> querySettleRecords(OrderExample orderExample) {
        return orderMapper.querySettleRecords(orderExample);
    }
}
