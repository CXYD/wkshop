package net.jeeshop.biz.finance.paygate.alipay.model;
/**
 *
 * @author dylan
 * @date 16/3/10 22:23
 * Email: dinguangx@163.com
 */
public class CommonResponse {
	private boolean success;
	private String errMsg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
