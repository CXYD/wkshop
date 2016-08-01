package net.jeeshop.biz.product.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.product.model.PackageInfo;
import net.jeeshop.biz.product.model.PackageInfoExample;

import java.util.List;

public interface PackageInfoMapper extends BaseMapper<PackageInfo, PackageInfoExample> {
    int countByExample(PackageInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PackageInfo record);

    int insertSelective(PackageInfo record);

    List<PackageInfo> selectByExampleWithBLOBs(PackageInfoExample example);

    List<PackageInfo> selectByExample(PackageInfoExample example);

    PackageInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PackageInfo record);

    int updateByPrimaryKeyWithBLOBs(PackageInfo record);

    int updateByPrimaryKey(PackageInfo record);

    List selectKeyValue(String khid);
}