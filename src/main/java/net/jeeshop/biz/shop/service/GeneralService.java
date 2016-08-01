package net.jeeshop.biz.shop.service;

import net.jeeshop.biz.shop.client.GeneralMapper;
import net.jeeshop.biz.shop.model.BuySet;
import net.jeeshop.biz.shop.model.WeiWan;
import net.jeeshop.web.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
@Service
public class GeneralService {
    @Autowired
    private GeneralMapper generalMapper;

    public BuySet getBuySet(String khid) {
        return generalMapper.getBuySet(khid);
    }

    public void upBuySet(BuySet buySet) {
        generalMapper.upBuySet(buySet);
    }

    public void addBuySet(BuySet buySet) {
        generalMapper.addBuySet(buySet);
    }

    public List getDKd(String khid) {
        return generalMapper.getDKd(khid);
    }

    public Integer getWWCount() {
        return generalMapper.getWWCount();
    }

    public List<WeiWan> getWeiWanList(Page<WeiWan> page) {
        return generalMapper.getWeiWanList(page);
    }

    public String TuiJian(String param) {
        return generalMapper.TuiJian(param);
    }

    public List getWeiWanCount(Page page) {
        return generalMapper.getWeiWanCount(page);
    }

    public void upTuiJian(WeiWan weiWan) {
        generalMapper.upTuiJian(weiWan);
    }
}
