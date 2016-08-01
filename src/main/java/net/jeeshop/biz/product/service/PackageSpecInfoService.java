package net.jeeshop.biz.product.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.client.PackageSpecInfoMapper;
import net.jeeshop.biz.product.model.PackageSpecInfo;
import net.jeeshop.biz.product.model.PackageSpecInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/22
 * Time: 16:09
 */
@Service
public class PackageSpecInfoService  extends BaseService {

    @Autowired
    private PackageSpecInfoMapper packageSpecInfoMapper;

    @Override
    protected BaseMapper getMapper() {
        return null;
    }

    public List<PackageSpecInfo> selectByExampleWithBLOBs(PackageSpecInfoExample packageSpecInfoExample) {
        return packageSpecInfoMapper.selectByExampleWithBLOBs(packageSpecInfoExample);
    }
}
