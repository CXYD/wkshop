package net.jeeshop.biz.system.model;

import java.io.Serializable;
import java.util.Date;
import net.jeeshop.biz.base.model.BaseModel;
import net.jeeshop.biz.system.enums.LogType;

public class SystemLog extends BaseModel implements Serializable {
    /** æ ‡é¢˜ */
    private String title;

    /** æ—¥å¿—å†…å®¹ */
    private String content;

    /** æ—¥å¿—ç±»åž‹ */
    private LogType logType;

    /** æ—¥å¿—ç”¨æˆ· */
    private String account;

    /** sys_system_log.login_ip */
    private String loginIp;

    /** ç™»å½•åŒºåŸŸ */
    private String loginArea;

    /** æ—¥å¿—è®°å½•æ—¶é—´ */
    private Date logTime;

    private static final long serialVersionUID = 1L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public String getLoginArea() {
        return loginArea;
    }

    public void setLoginArea(String loginArea) {
        this.loginArea = loginArea == null ? null : loginArea.trim();
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}