package net.jeeshop.biz.product.service;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.client.ProductLabelMapper;
import net.jeeshop.biz.product.client.ProductLabelMapperExt;
import net.jeeshop.biz.product.model.ProductLabel;
import net.jeeshop.biz.product.model.ProductLabelExample;
import net.jeeshop.biz.system.model.SysUser;
import net.jeeshop.web.util.LoginUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/5/31
 * Time: 19:10
 */
@Service
public class ProductLabelService extends BaseService<ProductLabel,ProductLabelExample> {

    @Autowired
    private ProductLabelMapper productLabelMapper;

    @Autowired
    private ProductLabelMapperExt productLabelMapperExt;

    @Override
    protected BaseMapper getMapper() {
        return productLabelMapper;
    }


    public PageBean<ProductLabel> selectPageBean(final ProductLabel params, PageQueryBean pageQueryBean) {
        return executePageQuery(new PageQueryExecutor<ProductLabel>() {
            @Override
            public List<ProductLabel> executeQuery() {
                ProductLabelExample productLableExample = new ProductLabelExample();
                ProductLabelExample.Criteria criteria = productLableExample.createCriteria();
                criteria.andNameIsNotNull();
                return productLabelMapper.selectByExample(productLableExample);
            }
        }, pageQueryBean);
    }


    /**
     * 新增标签
     *
     * @param newLable
     */
    @Transactional
    public void addUser(ProductLabel newLable) {
        SysUser loginUser = LoginUserHolder.getLoginUser();

        newLable.setCreatetime(new Date());
        productLabelMapper.insert(newLable);
    }

    /**
     * 更新标签
     *
     * @param lable
     */
    @Transactional
    public void updateUser(ProductLabel lable) {
        SysUser loginUser = LoginUserHolder.getLoginUser();
        //更新
        productLabelMapper.updateByPrimaryKey(lable);
    }


    public int deleteByPrimaryKey(Long id) {
        return productLabelMapper.deleteByPrimaryKey(id);
    }
}
