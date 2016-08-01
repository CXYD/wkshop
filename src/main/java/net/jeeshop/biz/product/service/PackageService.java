package net.jeeshop.biz.product.service;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.base.service.BaseService;
import net.jeeshop.biz.product.client.PackageInfoMapper;
import net.jeeshop.biz.product.model.PackageInfo;
import net.jeeshop.biz.product.model.PackageInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/6/15
 * Time: 15:34
 */
@Service
public class PackageService extends BaseService<PackageInfo,PackageInfoExample> {

    @Autowired
    private PackageInfoMapper packageInfoMapper;
    @Override
    protected BaseMapper<PackageInfo, PackageInfoExample> getMapper() {
        return packageInfoMapper;
    }


    public List<PackageInfo> selectByExampleWithBLOBs(PackageInfoExample packageInfoExample) {
        return packageInfoMapper.selectByExampleWithBLOBs(packageInfoExample);
    }

    public List selectKeyValue(String s) {

        return  packageInfoMapper.selectKeyValue(s);
    }
}
