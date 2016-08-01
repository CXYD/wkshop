package net.jeeshop.biz.product.client;

import java.util.List;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.product.model.ProductLabel;
import net.jeeshop.biz.product.model.ProductLabelExample;

public interface ProductLabelMapper extends BaseMapper<ProductLabel, ProductLabelExample> {
    int countByExample(ProductLabelExample example);

    int deleteByPrimaryKey(Long labelId);

    int insert(ProductLabel record);

    int insertSelective(ProductLabel record);

    List<ProductLabel> selectByExample(ProductLabelExample example);

    ProductLabel selectByPrimaryKey(Long labelId);

    int updateByPrimaryKeySelective(ProductLabel record);

    int updateByPrimaryKey(ProductLabel record);
}