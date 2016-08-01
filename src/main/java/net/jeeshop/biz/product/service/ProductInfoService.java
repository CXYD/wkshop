package net.jeeshop.biz.product.service;

import net.jeeshop.biz.base.bean.PageBean;
import net.jeeshop.biz.base.bean.PageQueryBean;
import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.bean.ProductInfoBean;
import net.jeeshop.biz.product.client.ProductInfoMapper;
import net.jeeshop.biz.product.client.ProductInfoMapperExt;
import net.jeeshop.biz.product.model.ProductInfo;
import net.jeeshop.biz.product.model.ProductInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/7
 * Time: 15:56
 */
@Service
public class ProductInfoService extends BaseService<ProductInfoBean,ProductInfoExample> {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    ProductInfoMapperExt productInfoMapperExt;


    @Override
    protected BaseMapper getMapper() {
        return productInfoMapper;
    }


    public PageBean<ProductInfoBean> selectPageBean(final ProductInfoExample params, PageQueryBean pageQueryBean) {
        return executePageQuery(new PageQueryExecutor<ProductInfoBean>() {
            @Override
            public List<ProductInfoBean> executeQuery() {

                return productInfoMapperExt.selectByParams(params);
            }
        }, pageQueryBean);
    }


    /**
     * 分页查询数据
     *
     * @param
     * @return
     */
    public PageBean<ProductInfoBean> selectPageList(ProductInfoBean productInfo, PageQueryBean pageQueryBean) {
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();

        PageBean<ProductInfoBean> pagerModel = super.selectPageList(example, pageQueryBean);
        return pagerModel;
    }


    public int updateByPrimaryKeySelective(ProductInfo record){
        return productInfoMapper.updateByPrimaryKeySelective(record);
    }


    public List<ProductInfoBean> selectByProductId(ProductInfoExample example)
    {
        return productInfoMapperExt.selectByParams(example);
    }

//    public void update(ProductInfo productInfo){



//        logger.debug("list size:{}", spec.size());
//        JSONArray jsonArr = (JSONArray) JSONArray.toJSON(spec);
//        logger.debug("jsonarry数组:{}",jsonArr.toString());
//        logger.debug("size:{}",jsonArr.size());
//        logger.debug("第一行{}",jsonArr.get(0));
//    };


}
