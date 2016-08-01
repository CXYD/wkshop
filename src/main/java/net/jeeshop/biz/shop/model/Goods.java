package net.jeeshop.biz.shop.model;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
public class Goods {
    private Integer orderNum;
    private String goods;
    private String moon;

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getMoon() {
        return moon;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }

    public Goods() {
    }

    public Goods(String goods, String moon, Integer orderNum) {
        this.goods = goods;
        this.moon = moon;
        this.orderNum = orderNum;
    }
}
