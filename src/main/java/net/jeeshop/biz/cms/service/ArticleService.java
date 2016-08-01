package net.jeeshop.biz.cms.service;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.cms.bean.ArticleBean;
import net.jeeshop.biz.cms.client.ArticleMapper;
import net.jeeshop.biz.cms.client.ArticleMapperExt;
import net.jeeshop.biz.cms.model.Article;
import net.jeeshop.biz.cms.model.ArticleExample;
import net.jeeshop.biz.cms.model.Notice;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pingge on 2016/1/15.
 */
@Service
public class ArticleService extends BaseService<Article, ArticleExample> {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleMapperExt articleMapperExt;

    @Override
    protected BaseMapper<Article, ArticleExample> getMapper() {
        return articleMapper;
    }
    /**
     * 分页查询数据
     *
     * @param article
     * @return
     */
    public PageBean<ArticleBean> selectPageBeanList(final ArticleBean article, PageQueryBean pageQueryBean){
        if (StringUtils.isNotBlank(article.getTitle())){
            article.setTitle("%"+article.getTitle().trim()+"%");
        }else{
        	article.setTitle(null);
        }
        if (StringUtils.isNotBlank(article.getCode())){
            article.setCode("%"+article.getCode().trim()+"%");
        }else{
        	article.setCode(null);
        }
        if (article.getCategoryId() != null && article.getCategoryId() != 0L){
            article.setCategoryId(article.getCategoryId());
        }else{
        	article.setCategoryId(null);
        }
        PageBean<ArticleBean> pagerModel = super.executePageQuery(new PageQueryExecutor<ArticleBean>() {
            @Override
            public List<ArticleBean> executeQuery() {
                return articleMapperExt.selectArticleBeanList(article);
            }
        }, pageQueryBean);
        return pagerModel;
    }

    /**
     * 根据code检索
     *
     * @param code
     * @return
     */
    public Article selectByCode(String code) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(StringUtils.trimToEmpty(code));

        List<Article> catalogs = articleMapper.selectByExample(example);
        return catalogs.size() > 0 ? catalogs.get(0) : null;
    }
	/**
	 * 更新阅读次数
	 * @param notice
	 */
	public void updateReadCount(Article article) {
		Long count = article.getReadCount();
		if(count == null)
			count = 0L;
		count +=1;
		article.setReadCount(count);
		articleMapper.updateByPrimaryKey(article);
	}
}
