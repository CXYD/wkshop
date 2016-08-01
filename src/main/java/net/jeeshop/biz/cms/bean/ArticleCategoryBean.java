package net.jeeshop.biz.cms.bean;

import java.util.List;

import net.jeeshop.biz.cms.model.ArticleCategory;
import net.jeeshop.core.util.BeanUtilsExt;

/**
* Created by dylan on 15-9-5.
*/
public class ArticleCategoryBean extends ArticleCategory {
	private static final long serialVersionUID = 1600799011967259967L;

	private List<ArticleCategoryBean> children;
	
	public ArticleCategoryBean() {
    }

    public ArticleCategoryBean(ArticleCategory catalog) {
        convertFrom(catalog);
    }

    public List<ArticleCategoryBean> getChildren() {
        return children;
    }

    public void setChildren(List<ArticleCategoryBean> children) {
        this.children = children;
    }

    public void convertFrom(ArticleCategory catalog) {
        BeanUtilsExt.copyProperties(this, catalog);
    }
}
