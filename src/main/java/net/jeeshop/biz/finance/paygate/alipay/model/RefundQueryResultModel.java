package net.jeeshop.biz.finance.paygate.alipay.model;

/**
 * 退款返回结果信息
 * @author dylan
 * @date 16/3/10 22:23
 * Email: dinguangx@163.com
 */
public class RefundQueryResultModel extends RefundResultModel {
	private String refundNum;

	public String getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(String refundNum) {
		this.refundNum = refundNum;
	}
}
