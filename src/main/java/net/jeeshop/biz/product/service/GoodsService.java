package net.jeeshop.biz.product.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.client.GoodsInfoMapper;
import net.jeeshop.biz.product.model.GoodsInfo;
import net.jeeshop.biz.product.model.GoodsInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/7
 * Time: 16:49
 */
@Service
public class GoodsService extends BaseService<GoodsInfo,GoodsInfoExample> {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    protected BaseMapper<GoodsInfo, GoodsInfoExample> getMapper() {
        return goodsInfoMapper;
    }

    public int save(GoodsInfo goodsInfo){
       return goodsInfoMapper.insert(goodsInfo);
    }

    public List<GoodsInfo> selectByExampleWithBLOBs(GoodsInfoExample goodsInfoExample) { return  goodsInfoMapper.selectByExampleWithBLOBs(goodsInfoExample);
    }

    public int deleteByProductId(Long productId) {
        return goodsInfoMapper.deleteByProductId(productId);
    }
}
