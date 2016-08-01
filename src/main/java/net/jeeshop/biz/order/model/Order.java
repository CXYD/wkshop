package net.jeeshop.biz.order.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class Order extends BaseModel implements Serializable {
    /** 客户编号 */
    private String khid;

    /** 会员ID */
    private Long memberId;

    /** 订单编号 */
    private String orderNum;

    /** 宽带账号 */
    private String adslaccount;

    /** 订单状态 */
    private String orderstatus;

    /** 订单总金额 */
    private Double amount;

    /** 商品金额 */
    private Double productAmount;

    /** 配送费用 */
    private Double shipAmount;

    /** 标题 */
    private String title;

    /** 总重量 */
    private Double weight;

    /** 数量 */
    private Integer quantity;

    /** 支付方式 */
    private String paytype;

    /** 是否已经支付(0否， 1是) */
    private String isPaid;

    /** 支付手续费 */
    private Double payFee;

    /** 支付流水号 */
    private String paymentNum;

    /** 支付时间 */
    private Date paymentTime;

    /** 联系人 */
    private String linkman;

    /** 手机号码联系人 */
    private String contractmobile;

    /** 配送地址 */
    private String distributddress;

    /** 装机地址 */
    private String installaddress;

    /** 机主姓名 */
    private String owner;

    /** 机主电话 */
    private String ownermobi;

    /** 身份证号码 */
    private String idcard;

    /** 订单来源 */
    private String origin;

    /** 套餐id */
    private Long packageid;

    /** 套餐名称 */
    private String packagename;

    /** 0半月 1全月 2按流量 */
    private String firstmonthfeetype;

    /** 发票 */
    private String invoice;

    /** 发票金额 */
    private Double invoiceprice;

    /** 佣金 */
    private Double commision;

    /** 预存款 */
    private Double predeposit;

    /** 会员备注 */
    private String memberRemark;

    /** 备注 */
    private String remark;

    /** 办理完成时间 */
    private Date finishtime;

    /** 结算状态 */
    private String settlestatus;

    /** 入网资料 */
    private String netusername;

    /** 入网身份证号码 */
    private String netcardid;

    /** ord_order.netimg1 */
    private String netimg1;

    /** ord_order.netimg2 */
    private String netimg2;

    /** ord_order.netimg3 */
    private String netimg3;

    private static final long serialVersionUID = 1L;

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid == null ? null : khid.trim();
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getAdslaccount() {
        return adslaccount;
    }

    public void setAdslaccount(String adslaccount) {
        this.adslaccount = adslaccount == null ? null : adslaccount.trim();
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }

    public Double getShipAmount() {
        return shipAmount;
    }

    public void setShipAmount(Double shipAmount) {
        this.shipAmount = shipAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid == null ? null : isPaid.trim();
    }

    public Double getPayFee() {
        return payFee;
    }

    public void setPayFee(Double payFee) {
        this.payFee = payFee;
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum == null ? null : paymentNum.trim();
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getContractmobile() {
        return contractmobile;
    }

    public void setContractmobile(String contractmobile) {
        this.contractmobile = contractmobile == null ? null : contractmobile.trim();
    }

    public String getDistributddress() {
        return distributddress;
    }

    public void setDistributddress(String distributddress) {
        this.distributddress = distributddress == null ? null : distributddress.trim();
    }

    public String getInstalladdress() {
        return installaddress;
    }

    public void setInstalladdress(String installaddress) {
        this.installaddress = installaddress == null ? null : installaddress.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getOwnermobi() {
        return ownermobi;
    }

    public void setOwnermobi(String ownermobi) {
        this.ownermobi = ownermobi == null ? null : ownermobi.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public Long getPackageid() {
        return packageid;
    }

    public void setPackageid(Long packageid) {
        this.packageid = packageid;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename == null ? null : packagename.trim();
    }

    public String getFirstmonthfeetype() {
        return firstmonthfeetype;
    }

    public void setFirstmonthfeetype(String firstmonthfeetype) {
        this.firstmonthfeetype = firstmonthfeetype == null ? null : firstmonthfeetype.trim();
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice == null ? null : invoice.trim();
    }

    public Double getInvoiceprice() {
        return invoiceprice;
    }

    public void setInvoiceprice(Double invoiceprice) {
        this.invoiceprice = invoiceprice;
    }

    public Double getCommision() {
        return commision;
    }

    public void setCommision(Double commision) {
        this.commision = commision;
    }

    public Double getPredeposit() {
        return predeposit;
    }

    public void setPredeposit(Double predeposit) {
        this.predeposit = predeposit;
    }

    public String getMemberRemark() {
        return memberRemark;
    }

    public void setMemberRemark(String memberRemark) {
        this.memberRemark = memberRemark == null ? null : memberRemark.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public String getSettlestatus() {
        return settlestatus;
    }

    public void setSettlestatus(String settlestatus) {
        this.settlestatus = settlestatus == null ? null : settlestatus.trim();
    }

    public String getNetusername() {
        return netusername;
    }

    public void setNetusername(String netusername) {
        this.netusername = netusername == null ? null : netusername.trim();
    }

    public String getNetcardid() {
        return netcardid;
    }

    public void setNetcardid(String netcardid) {
        this.netcardid = netcardid == null ? null : netcardid.trim();
    }

    public String getNetimg1() {
        return netimg1;
    }

    public void setNetimg1(String netimg1) {
        this.netimg1 = netimg1 == null ? null : netimg1.trim();
    }

    public String getNetimg2() {
        return netimg2;
    }

    public void setNetimg2(String netimg2) {
        this.netimg2 = netimg2 == null ? null : netimg2.trim();
    }

    public String getNetimg3() {
        return netimg3;
    }

    public void setNetimg3(String netimg3) {
        this.netimg3 = netimg3 == null ? null : netimg3.trim();
    }


//    @Override
//    public String toString() {
//        return "Order{" +
//                "khid='" + khid + '\'' +
//                ", memberId=" + memberId +
//                ", orderNum='" + orderNum + '\'' +
//                ", adslaccount='" + adslaccount + '\'' +
//                ", orderstatus='" + orderstatus + '\'' +
//                ", amount=" + amount +
//                ", productAmount=" + productAmount +
//                ", shipAmount=" + shipAmount +
//                ", title='" + title + '\'' +
//                ", weight=" + weight +
//                ", quantity=" + quantity +
//                ", paytype='" + paytype + '\'' +
//                ", isPaid='" + isPaid + '\'' +
//                ", payFee=" + payFee +
//                ", paymentNum='" + paymentNum + '\'' +
//                ", paymentTime=" + paymentTime +
//                ", linkman='" + linkman + '\'' +
//                ", contractmobile='" + contractmobile + '\'' +
//                ", distributddress='" + distributddress + '\'' +
//                ", installaddress='" + installaddress + '\'' +
//                ", owner='" + owner + '\'' +
//                ", ownermobi='" + ownermobi + '\'' +
//                ", idcard='" + idcard + '\'' +
//                ", origin='" + origin + '\'' +
//                ", packageid=" + packageid +
//                ", packagename='" + packagename + '\'' +
//                ", firstmonthfeetype='" + firstmonthfeetype + '\'' +
//                ", invoice='" + invoice + '\'' +
//                ", invoiceprice=" + invoiceprice +
//                ", commision=" + commision +
//                ", predeposit=" + predeposit +
//                ", memberRemark='" + memberRemark + '\'' +
//                ", remark='" + remark + '\'' +
//                ", finishtime=" + finishtime +
//                ", settlestatus='" + settlestatus + '\'' +
//                ", netusername='" + netusername + '\'' +
//                ", netcardid='" + netcardid + '\'' +
//                ", netimg1='" + netimg1 + '\'' +
//                ", netimg2='" + netimg2 + '\'' +
//                ", netimg3='" + netimg3 + '\'' +
//                '}';
//    }
}