package net.jeeshop.biz.global.bean;

import com.google.common.collect.Lists;
import net.jeeshop.biz.cms.bean.ArticleBean;
import net.jeeshop.biz.cms.model.ArticleCategory;
import net.jeeshop.core.util.BeanUtilsExt;

import java.util.List;

/**
 * @author Dylan.Ding
 * @date 2016-04-04 17-16
 */
public class ArticleCategoryWrapper extends ArticleCategory {

    public ArticleCategoryWrapper(ArticleCategory articleCategory) {
        BeanUtilsExt.copyProperties(this, articleCategory);
    }

    private List<ArticleBean> articles = Lists.newArrayList();

    public List<ArticleBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleBean> articles) {
        this.articles = articles;
    }
}
