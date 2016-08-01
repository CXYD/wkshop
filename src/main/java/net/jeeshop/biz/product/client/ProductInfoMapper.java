package net.jeeshop.biz.product.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.product.bean.ProductInfoBean;
import net.jeeshop.biz.product.model.ProductInfo;
import net.jeeshop.biz.product.model.ProductInfoExample;

import java.util.List;

public interface ProductInfoMapper extends BaseMapper<ProductInfoBean, ProductInfoExample> {
    int countByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    List<ProductInfo> selectByExampleWithBLOBs(ProductInfoExample example);

    List<ProductInfoBean> selectByExample(ProductInfoExample example);

    ProductInfoBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKeyWithBLOBs(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}