package net.jeeshop.biz.system.model;

import java.io.Serializable;
import net.jeeshop.biz.base.model.BaseModel;

public class SystemSetting extends BaseModel implements Serializable {
    /** ç³»ç»Ÿä»£å�· */
    private String systemCode;

    /** åº”ç”¨å��ç§° */
    private String appName;

    /** é—¨æˆ·é¡µé�¢ */
    private String website;

    /** LOGO */
    private String logo;

    /** æ ‡é¢˜ */
    private String title;

    /** æ��è¿°ä¿¡æ�¯ */
    private String description;

    /** å…³é”®å­— */
    private String keywords;

    /** å›¾æ ‡ */
    private String shortcutIcon;

    /** è�”ç³»åœ°å�€ */
    private String address;

    /** è�”ç³»ç”µè¯� */
    private String telphone;

    /** é‚®ç®± */
    private String email;

    /** å¤‡æ¡ˆå�· */
    private String icp;

    /** æ˜¯å�¦å¼€æ”¾,1-æ˜¯0-å�¦ */
    private Boolean isOpen;

    /** ç½‘ç«™å…³é—­æ��ç¤ºè¯­ */
    private String closeMsg;

    /** å›¾ç‰‡æ ¹è·¯å¾„ */
    private String imageRootPath;

    /** é»˜è®¤äº§å“�å›¾ç‰‡ */
    private String defaultProductImg;

    /** æ ·å¼� */
    private String style;

    /** ç³»ç»Ÿç‰ˆæœ¬å�· */
    private String version;

    /** ç»Ÿè®¡ä»£ç � */
    private String statisticsCode;

    /** æ˜¯å�¦å¼€æ”¾å“�åº”å¼�,1-æ˜¯0-å�¦ */
    private String isResponsive;

    /** å›¾ç‰‡é›† */
    private String images;

    private static final long serialVersionUID = 1L;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getShortcutIcon() {
        return shortcutIcon;
    }

    public void setShortcutIcon(String shortcutIcon) {
        this.shortcutIcon = shortcutIcon == null ? null : shortcutIcon.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp == null ? null : icp.trim();
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getCloseMsg() {
        return closeMsg;
    }

    public void setCloseMsg(String closeMsg) {
        this.closeMsg = closeMsg == null ? null : closeMsg.trim();
    }

    public String getImageRootPath() {
        return imageRootPath;
    }

    public void setImageRootPath(String imageRootPath) {
        this.imageRootPath = imageRootPath == null ? null : imageRootPath.trim();
    }

    public String getDefaultProductImg() {
        return defaultProductImg;
    }

    public void setDefaultProductImg(String defaultProductImg) {
        this.defaultProductImg = defaultProductImg == null ? null : defaultProductImg.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getStatisticsCode() {
        return statisticsCode;
    }

    public void setStatisticsCode(String statisticsCode) {
        this.statisticsCode = statisticsCode == null ? null : statisticsCode.trim();
    }

    public String getIsResponsive() {
        return isResponsive;
    }

    public void setIsResponsive(String isResponsive) {
        this.isResponsive = isResponsive == null ? null : isResponsive.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }
}