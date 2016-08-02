package net.jeeshop.core.cache;

import net.jeeshop.biz.system.bean.SystemSettingBean;
import net.jeeshop.biz.system.model.SystemSetting;
import net.jeeshop.biz.system.model.SystemSettingExample;
import net.jeeshop.biz.system.service.SystemSettingService;
import net.jeeshop.core.SystemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by dylan on 15-2-7.
 */
@Component
public class CacheInitiator implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * manage后台
     */
//    @Autowired
//    private KeyvalueService keyvalueService;
    @Autowired
    private SystemSettingService systemSettingService;
//    @Autowired
//    private NewsService newsService;
//    @Autowired
//    private CatalogService catalogService;
//    @Autowired
//    private IndexImgService indexImgService;
//    @Autowired
//    private NavigationService navigationService;
//    @Autowired
//    private AttributeService attributeService;
//    @Autowired
//    private PayService payService;
//    @Autowired
//    private CommentTypeService commentTypeService;
//    @Autowired
//    private AreaService areaService;
//    @Autowired
//    private ExpressService expressService;
//    @Autowired
//    private AdvertService advertService;
//    @Autowired
//    private NotifyTemplateService notifyTemplateService;
//    @Autowired
//    //	private OssService ossService;
//    private OrderService orderService;
//    @Autowired
//    private CommentService commentService;
//    @Autowired
//    private AccountRankService accountRankService;
//    @Autowired
//    private ActivityService activityService;
//    @Autowired
//    private HotqueryService hotqueryService;


    @Override
    public void afterPropertiesSet() throws Exception {
        loadSystemSettingCache();
    }

    public void loadSystemSettingCache() {
        logger.info("加载系统配置缓存项...");
        SystemSetting systemSetting = systemSettingService.selectUniqueByExample(new SystemSettingExample());
        SystemSettingBean systemSettingBean = new SystemSettingBean();
        BeanUtils.copyProperties(systemSetting, systemSettingBean);
        SystemManager.getInstance().setSystemSetting(systemSettingBean);
    }
}
