package net.jeeshop.biz.finance.paygate.alipay.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝单笔交易查询结果
 *
 * @author dylan
 * @date 16/3/10 22:23
 * Email: dinguangx@163.com
 */
public class QueryResultModel {
	public String is_success;
	public String error;
	public String sign;
	public String sign_type;
	private Map<String, String> requestData = new HashMap<String, String>();
	private Map<String, String> tradeData = new HashMap<String, String>();

	public void addRequestData(String key, String value) {
		this.requestData.put(key, value);
	}

	public void addTradeData(String key, String value) {
		this.tradeData.put(key, value);
	}

	public Map<String, String> requestDatas() {
		return this.requestData;
	}

	public Map<String, String> tradeDatas() {
		return this.tradeData;
	}

}
