package net.jeeshop.biz.shop.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.shop.model.CustomPage;
import net.jeeshop.biz.shop.model.CustomPageExample;

import java.util.List;

public interface CustomPageMapper extends BaseMapper<CustomPage, CustomPageExample> {
    int countByExample(CustomPageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomPage record);

    int insertSelective(CustomPage record);

    List<CustomPage> selectByExampleWithBLOBs(CustomPageExample example);

    List<CustomPage> selectByExample(CustomPageExample example);

    CustomPage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomPage record);

    int updateByPrimaryKeyWithBLOBs(CustomPage record);

    int updateByPrimaryKey(CustomPage record);

    int updatePageHomeStatus(String khid);
}