package net.jeeshop.biz.product.model;

import net.jeeshop.biz.base.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class ProductInfo extends BaseModel implements Serializable {
    /** 商品分类ID */
    private Long categoryId;

    /** 分类名称 */
    private String categoryName;

    /** 商品名称 */
    private String productName;

    /** 商品简述 */
    private String shortDescription;

    /** 描述信息 */
    private String description;

    /** 是否新品 */
    private String isNew;

    /** 是否热销 */
    private String isHot;

    /** 商品内容 */
    private String procontent;

    /** 页面标题,商品名称+规格名称 */
    private String title;

    /** 商品关键字，逗号分隔 */
    private String keywords;

    /** 标签 */
    private Long brandId;

    /** 月资费 */
    private Double monthPrice;

    /** 原价 */
    private Double oldPrice;

    /** 销售价 */
    private Double sellPrice;

    /** 商品状态 1正常 0已删除 2下架 3申请下架 */
    private String productStatus;

    /** 上架时间 */
    private Date upTime;

    /** pro_product_info.down_time */
    private Date downTime;

    /** 库存 */
    private Integer storeNums;

    /** 原图 */
    private String img;

    /** 宣传图 */
    private String adImg;

    /** 计量单位 */
    private String unit;

    /** 浏览次数 */
    private Integer visit;

    /** 排序 */
    private Short sort;

    /** 评价数 */
    private Integer comments;

    /** 销量 */
    private Integer sale;

    /** 评分总数 */
    private Integer grade;

    /** 卖家id */
    private String khid;

    /** 价格前缀 */
    private String prestr;

    /** 是否结算 0否，1是 */
    private String toBalance;

    /** 是否发票 0不 1是 */
    private String receipt;

    /** 关联商品 */
    private Integer linkproduct;

    /** 发票价格 */
    private Integer receiptPrice;

    /** 是否已结算 */
    private Integer issettle;

    /** 运费 */
    private Double freight;

    /** 流程id */
    private Long processid;

    /** 佣金 */
    private Double commission;

    /** 类型 1新装 2续费 3普通 */
    private String type;

    /** 序列化存储规格,key值为规则ID，value为此商品具有的规格值 */
    private String specArray;

    public String getSelectArray() {
        return selectArray;
    }

    public void setSelectArray(String selectArray) {
        this.selectArray = selectArray;
    }

    private String selectArray;

    private static final long serialVersionUID = 1L;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription == null ? null : shortDescription.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew == null ? null : isNew.trim();
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot == null ? null : isHot.trim();
    }

    public String getProcontent() {
        return procontent;
    }

    public void setProcontent(String procontent) {
        this.procontent = procontent == null ? null : procontent.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Double getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(Double monthPrice) {
        this.monthPrice = monthPrice;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus == null ? null : productStatus.trim();
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    public Integer getStoreNums() {
        return storeNums;
    }

    public void setStoreNums(Integer storeNums) {
        this.storeNums = storeNums;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getAdImg() {
        return adImg;
    }

    public void setAdImg(String adImg) {
        this.adImg = adImg == null ? null : adImg.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getKhid() {
        return khid;
    }

    public void setKhid(String khid) {
        this.khid = khid == null ? null : khid.trim();
    }

    public String getPrestr() {
        return prestr;
    }

    public void setPrestr(String prestr) {
        this.prestr = prestr == null ? null : prestr.trim();
    }

    public String getToBalance() {
        return toBalance;
    }

    public void setToBalance(String toBalance) {
        this.toBalance = toBalance == null ? null : toBalance.trim();
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt == null ? null : receipt.trim();
    }

    public Integer getLinkproduct() {
        return linkproduct;
    }

    public void setLinkproduct(Integer linkproduct) {
        this.linkproduct = linkproduct;
    }

    public Integer getReceiptPrice() {
        return receiptPrice;
    }

    public void setReceiptPrice(Integer receiptPrice) {
        this.receiptPrice = receiptPrice;
    }

    public Integer getIssettle() {
        return issettle;
    }

    public void setIssettle(Integer issettle) {
        this.issettle = issettle;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Long getProcessid() {
        return processid;
    }

    public void setProcessid(Long processid) {
        this.processid = processid;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSpecArray() {
        return specArray;
    }

    public void setSpecArray(String specArray) {
        this.specArray = specArray == null ? null : specArray.trim();
    }
}