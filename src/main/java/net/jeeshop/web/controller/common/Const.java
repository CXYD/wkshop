package net.jeeshop.web.controller.common;

/**
 * Created by pengdong on 2016/6/13.
 */
public class Const {

    public  static final String HANDLE_FAIL = "08";//办理失败
    public  static final String HANDLE_SUCCESS = "05";//办理成功
    public  static final String PRE_SETTLE = "0";//订单待结算
    public  static final String ALREADY_SETTLE = "1";//订单已结算

    public  static final int PRE_BALANCE = 0;//结算记录待结算
    public  static final int BALANCE_APPLY = 1;//结算记录已申请
    public  static final int ALREADY_BALANCE = 2;//结算记录已结算

    public  static final String pay_lianl = "21";//连连支付
    public  static final String PAY_ALIPAY = "22";//支付宝支付
    public  static final String PAY_DELIVERY   = "23";//货到付款
    public  static final String ORDERSTATUS_NOPAY  = "待付款";//订单待付款
    public  static final String ORDERSTATUS_NOHANDLE  = "待办理";//订单待办理
    public  static final String ORDERSTATUS_REFUNDED  = "已退款";//订单已退款

}
