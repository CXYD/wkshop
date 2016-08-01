package net.jeeshop.core;

import net.jeeshop.biz.cms.bean.ArticleCategoryBean;
import net.jeeshop.biz.system.bean.SystemSettingBean;
import net.jeeshop.core.cache.CacheProvider;
import net.jeeshop.core.cache.SimpleCacheProvider;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;


/**
 * 系统管理类.
 * 1、负责管理system.properties的东东
 * 2、负责缓存管理
 * @author huangf
 */
public class SystemManager {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SystemManager.class);
    @Autowired
    @Qualifier("appProperties")
	private Properties appProperties;
    private static CacheProvider cacheProvider = new SimpleCacheProvider();
	private static SystemManager instance;

    @PostConstruct
    public void afterPropertiesSet(){
        instance = this;
    }
	private static Map<String,String> manageExpressMap = new HashMap<String, String>();//后台发货页面物流公司列表

    public static SystemManager getInstance(){
        return instance;
    }

    static {
		init();
	}
	private static void init(){
        manageExpressMap.put("shunfeng", "顺风快递");
        manageExpressMap.put("ems", "EMS");
        manageExpressMap.put("shentong", "申通E物流");
        manageExpressMap.put("yuantong", "圆通速递");
        manageExpressMap.put("zhongtong", "中通速递");
        manageExpressMap.put("zhaijisong", "宅急送");
        manageExpressMap.put("yunda", "韵达快运");
        manageExpressMap.put("tiantian", "天天快递");
        manageExpressMap.put("lianbangkuaidi", "联邦快递");
        manageExpressMap.put("huitongkuaidi", "汇通快运");
	}
	
	public String getProperty(String key){
		return appProperties.getProperty(key);
	}
	
	private Random random = new Random();
	
	/**
	 * 随机从图集里面选取一张图片
	 * @return
	 */
	public String getImageRandom(){
        SystemSettingBean systemSetting = getSystemSetting();
		if(systemSetting==null || systemSetting.getImagesList()==null || systemSetting.getImagesList().size()==0){
			logger.error("系统未设置图集，但广告位却设置了图集优先显示。请管理员立刻设置图集。");
			return null;
		}
		
		int n = random.nextInt(systemSetting.getImagesList().size());
		
		return systemSetting.getImageRootPath()+systemSetting.getImagesList().get(n);
	}

    //应用缓存管理

    public CacheProvider getCacheProvider() {
        return cacheProvider;
    }

    public void setCacheProvider(CacheProvider cacheProvider) {
        SystemManager.cacheProvider = cacheProvider;
    }

    private static String buildKey(String key) {
        return "SystemManager." + StringUtils.trimToEmpty(key);
    }
    private static void putCacheObject(String key, Serializable cacheObject){
        String key1 = buildKey(key);
        if(cacheObject == null){
            cacheProvider.remove(key1);
        } else {
            cacheProvider.put(key1, cacheObject);
        }
    }
    private static <T extends Serializable> T getCacheObject(String key){
        return (T)cacheProvider.get(buildKey(key));
    }

    //系统设置
    public SystemSettingBean getSystemSetting() {
        return getCacheObject("systemSetting");
    }

    public void setSystemSetting(SystemSettingBean systemSetting) {
        putCacheObject("systemSetting", systemSetting);
    }

    /**
     * 文章目录列表
     * @return
     */
    public List<ArticleCategoryBean> getArticleCatalogs() {
    //    return getCacheObject("articleCatalogs");
    	return null;
    }

    public void setArticleCatalogs(List<ArticleCategoryBean> catalogsArticle) {
        putCacheObject("articleCatalogs", (Serializable)catalogsArticle);
    }

}
