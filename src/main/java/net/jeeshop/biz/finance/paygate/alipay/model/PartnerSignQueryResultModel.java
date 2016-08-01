package net.jeeshop.biz.finance.paygate.alipay.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 合作商户签约查询接口
 *
 * @author dylan
 * @date 16/3/10 22:23
 * Email: dinguangx@163.com
 */
public class PartnerSignQueryResultModel {
	private boolean success;
	private boolean signPayment;
	private boolean signRefund;
	private String userId;
	private String errMsg;
	private Map<String, String> response = new HashMap<String, String>();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSignPayment() {
		return signPayment;
	}

	public void setSignPayment(boolean signPayment) {
		this.signPayment = signPayment;
	}

	public boolean isSignRefund() {
		return signRefund;
	}

	public void setSignRefund(boolean signRefund) {
		this.signRefund = signRefund;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Map<String, String> getResponse() {
		return response;
	}

	public void putResponse(String name, String value){
		response.put(name, value);
	}
	

}
