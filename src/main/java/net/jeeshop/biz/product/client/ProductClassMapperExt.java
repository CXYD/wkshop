package net.jeeshop.biz.product.client;

import net.jeeshop.biz.product.bean.ProductClassBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/1
 * Time: 16:46
 */
public interface ProductClassMapperExt {

    public List<ProductClassBean> selectByParams(ProductClassBean params);

    ProductClassBean selectClassBeanById(long cid);
}
