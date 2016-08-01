package net.jeeshop.biz.base.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.model.BaseModel;
import net.jeeshop.biz.shop.model.ShopInfo;
import net.jeeshop.core.exception.JShopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public abstract class BaseService<E extends BaseModel, Example>
{
    protected Logger logger = LoggerFactory.getLogger(getClass());
    abstract protected BaseMapper<E, Example> getMapper();

    @Transactional
    public long insert(E entity)
    {
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        return getMapper().insertSelective(entity);
    }

    @Transactional
    public int deleteById(long id)
    {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Transactional
    public int deletes(Long[] ids) 
    {
        int cnt = 0;
        for (Long id : ids) {
            int i = deleteById(id);
            cnt += i;
        }
        return cnt;
    }

    @Transactional
    public int update(E entity) {
        entity.setUpdateTime(new Date());
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    public E selectById(long id) {
        return getMapper().selectByPrimaryKey(id);
    }

    /**
     * 分页查询数据
     *
     * @param example
     * @param pageQueryBean
     * @return
     */
    public PageBean<E> selectPageList(final Example example, PageQueryBean pageQueryBean) 
    {
        return executePageQuery(new PageQueryExecutor<E>() {
            @Override
            public List<E> executeQuery() {
                return getMapper().selectByExample(example);
            }
        }, pageQueryBean);
    }

    /**
     * 执行分页查询
     * @param executor
     * @param pageQueryBean
     * @param <T>
     * @return
     */
    protected <T> PageBean<T> executePageQuery(PageQueryExecutor<T> executor, PageQueryBean pageQueryBean)
    {
        initPageHelper(pageQueryBean);
        List<T> datas = executor.executeQuery();
        PageBean<T> pagerModel = new PageBean<T>();
        pagerModel.setList(datas);
        pagerModel.setRecordsTotal(((Page) datas).getTotal());
        pagerModel.setRecordsFiltered(pagerModel.getRecordsTotal());
        logger.debug("返回pagebean：{}",pagerModel);
        return pagerModel;
    }



    /**
     * 分页查询实际执行者
     * @param <T>
     */
    protected static interface PageQueryExecutor<T>
    {
        /**
         * 执行查询动作
         * @return
         */
        public List<T> executeQuery() ;
    }

    protected void initPageHelper(PageQueryBean pageQueryBean)
    {
        if (pageQueryBean.getLength() == PageQueryBean.UNLIMIT_LENGTH) 
        {
            return;
        }
        if (pageQueryBean.getLength() <= 0) 
        {
            pageQueryBean.setLength(PageQueryBean.DEFAULT_LENGTH);
        }
        
        PageHelper.startPage(pageQueryBean.getStart() / pageQueryBean.getLength() + 1, pageQueryBean.getLength());
    }

    public List<E> selectByExample(Example example) 
    {
        return getMapper().selectByExample(example);
    }

    public E selectUniqueByExample(Example example)
    {
        List<E> list = getMapper().selectByExample(example);
        if (list == null || list.size() == 0) 
        {
            return null;
        }
        if (list.size() > 1) 
        {
            throw new JShopException("result size more than one.");
        }
        return list.get(0);
    }
    
}
