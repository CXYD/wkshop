package net.jeeshop.biz.cms.bean;


import net.jeeshop.biz.cms.model.Article;

/**
 * Created by pingge on 2016/1/15.
 */
public class ArticleBean extends Article {
    private static final long serialVersionUID = 8350875921506511461L;
    private String categoryName;//文章分类


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
