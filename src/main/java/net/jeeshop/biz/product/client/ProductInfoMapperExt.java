package net.jeeshop.biz.product.client;

import net.jeeshop.biz.product.bean.ProductInfoBean;
import net.jeeshop.biz.product.model.ProductInfoExample;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/8
 * Time: 11:06
 */
public interface ProductInfoMapperExt {
    public List<ProductInfoBean> selectByParams(ProductInfoExample params);
}
