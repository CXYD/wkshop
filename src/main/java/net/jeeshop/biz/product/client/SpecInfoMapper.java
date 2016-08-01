package net.jeeshop.biz.product.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.product.model.SpecInfo;
import net.jeeshop.biz.product.model.SpecInfoExample;

import java.util.List;

public interface SpecInfoMapper extends BaseMapper<SpecInfo, SpecInfoExample> {
    int countByExample(SpecInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SpecInfo record);

    int insertSelective(SpecInfo record);

    List<SpecInfo> selectByExampleWithBLOBs(SpecInfoExample example);

    List<SpecInfo> selectByExample(SpecInfoExample example);

    SpecInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpecInfo record);

    int updateByPrimaryKeyWithBLOBs(SpecInfo record);

    int updateByPrimaryKey(SpecInfo record);
}