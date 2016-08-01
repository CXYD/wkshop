package net.jeeshop.biz.system.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.member.model.Member;
import net.jeeshop.biz.system.enums.LogType;
import net.jeeshop.biz.system.client.SystemLogMapper;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.biz.system.model.SystemLog;
import net.jeeshop.biz.system.model.SystemLogExample;
import net.jeeshop.core.util.AddressUtils;
import net.jeeshop.core.util.Constants;
import net.jeeshop.web.util.LoginUserHolder;
import net.jeeshop.web.util.RequestHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author dinguangx@163.com
 * @date 2015-12-22 23:14
 */
@Service
public class SystemLogService extends BaseService<SystemLog, SystemLogExample> {
    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    protected BaseMapper<SystemLog, SystemLogExample> getMapper() {
        return systemLogMapper;
    }

    /**
     */
    @Transactional
    public void newLog(String title, String content, LogType logType) {
        SysUser currentUser = LoginUserHolder.getLoginUser();

        SystemLog systemlog = new SystemLog();
        systemlog.setTitle(title);
        systemlog.setContent(content);
        systemlog.setAccount(currentUser.getUsername());
        systemlog.setLogType(logType);
        systemlog.setCreateTime(new Date());
        systemlog.setUpdateTime(new Date());
        systemlog.setLogTime(new Date());
        systemlog.setLoginIp(AddressUtils.getIp(RequestHolder.getRequest()));

        String address = null;
        if (!systemlog.getLoginIp().equals("127.0.0.1") && !systemlog.getLoginIp().equals("localhost")) {
            //获取指定IP的区域位置
            try {
                address = AddressUtils.getAddresses("ip=" + systemlog.getLoginIp(), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            systemlog.setLoginArea(address);

        }

        this.insert(systemlog);
    }


    /**
     * 前台日志记录
     */
    @Transactional
    public void newFrontLog(String title, String content, LogType logType) {
        Member member = LoginUserHolder.getLoginMember();

        String account = member == null ? "NOBODY" : member.getUsername();

        SystemLog systemlog = new SystemLog();
        systemlog.setTitle(title);
        systemlog.setContent(content);
        systemlog.setAccount(account);
        systemlog.setLogType(logType);
        systemlog.setCreateTime(new Date());
        systemlog.setUpdateTime(new Date());
        systemlog.setLogTime(new Date());
//        systemlog.setLoginIp(AddressUtils.getIp(RequestHolder.getRequest()));
        systemlog.setLoginIp("219.136.134.157");

        String address = null;
        if (!systemlog.getLoginIp().equals("127.0.0.1") && !systemlog.getLoginIp().equals("localhost")) {
            //获取指定IP的区域位置
            try {
                logger.debug("当前IP:"+systemlog.getLoginIp());
                address = AddressUtils.getAddresses("ip=" + systemlog.getLoginIp(), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            systemlog.setLoginArea(address);

        }

        this.insert(systemlog);
    }
}
