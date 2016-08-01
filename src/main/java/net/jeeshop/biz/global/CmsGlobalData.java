package net.jeeshop.biz.global;

import com.google.common.collect.Lists;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.cms.bean.ArticleBean;
import net.jeeshop.biz.cms.model.*;
import net.jeeshop.biz.cms.service.*;
import net.jeeshop.biz.global.bean.ArticleCategoryWrapper;
import net.jeeshop.core.DataGetter;
import net.jeeshop.core.util.CollectionUtils;
import net.jeeshop.core.util.transform.Transform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Dylan.Ding
 * @date 2016-04-04 16-56
 */
@Component
public class CmsGlobalData {
    @Autowired
    GlobalDataManager globalDataManager;

    @Autowired
    private FriendLinkService friendLinkService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private HotQueryService hotQueryService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleCategoryService articleCategoryService;

    private int pageSize = 100;//最多查询100条数据

    @PostConstruct
    public void init() {

        //cms data getter
        //friend link list
        globalDataManager.addDataGetter(new DataGetter<List>("cms.friendLinkList", DataGetter.DataType.LIST) {
            @Override
            public List<FriendLink> getData() {
                FriendLinkExample example = new FriendLinkExample();
                example.setOrderByClause("ordinal");
                return friendLinkService.selectPageList(example,
                        new PageQueryBean(pageSize)).getList();
            }
        });
        //notice list
        globalDataManager.addDataGetter(new DataGetter<List>("cms.noticeList", DataGetter.DataType.LIST) {
            @Override
            public List<Notice> getData() {
                NoticeExample example = new NoticeExample();
                NoticeExample.Criteria criteria = example.createCriteria();
                criteria.andIsValidEqualTo(true);
                example.setOrderByClause("create_time desc");
                return noticeService.selectPageList(example,
                        new PageQueryBean(10)).getList();
            }
        });
        //article category list
        globalDataManager.addDataGetter(new DataGetter<List>("cms.articleCategoryList", DataGetter.DataType.LIST) {
            @Override
            public List getData() {
                ArticleCategoryExample example = new ArticleCategoryExample();
                ArticleCategoryExample.Criteria criteria = example.createCriteria();
                criteria.andParentIdEqualTo(0L);//只查一级分类
                List<ArticleCategory> articleCategories = articleCategoryService.selectByExample(example);
                List<ArticleCategoryWrapper> categoryWrappers = CollectionUtils.convert(articleCategories, new Transform<ArticleCategory, ArticleCategoryWrapper>() {
                    @Override
                    public ArticleCategoryWrapper convert(ArticleCategory articleCategory) {
                        ArticleCategoryWrapper wrapper = new ArticleCategoryWrapper(articleCategory);
                        ArticleBean articleBean = new ArticleBean();
                        articleBean.setCategoryId(articleCategory.getId());
                        wrapper.setArticles(articleService.selectPageBeanList(articleBean, new PageQueryBean(pageSize)).getList());
                        return wrapper;
                    }

                    @Override
                    public boolean apply(ArticleCategory articleCategory) {
                        return true;
                    }
                });
                return categoryWrappers;
            }
        });
        //index image list
        globalDataManager.addDataGetter(new DataGetter<List>("cms.indexImages", DataGetter.DataType.LIST) {
            @Override
            public List getData() {
                return Lists.newArrayList();
            }
        });
        //hot query list
        globalDataManager.addDataGetter(new DataGetter<List>("cms.hotQueryList", DataGetter.DataType.LIST) {
            @Override
            public List getData() {
                return Lists.newArrayList();
            }
        });
    }
}
