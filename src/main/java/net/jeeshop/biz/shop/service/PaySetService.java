package net.jeeshop.biz.shop.service;

import net.jeeshop.biz.shop.client.PaySetMapper;
import net.jeeshop.biz.shop.model.PayAccount;
import net.jeeshop.biz.shop.model.PaySet;
import net.jeeshop.biz.shop.model.PaySetExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
@Service
public class PaySetService {

    @Autowired
    PaySetMapper paySetMapper;
    public void addPaySet(PaySet paySet) {
        paySetMapper.insertSelective(paySet);
    }

    public PaySet getPaySet(PaySet paySet) {
//        PaySetExample paySetExample = new PaySetExample();
//        paySetExample.createCriteria().andKhidEqualTo(paySet.getKhid());
//        return (PaySet)paySetMapper.selectByExample(paySetExample).get(0);
        return paySetMapper.getPaySet(paySet);
    }

    public void upPaySet(PaySet paySet) {
        paySetMapper.upPaySet(paySet);
    }


}
