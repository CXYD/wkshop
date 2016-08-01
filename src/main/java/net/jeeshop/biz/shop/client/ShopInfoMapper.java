package net.jeeshop.biz.shop.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.shop.model.ShopInfo;
import net.jeeshop.biz.shop.model.ShopInfoExample;
import net.jeeshop.biz.system.model.SysUser;

public interface ShopInfoMapper extends BaseMapper<ShopInfo, ShopInfoExample> {
    int countByExample(ShopInfoExample example);

    int insert(ShopInfo record);

    int insertSelective(ShopInfo record);

    List<ShopInfo> selectByExample(ShopInfoExample example);

    void upShopInfo(ShopInfo shopInfo);

    List getMoons();

    void addShop(ShopInfo shopInfo);

    void upUserKhid(SysUser loginUser);

    void upPic(ShopInfo shopInfo);
}