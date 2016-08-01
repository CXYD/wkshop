package net.jeeshop.biz.finance.paygate.alipay.model;

/**
 * 退款冻结结果:冻结订单号^冻结账户^冻结账户 ID^冻结金额^处理时间^状态^描述码
 *
 * @author dylan
 * @date 16/3/10 22:23
 * Email: dinguangx@163.com
 */
public class RefundFreezedModel {
	private String traceNum;
	private String freezeAccount;
	private String freezePartnerNum;
	private long freezeAmount;
	private String freezeTime;
	private String freezeState;
	private String msg;

	public String getTraceNum() {
		return traceNum;
	}

	public void setTraceNum(String traceNum) {
		this.traceNum = traceNum;
	}

	public String getFreezeAccount() {
		return freezeAccount;
	}

	public void setFreezeAccount(String freezeAccount) {
		this.freezeAccount = freezeAccount;
	}

	public String getFreezePartnerNum() {
		return freezePartnerNum;
	}

	public void setFreezePartnerNum(String freezePartnerNum) {
		this.freezePartnerNum = freezePartnerNum;
	}

	public long getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(long freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public String getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(String freezeTime) {
		this.freezeTime = freezeTime;
	}

	public String getFreezeState() {
		return freezeState;
	}

	public void setFreezeState(String freezeState) {
		this.freezeState = freezeState;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
