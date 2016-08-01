package net.jeeshop.biz.product.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.client.SpecInfoMapper;
import net.jeeshop.biz.product.model.SpecInfo;
import net.jeeshop.biz.product.model.SpecInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/9
 * Time: 06:37
 */
@Service
public class NormService extends BaseService<SpecInfo,SpecInfoExample> {
    @Autowired
    private SpecInfoMapper specInfoMapper;

    @Override
    protected BaseMapper<SpecInfo, SpecInfoExample> getMapper() {
        return specInfoMapper;
    }
}
