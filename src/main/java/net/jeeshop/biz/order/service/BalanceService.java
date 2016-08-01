package net.jeeshop.biz.order.service;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.order.client.BalanceMapper;
import net.jeeshop.biz.order.model.Balance;
import net.jeeshop.biz.order.model.BalanceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengdong on 2016/6/2.
 */
@Service
public class BalanceService extends BaseService<Balance, BalanceExample> {
     @Autowired
     private BalanceMapper balanceMapper;
    @Override
    protected BaseMapper<Balance, BalanceExample> getMapper() {
        return balanceMapper;
    }
    public PageBean<Balance> selectPageBean(final Balance params, PageQueryBean pageQueryBean) {
        return executePageQuery(new PageQueryExecutor<Balance>() {
            @Override
            public List<Balance> executeQuery() {
                BalanceExample balanceExample = new BalanceExample();
                BalanceExample.Criteria criteria = balanceExample.createCriteria();
                return balanceMapper.selectByExample(balanceExample);
            }
        }, pageQueryBean);
    }

    public int addsettleRecordsBatch(List<Balance> list) {

        return balanceMapper.addsettleRecordsBatch(list);
    }
}
