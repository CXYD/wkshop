package net.jeeshop.biz.cms.client;

import net.jeeshop.biz.cms.bean.ArticleBean;

import java.util.List;

public interface ArticleMapperExt  {
    List<ArticleBean> selectArticleBeanList(ArticleBean example);

}