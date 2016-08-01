package net.jeeshop.biz.product.bean;

import net.jeeshop.biz.product.model.ProductInfo;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/8
 * Time: 10:49
 */
public class ProductInfoBean extends ProductInfo {
    private String labelName;
    private String typeName;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


}
