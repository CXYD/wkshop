package net.jeeshop.biz.product.service;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.bean.ProductClassBean;
import net.jeeshop.biz.product.client.ProductClassMapper;
import net.jeeshop.biz.product.client.ProductClassMapperExt;
import net.jeeshop.biz.product.model.ProductClass;
import net.jeeshop.biz.product.model.ProductClassExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/1
 * Time: 16:29
 */
@Service
public class ProductClassService extends BaseService<ProductClass,ProductClassExample>{

    @Autowired
    private ProductClassMapper productClassMapper;

    @Autowired
    private ProductClassMapperExt productClassMapperExt;

    @Override
    protected BaseMapper<ProductClass, ProductClassExample> getMapper() {
        return productClassMapper;
    }

    public PageBean<ProductClassBean> selectPageBean(final ProductClassExample params, PageQueryBean pageQueryBean) {



        return executePageQuery(new PageQueryExecutor<ProductClassBean>() {
            @Override
            public List<ProductClassBean> executeQuery() {

                return productClassMapperExt.selectByParams(params);
            }
        }, pageQueryBean);
    }


    /**
     * 分页查询数据
     *
     * @param
     * @return
     */
    public PageBean<ProductClass> selectPageList(ProductClass productClass, PageQueryBean pageQueryBean) {
        ProductClassExample example = new ProductClassExample();
        ProductClassExample.Criteria criteria = example.createCriteria();

        PageBean<ProductClass> pagerModel = super.selectPageList(example, pageQueryBean);
        return pagerModel;
    }

}
