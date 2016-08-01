package net.jeeshop.biz.product.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.product.model.GoodsInfo;
import net.jeeshop.biz.product.model.GoodsInfoExample;

import java.util.List;

public interface GoodsInfoMapper extends BaseMapper<GoodsInfo, GoodsInfoExample> {
    int countByExample(GoodsInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    List<GoodsInfo> selectByExampleWithBLOBs(GoodsInfoExample example);

    List<GoodsInfo> selectByExample(GoodsInfoExample example);

    GoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKeyWithBLOBs(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    int deleteByProductId(Long productId);
}