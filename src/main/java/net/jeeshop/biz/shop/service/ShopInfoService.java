
package net.jeeshop.biz.shop.service;

import net.jeeshop.biz.shop.client.ShopInfoMapper;
import net.jeeshop.biz.shop.model.ShopInfo;
import net.jeeshop.biz.shop.model.ShopInfoExample;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.system.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
@Service
public class ShopInfoService extends BaseService<ShopInfo, ShopInfoExample> {
    @Autowired
    ShopInfoMapper shopInfoMapper;

    @Override
    protected BaseMapper<ShopInfo, ShopInfoExample> getMapper() {
        return shopInfoMapper;
    }

    public void upShopInfo(ShopInfo shopInfo) {
        shopInfoMapper.upShopInfo(shopInfo);
    }

    public ShopInfo getShopInfo(String khid){
        ShopInfoExample shopInfoExample = new ShopInfoExample();
        shopInfoExample.createCriteria().andKhidEqualTo(khid);
        return (ShopInfo) shopInfoMapper.selectByExample(shopInfoExample).get(0);
    }

    public List getMoons() {
        return shopInfoMapper.getMoons();
    }

    public void addShop(ShopInfo shopInfo) {
        shopInfoMapper.addShop(shopInfo);
    }

    public void upUserKhid(SysUser loginUser) {
        shopInfoMapper.upUserKhid(loginUser);
    }

    public void upPic(ShopInfo shopInfo) {
        shopInfoMapper.upPic(shopInfo);
    }
}
