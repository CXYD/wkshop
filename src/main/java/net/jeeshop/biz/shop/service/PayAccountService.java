package net.jeeshop.biz.shop.service;

import net.jeeshop.biz.shop.client.PayAccountMapper;
import net.jeeshop.biz.shop.model.PayAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
@Service
public class PayAccountService {
    @Autowired
    PayAccountMapper payAccountMapper;

    public void addAccount(PayAccount payAccount) {
        payAccountMapper.addAccount(payAccount);
    }

    public PayAccount getAccount(String khid) {
        return payAccountMapper.getAccount(khid);
    }

    public void upAccount(PayAccount payAccount) {
        payAccountMapper.upAccount(payAccount);
    }
}
