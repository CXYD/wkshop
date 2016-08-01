package net.jeeshop.biz.product.bean;

import net.jeeshop.biz.product.model.ProductClass;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/1
 * Time: 16:41
 */
public class ProductClassBean extends ProductClass {

    private long productNums;

    public long getProductNums() {
        return productNums;
    }

    public void setProductNums(long productNums) {
        this.productNums = productNums;
    }
}
