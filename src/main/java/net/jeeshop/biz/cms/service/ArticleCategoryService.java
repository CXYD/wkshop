package net.jeeshop.biz.cms.service;

import com.google.common.collect.Lists;
import net.jeeshop.biz.cms.bean.ArticleCategoryBean;
import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.cms.client.ArticleCategoryMapper;
import net.jeeshop.biz.cms.model.ArticleCategory;
import net.jeeshop.biz.cms.model.ArticleCategoryExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author dylan
* @date 15/8/18 21:19
* Email: dinguangx@163.com
*/
@Service
public class ArticleCategoryService extends BaseService<ArticleCategory, ArticleCategoryExample> {

    @Resource
    ArticleCategoryMapper articleCatagoryMapper;

    @Override
    protected BaseMapper getMapper() {
        return articleCatagoryMapper;
    }

    /**
     * 分页查询数据
     *
     * @param articleCatalog
     * @return
     */
    public PageBean<ArticleCategory> selectPageList(ArticleCategory articleCatalog, PageQueryBean pageQueryBean) {
        ArticleCategoryExample example = getExampleWithOrder();
        ArticleCategoryExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(articleCatalog.getCategoryName())) {
            criteria.andCategoryNameEqualTo(StringUtils.trimToEmpty(articleCatalog.getCategoryName()));
        }
        PageBean<ArticleCategory> pagerModel = super.selectPageList(example, pageQueryBean);
        return pagerModel;
    }

    /**
     * 获取一级目录分类
     *
     * @return
     */
    public List<ArticleCategoryBean> loadRoot() {
        ArticleCategoryExample example = getExampleWithOrder();
        ArticleCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(0L);

        List<ArticleCategory> rootCatalogs = articleCatagoryMapper.selectByExample(example);
        List<ArticleCategoryBean> result = convertList(rootCatalogs);
        for (ArticleCategoryBean catalogBean : result) {
            loadChildrenRecursive(catalogBean);
        }
        return result;
    }

    private ArticleCategoryExample getExampleWithOrder() {
        ArticleCategoryExample example = new ArticleCategoryExample();
        example.setOrderByClause("ordinal asc");
        return example;
    }

    private List<ArticleCategoryBean> convertList(List<ArticleCategory> articleCatalogs) {
        List<ArticleCategoryBean> result = Lists.newArrayList();
        for (ArticleCategory catalog : articleCatalogs) {
            result.add(new ArticleCategoryBean(catalog));
        }
        return result;
    }

    /**
     * 加载指定节点下的全部子节点
     *
     * @param item
     */
    private void loadChildrenRecursive(ArticleCategoryBean item) {
        ArticleCategoryExample example = getExampleWithOrder();
        ArticleCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(item.getId());
        item.setChildren(convertList(articleCatagoryMapper.selectByExample(example)));
        if (item.getChildren() != null && item.getChildren().size() > 0) {
            for (ArticleCategoryBean bean : item.getChildren()) {
                loadChildrenRecursive(bean);
            }
        }
    }

    /**
     * 根据code检索
     *
     * @param code
     * @return
     */
    public ArticleCategory selectByCode(String code) {
        ArticleCategoryExample example = new ArticleCategoryExample();
        ArticleCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryCodeEqualTo(StringUtils.trimToEmpty(code));

        List<ArticleCategory> catalogs = articleCatagoryMapper.selectByExample(example);
        return catalogs.size() > 1 ? catalogs.get(0) : null;
    }

    /**
     * 刷新文章分类的缓存
     */
    public void refreshCache() {
        //TODO
    }
}
