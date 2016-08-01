package net.jeeshop.biz.shop.client;

import net.jeeshop.biz.shop.model.BuySet;
import net.jeeshop.biz.shop.model.WeiWan;
import net.jeeshop.web.util.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
public interface GeneralMapper {
    BuySet getBuySet(String khid);

    void upBuySet(BuySet buySet);

    void addBuySet(BuySet buySet);

    List getDKd(String khid);

    Integer getWWCount();

    List<WeiWan> getWeiWanList(Page<WeiWan> page);

    String TuiJian(String param);

    List getWeiWanCount(Page page);

    void upTuiJian(WeiWan weiWan);
}
