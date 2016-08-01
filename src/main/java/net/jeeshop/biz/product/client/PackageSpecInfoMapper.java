package net.jeeshop.biz.product.client;

import net.jeeshop.biz.base.client.BaseMapper;
import net.jeeshop.biz.product.model.PackageSpecInfo;
import net.jeeshop.biz.product.model.PackageSpecInfoExample;

import java.util.List;

public interface PackageSpecInfoMapper extends BaseMapper<PackageSpecInfo, PackageSpecInfoExample> {
    int countByExample(PackageSpecInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PackageSpecInfo record);

    int insertSelective(PackageSpecInfo record);

    List<PackageSpecInfo> selectByExampleWithBLOBs(PackageSpecInfoExample example);

    List<PackageSpecInfo> selectByExample(PackageSpecInfoExample example);

    PackageSpecInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PackageSpecInfo record);

    int updateByPrimaryKeyWithBLOBs(PackageSpecInfo record);

    int updateByPrimaryKey(PackageSpecInfo record);

    int deleteByPackageId(String packageId);
}