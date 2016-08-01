package net.jeeshop.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.jeeshop.core.exception.JShopException;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 系统配置加载监听器
 * 
 * @author huangf
 * 
 */
public class SystemListener implements ServletContextListener {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SystemListener.class);
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
//			SystemManager.getInstance();

			WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
//			FrontCache frontCache = (FrontCache) app.getBean("frontCache");
//			ManageCache manageCache = (ManageCache) app.getBean("manageCache");
//			frontCache.loadAllCache();
//			manageCache.loadAllCache();
			
//			TaskManager taskManager = (TaskManager) app.getBean("taskManager");
//			taskManager.start();
		} catch (Throwable e) {
			logger.error("System load faild!"+e.getMessage());
            throw new JShopException("系统初始化失败！");
		}
	}

}
