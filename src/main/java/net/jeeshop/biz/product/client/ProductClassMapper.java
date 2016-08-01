package net.jeeshop.biz.product.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.product.model.ProductClass;
import net.jeeshop.biz.product.model.ProductClassExample;

public interface ProductClassMapper extends BaseMapper<ProductClass, ProductClassExample> {
    int countByExample(ProductClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductClass record);

    int insertSelective(ProductClass record);

    List<ProductClass> selectByExample(ProductClassExample example);

    ProductClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductClass record);

    int updateByPrimaryKey(ProductClass record);
}