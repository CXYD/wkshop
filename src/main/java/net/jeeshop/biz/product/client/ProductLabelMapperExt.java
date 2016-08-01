package net.jeeshop.biz.product.client;

import net.jeeshop.biz.product.model.ProductLabel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/1
 * Time: 10:13
 */
public interface ProductLabelMapperExt {

    public List<ProductLabel> selectByParams(ProductLabel params);

    ProductLabel selectUserBeanById(long uid);
}
